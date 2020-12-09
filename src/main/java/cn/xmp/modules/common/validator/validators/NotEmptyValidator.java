package cn.xmp.modules.common.validator.validators;

import cn.xmp.modules.common.validator.AbstractValidator;
import cn.xmp.modules.common.validator.annotations.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;

public class NotEmptyValidator extends AbstractValidator {
    private static final Logger log = LoggerFactory.getLogger(NotEmptyValidator.class);

    public NotEmptyValidator() {
    }

    public String validate(Object object, Annotation annotation, Object value, String fieldName) {
        return null != value && !value.toString().isEmpty() ? null : object.getClass().getSimpleName() + "." + fieldName + " " + ((NotEmpty)annotation).message();
    }
}
