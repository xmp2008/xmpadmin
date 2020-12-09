package cn.xmp.modules.common.validator;

import java.lang.annotation.Annotation;

public interface IValidator {
    String validate(Object var1, Annotation var2, Object var3, String var4);
}
