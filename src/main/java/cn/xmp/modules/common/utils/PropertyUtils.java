package cn.xmp.modules.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * <p>
 * <p>
 *
 * @author xiemopeng
 * @since 2020/11/18
 */
public class PropertyUtils {
    private static final Logger log = LoggerFactory.getLogger(PropertyUtils.class);

    public PropertyUtils() {
    }

    public static String getBeanName(String methodName) {
        return Introspector.decapitalize(methodName.substring(methodName.startsWith("is") ? 2 : 3));
    }

    public static Annotation[] getFieldAnnotations(Class<?> clazz, String name) {
        try {
            Field f = clazz.getDeclaredField(name);
            Annotation[] annotations = f.getDeclaredAnnotations();
            return annotations;
        } catch (NoSuchFieldException var4) {
            Class<?> superClazz = clazz.getSuperclass();
            if (!superClazz.getName().equals("java.lang.Object")) {
                return getFieldAnnotations(superClazz, name);
            }

            log.trace("No such field {}: {}", name, var4.getMessage());
        } catch (NullPointerException var5) {
            log.trace("Null Exception {}: {}", name, var5.getMessage());
        }

        return null;
    }

    public static Annotation getAnnotation(Object object, String name, Class<? extends Annotation> clazz) {
        try {
            Field f = object.getClass().getDeclaredField(name);
            return f.getAnnotation(clazz);
        } catch (NoSuchFieldException var4) {
            log.debug("{}", var4);
        } catch (NullPointerException var5) {
            log.trace("Null Exception {}: {}", name, var5.getMessage());
        }

        return null;
    }

    public static void invokeSetter(Object obj, String propertyName, Object propertyValue) {
        try {
            PropertyDescriptor objPropertyDescriptor = new PropertyDescriptor(propertyName, obj.getClass());
            Method setter = objPropertyDescriptor.getWriteMethod();
            setter.setAccessible(true);
            setter.invoke(obj, propertyValue);
        } catch (IllegalArgumentException | InvocationTargetException | IntrospectionException | IllegalAccessException var5) {
            log.debug("{}", var5);
        }

    }

    public static Object invokeGetter(Object obj, String propertyName) {
        try {
            PropertyDescriptor objPropertyDescriptor = new PropertyDescriptor(propertyName, obj.getClass());
            Method getter = objPropertyDescriptor.getReadMethod();
            getter.setAccessible(true);
            return getter.invoke(obj);
        } catch (IllegalArgumentException | InvocationTargetException | IntrospectionException | IllegalAccessException var4) {
            log.debug("{}", var4);
            return null;
        }
    }
}

