package cn.xmp.moses.validator;

import java.io.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseAbstractParameter implements Validateable, Serializable {
    private static final Logger log = LoggerFactory.getLogger(BaseAbstractParameter.class);

    public BaseAbstractParameter() {
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

    public String toString() {
        return "BaseAbstractParameter()";
    }
}
