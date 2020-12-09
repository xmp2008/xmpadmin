package cn.xmp.modules.common.validator.annotations;

import cn.xmp.modules.common.validator.AbstractValidator;
import cn.xmp.modules.common.validator.validators.NotNullValidator;
import org.hibernate.validator.constraints.Range;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
    validatedBy = {}
)
public @interface NotNull {
    String message() default "message.validator.NotNull";

    String[] when() default {};

    Class<? extends AbstractValidator> validator() default NotNullValidator.class;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface List {
        Range[] value();
    }
}
