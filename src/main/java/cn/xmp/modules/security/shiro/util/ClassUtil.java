package cn.xmp.modules.security.shiro.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by chenwei on 2017/6/5.
 * 用于获取指定包名下的所有类名.<br/>
 * 并可设置是否遍历该包名下的子包的类名.<br/>
 * 并可通过Annotation(内注)来过滤，避免一些内部类的干扰.<br/>
 * project name: security-admin-provider
 */
@Slf4j
public class ClassUtil {
//    public static void main(String[] args) {
////      String pkg = "javax.crypto.spec";// 为java/jre6/lib/jce.jar，普通的java工程默认已引用
////      String pkg = "javax.crypto";
////      String pkg = "lab.sodino";
//        //指定包名
//        String pkg = "cn.vpclub.moses.security.admin.consumer.web";
//        Map<String, String> permissions = getPermissions(pkg, true);
//        log.debug("permissions is -- {}", JsonUtil.objectToJson(permissions));
//    }

    /**
     * @param pkg       指定的包名
     * @param recursive 标识是否要遍历该包路径下子包的类名
     * @return
     */
    public static Map<String, String> getPermissions(String pkg, Boolean recursive) {
        if (null == recursive) {
            recursive = true;
        }
        List<Class<? extends Object>> list = null;
//      list = getClassList(pkg, recursive, null);
        // 增加 author.class的过滤项，即可只选出ClassTestDemo
        list = getClassList(pkg, recursive, RestController.class);
        Map<String, Class<? extends Object>> beansWithAnnotation = new HashedMap();

        for (int i = 0; i < list.size(); i++) {
            log.debug(i + ":{}", list.get(i));
            beansWithAnnotation.put(i + ":", list.get(i));
        }
        return traverse(beansWithAnnotation);
    }

    //遍历requirePermissions
    public static Map<String, String> traverse(Map<String, Class<? extends Object>> map) {
        Class<? extends Object> clazz = null;
        Map<String, String> pers = new HashMap<String, String>();
        Map<String, String> permissionList = new HashedMap();
        for (Map.Entry<String, Class<? extends Object>> entry : map.entrySet()) {
            clazz = entry.getValue();//获取到实例对象的class信息
            //接口信息
            Method[] methods = clazz.getDeclaredMethods();
            RequestMapping superMapping = clazz.getDeclaredAnnotation(RequestMapping.class);

            for (Method method : methods) {
                //过滤方法名
                RequiresPermissions permissions = method.getDeclaredAnnotation(RequiresPermissions.class);
                RequestMapping rp = method.getDeclaredAnnotation(RequestMapping.class);
                if (null != permissions && null != rp) {
                    log.debug("permission is " + permissions.value()[0]);
                    String path = rp.value()[0];
                    if (null != superMapping) {
                        path = superMapping.value()[0] + rp.value()[0];
                    }
                    permissionList.put(path, permissions.value()[0]);
                }

            }
        }
        for (String path : permissionList.keySet()) {
            String permission = permissionList.get(path);
            log.debug("{}==>{}", path, permission);
            pers.put(path, permission);
        }

        return pers;
    }

    public static List<Class<? extends Object>> getClassList(String pkgName, boolean isRecursive, Class<? extends Annotation> annotation) {
        List<Class<? extends Object>> classList = new ArrayList<Class<? extends Object>>();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try {
            // 按文件的形式去查找
            String strFile = pkgName.replaceAll("\\.", "/");
            Enumeration<URL> urls = loader.getResources(strFile);
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if (url != null) {
                    String protocol = url.getProtocol();
                    String pkgPath = url.getPath();
                    log.debug("protocol:" + protocol + " path:" + pkgPath);
                    if ("file".equals(protocol)) {
                        // 本地自己可见的代码
                        findClassName(classList, pkgName, pkgPath, isRecursive, annotation);
                    } else if ("jar".equals(protocol)) {
                        // 引用第三方jar的代码
                        findClassName(classList, pkgName, url, isRecursive, annotation);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return classList;
    }

    public static void findClassName(List<Class<? extends Object>> clazzList, String pkgName, String pkgPath, boolean isRecursive, Class<? extends Annotation> annotation) {
        if (clazzList == null) {
            return;
        }
        File[] files = filterClassFiles(pkgPath);// 过滤出.class文件及文件夹
        log.debug("files:" + ((files == null) ? "null" : "length=" + files.length));
        if (files != null) {
            for (File f : files) {
                String fileName = f.getName();
                if (f.isFile()) {
                    // .class 文件的情况
                    String clazzName = getClassName(pkgName, fileName);
                    addClassName(clazzList, clazzName, annotation);
                } else {
                    // 文件夹的情况
                    if (isRecursive) {
                        // 需要继续查找该文件夹/包名下的类
                        String subPkgName = pkgName + "." + fileName;
                        String subPkgPath = pkgPath + "/" + fileName;
                        findClassName(clazzList, subPkgName, subPkgPath, true, annotation);
                    }
                }
            }
        }
    }

    /**
     * 第三方Jar类库的引用。<br/>
     *
     * @throws IOException
     */
    public static void findClassName(List<Class<? extends Object>> clazzList, String pkgName, URL url, boolean isRecursive, Class<? extends Annotation> annotation) throws IOException {
        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
        JarFile jarFile = jarURLConnection.getJarFile();
        log.debug("jarFile:" + jarFile.getName());
        Enumeration<JarEntry> jarEntries = jarFile.entries();
        while (jarEntries.hasMoreElements()) {
            JarEntry jarEntry = jarEntries.nextElement();
            String jarEntryName = jarEntry.getName(); // 类似：sun/security/internal/interfaces/TlsMasterSecret.class
            String clazzName = jarEntryName.replace("/", ".");
            int endIndex = clazzName.lastIndexOf(".");
            String prefix = null;
            if (endIndex > 0) {
                String prefix_name = clazzName.substring(0, endIndex);
                endIndex = prefix_name.lastIndexOf(".");
                if (endIndex > 0) {
                    prefix = prefix_name.substring(0, endIndex);
                }
            }
            if (prefix != null && jarEntryName.endsWith(".class")) {
//              log.debug("prefix:" + prefix +" pkgName:" + pkgName);
                if (prefix.equals(pkgName)) {
                    log.debug("jar entryName:" + jarEntryName);
                    addClassName(clazzList, clazzName, annotation);
                } else if (isRecursive && prefix.startsWith(pkgName)) {
                    // 遍历子包名：子类
                    log.debug("jar entryName:" + jarEntryName + " isRecursive:" + isRecursive);
                    addClassName(clazzList, clazzName, annotation);
                }
            }
        }
    }

    private static File[] filterClassFiles(String pkgPath) {
        if (pkgPath == null) {
            return null;
        }
        // 接收 .class 文件 或 类文件夹
        return new File(pkgPath).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory();
            }
        });
    }

    private static String getClassName(String pkgName, String fileName) {
        int endIndex = fileName.lastIndexOf(".");
        String clazz = null;
        if (endIndex >= 0) {
            clazz = fileName.substring(0, endIndex);
        }
        String clazzName = null;
        if (clazz != null) {
            clazzName = pkgName + "." + clazz;
        }
        return clazzName;
    }

    private static void addClassName(List<Class<? extends Object>> clazzList, String clazzName, Class<? extends Annotation> annotation) {
        if (clazzList != null && clazzName != null) {
            Class<? extends Object> clazz = null;
            try {
                log.debug("clazzName is {}", clazzName);
                clazzName = clazzName.replaceAll("\\.class", "");
                log.debug("clazzName is {}", clazzName);
                clazz = Class.forName(clazzName);
            } catch (ClassNotFoundException e) {
                log.error("error : {}", e);
            }
//          log.debug("isAnnotation=" + clazz.isAnnotation() +" author:" + clazz.isAnnotationPresent(author.class));

            if (clazz != null) {
                if (annotation == null) {
                    clazzList.add(clazz);
                    log.debug("add:{}", clazz);
                } else if (clazz.isAnnotationPresent(annotation)) {
                    clazzList.add(clazz);
                    log.debug("add annotation:{}", clazz);
                }
            }
        }
    }
}
