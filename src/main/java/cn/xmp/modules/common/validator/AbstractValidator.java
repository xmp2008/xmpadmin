package cn.xmp.modules.common.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;

public abstract class AbstractValidator implements IValidator {
    private static final Logger log = LoggerFactory.getLogger(AbstractValidator.class);

    public AbstractValidator() {
    }

    public String validate(Object object, Annotation annotation, Object value, String fieldName) {
        log.warn("method validate is not implemented");
        return null;
    }
}