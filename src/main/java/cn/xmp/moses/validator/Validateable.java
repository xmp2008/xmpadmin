package cn.xmp.moses.validator;

public interface Validateable {
    void validateWith(IClassAttributeValidator var1) throws Exception;

    void validate(String var1) throws Exception;

    void validate() throws Exception;
}
