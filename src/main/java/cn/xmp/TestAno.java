package cn.xmp;

import cn.xmp.modules.system.entity.SysUser;

import java.lang.annotation.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * <p>注解和反射测试
 * <p>
 *
 * @author xiemopeng
 * @since 2020/12/3
 */
@MyAnnotation("我是注解")
public class TestAno {
    @MyAnnotation(value = "我是方法上的注解", basePackageClasses = {TestAno.class})
//    @Deprecated
    public static void test() {
        System.out.println("我是通过反射调用的");
    }

    @Override
    public String toString() {
        return super.toString();
    }

    //    @SuppressWarnings("all")
    public void test1() {
        test();
    }

    @SuppressWarnings("all")
    public void test2(Map<Long, String> map, List<String> list) {
    }

    public Map<Long, String> test3(Map<Long, String> map, List<String> list) {
        return null;
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchFieldException {
        Class<?> aClass = Class.forName("cn.xmp.TestAno");
        Class<?> aClass1 = Class.forName("cn.xmp.TestAno");
        Class<?> aClass2 = Class.forName("cn.xmp.TestAno");
        Class<?> aClass3 = Class.forName("cn.xmp.TestAno");
        System.out.println("aClass = " + aClass);
        System.out.println(aClass.hashCode());
        System.out.println(aClass1.hashCode());
        System.out.println(aClass2.hashCode());
        System.out.println(aClass3.hashCode());
        Class<SysUser> sysUserClass = SysUser.class;
        SysUser sysUser = new SysUser();
        Class<? extends SysUser> aClass4 = sysUser.getClass();
        Class<?> aClass5 = Class.forName("cn.xmp.modules.system.entity.SysUser");
        SysUser sysUser1 = sysUserClass.newInstance();
        System.out.println("sysUser1.getClass() = " + sysUser1.getClass());
        Class<Integer> type = Integer.TYPE;
        System.out.println("type = " + type);
        //实例化对象
        TestAno o = (TestAno) aClass.newInstance();
        Method test = aClass.getDeclaredMethod("test");
        //通过反射调用方法
        test.invoke(o);
        //获取泛型参数
        Method test2 = aClass.getMethod("test2", Map.class, List.class);
        Type[] genericParameterTypes = test2.getGenericParameterTypes();
        System.out.println("genericParameterTypes = " + genericParameterTypes);
        for (Type genericParameterType : genericParameterTypes) {
            System.out.println("genericParameterType = " + genericParameterType);
            if (genericParameterType instanceof ParameterizedType) {
                Type[] actualTypeArguments = ((ParameterizedType) genericParameterType).getActualTypeArguments();
                for (Type actualTypeArgument : actualTypeArguments) {
                    System.out.println("actualTypeArgument = " + actualTypeArgument);
                }
            }
        }
        //获取泛型返回
        Method test3 = aClass.getMethod("test3", Map.class, List.class);
        Type genericReturnType = test3.getGenericReturnType();
        System.out.println("genericReturnType = " + genericReturnType);
        if (genericReturnType instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) genericReturnType).getActualTypeArguments();
            for (Type actualTypeArgument : actualTypeArguments) {
                System.out.println("actualTypeArgument = " + actualTypeArgument);
            }
        }
        //获取注解
        Annotation[] annotations = aClass.getAnnotations();
        System.out.println("annotations = " + annotations);
        for (Annotation annotation : annotations) {
            System.out.println("annotation = " + annotation);
        }
        MyAnnotation annotation = aClass.getAnnotation(MyAnnotation.class);
        System.out.println("annotation.value() = " + annotation.value());
        Method test1 = aClass.getDeclaredMethod("test");
        MyAnnotation declaredAnnotation = test1.getDeclaredAnnotation(MyAnnotation.class);
        System.out.println("declaredAnnotation.value() = " + declaredAnnotation.value());
    }

}

@Target({ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {
    String value() default "";

    String[] basePackages() default {};

    Class<?>[] basePackageClasses() default {};
}

