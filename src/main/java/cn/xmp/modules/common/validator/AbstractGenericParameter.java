package cn.xmp.modules.common.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractGenericParameter extends BaseAbstractParameter {
    private static final Logger log = LoggerFactory.getLogger(AbstractGenericParameter.class);

    public AbstractGenericParameter() {
    }

    public void validateWith(IClassAttributeValidator validator) throws AttributeValidatorException {
        validator.validate(this, (String)null);
    }

    public void validate(String condition) throws AttributeValidatorException {
        IClassAttributeValidator validator = GenericClassAttributeValidator.getInstance();
        validator.validate(this, condition);
    }

    public void validate() throws AttributeValidatorException {
        this.validate((String)null);
    }
}
