package cn.xmp.moses.entity;

import cn.xmp.moses.validator.BaseAbstractParameter;
import cn.xmp.moses.validator.ValidatorConditionType;
import cn.xmp.moses.validator.annotations.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * <p>
 *
 * @author xiemopeng
 * @since 2020/11/18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonV extends BaseAbstractParameter {

    //    @NotNull(message = "classId 不能为空")
//    private String classId;
//
//    @Size(max = 33)
//    @NotNull(message = "name 不能为空")
//    private String name;
//
//    @Pattern(regexp = "((^Man$|^Woman$|^UGM$))", message = "sex 值不在可选范围")
//    @NotNull(message = "sex 不能为空")
//    private String sex;
//
//    @Email(message = "email 格式不正确")
//    @NotNull(message = "email 不能为空")
//    private String email;
//
//    //    @Region(message = "地区不正确")
//    private String region;
//    @NotNull(message = "新增时年龄必填", groups = ValidationGroup.Create.class)
    private String age;
    @NotEmpty(when = ValidatorConditionType.READ, message = "id不能为空")
    private String id;
}
