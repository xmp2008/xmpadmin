package cn.xmp.modules.common.validator.validators;

import cn.xmp.modules.common.validator.AbstractValidator;
import cn.xmp.modules.common.validator.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;

public class NotNullValidator extends AbstractValidator {
    private static final Logger log = LoggerFactory.getLogger(NotNullValidator.class);

    public NotNullValidator() {
    }

    public String validate(Object object, Annotation annotation, Object value, String fieldName) {
        return null == value ? object.getClass().getSimpleName() + "." + fieldName + " " + ((NotNull)annotation).message() : null;
    }
}