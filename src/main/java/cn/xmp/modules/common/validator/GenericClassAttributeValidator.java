package cn.xmp.modules.common.validator;

import cn.xmp.modules.common.utils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class GenericClassAttributeValidator implements IClassAttributeValidator {
    private static final Logger log = LoggerFactory.getLogger(GenericClassAttributeValidator.class);
    private static IClassAttributeValidator instance = null;

    private GenericClassAttributeValidator() {
    }

    public static synchronized IClassAttributeValidator getInstance() {
        if (instance == null) {
            instance = new GenericClassAttributeValidator();
        }

        return instance;
    }

    public synchronized void validate(BaseAbstractParameter param, String condition) throws AttributeValidatorException {
        Class clazz = param.getClass();
        Method[] methods = clazz.getMethods();
        Iterator methodIter = Arrays.asList(methods).iterator();
        Method method = null;
        String methodName = null;

        while(true) {
            Object methodResult;
            do {
                String fieldName;
                Annotation[] annotations;
                do {
                    do {
                        do {
                            if (!methodIter.hasNext()) {
                                return;
                            }

                            method = (Method)methodIter.next();
                            methodName = method.getName();
                            fieldName = PropertyUtils.getBeanName(methodName);
                            annotations = PropertyUtils.getFieldAnnotations(param.getClass(), fieldName);
                        } while(!methodName.startsWith("get"));
                    } while(null == annotations);
                } while(0 == annotations.length);

                methodResult = null;

                try {
                    methodResult = method.invoke(param, (Object[])null);
                } catch (IllegalArgumentException var25) {
                    throw new AttributeValidatorException(var25.getMessage());
                } catch (IllegalAccessException var26) {
                    throw new AttributeValidatorException(var26.getMessage());
                } catch (InvocationTargetException var27) {
                    throw new AttributeValidatorException(var27.getMessage());
                }

                String className = clazz.getName();
                className.substring(className.lastIndexOf(46) + 1);
                Annotation[] var12 = annotations;
                int var13 = annotations.length;

                for(int var14 = 0; var14 < var13; ++var14) {
                    Annotation annotation = var12[var14];

                    try {
                        Class<? extends AbstractValidator> validatorClazz = (Class)annotation.getClass().getDeclaredMethod("validator").invoke(annotation);
                        AbstractValidator abstractValidator = (AbstractValidator)BeanUtils.instantiate(validatorClazz);
                        boolean optional = null != condition;
                        if (optional) {
                            String[] whenConditions = new String[0];
                            Method whenMethod = annotation.getClass().getDeclaredMethod("when");
                            whenConditions = (String[])((String[])whenMethod.invoke(annotation));
                            if (0 != whenConditions.length) {
                                String[] var21 = whenConditions;
                                int var22 = whenConditions.length;

                                for(int var23 = 0; var23 < var22; ++var23) {
                                    String when = var21[var23];
                                    if (when.equals(condition)) {
                                        optional = false;
                                        break;
                                    }
                                }
                            } else {
                                optional = false;
                            }
                        }

                        if (!optional && null != abstractValidator) {
                            String message = abstractValidator.validate(param, annotation, methodResult, fieldName);
                            if (null != message) {
                                throw new AttributeValidatorException(message);
                            }
                        }

                        log.debug("{}", annotation.toString());
                    } catch (IllegalAccessException var28) {
                        log.debug("{}", var28.getMessage());
                    } catch (InvocationTargetException var29) {
                        log.debug("{}", var29.getMessage());
                    } catch (NoSuchMethodException var30) {
                        log.debug("{}", var30.getMessage());
                    }
                }
            } while(!(methodResult instanceof Collection));

            Collection col = (Collection)methodResult;
            Iterator iter = col.iterator();
            Object subParam = null;

            while(iter.hasNext()) {
                subParam = iter.next();
                if (subParam instanceof AbstractParameter) {
                    AbstractParameter abstractParam = (AbstractParameter)subParam;
                    abstractParam.validateWith(this);
                }
            }
        }
    }
}
