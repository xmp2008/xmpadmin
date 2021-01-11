package cn.xmp.modules.system.request;

import cn.xmp.modules.common.validator.AbstractGenericParameter;
import cn.xmp.modules.common.validator.ValidatorConditionType;
import cn.xmp.modules.common.validator.annotations.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */

@Getter
@Setter
@ToString(callSuper = true)
public class UserRequest  extends AbstractGenericParameter {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
	private Long userId;
    /**
     * 用户名
     */
	private String username;
    /**
     * 密码
     */
	private String password;
    /**
     * 部门ID
     */
	private Long deptId;
    /**
     * 邮箱
     */
	private String email;
    /**
     * 联系电话
     */
	private String mobile;
    /**
     * 状态 0锁定 1有效
     */
	private String status;
    /**
     * 创建时间
     */
	private Date createTime;
    /**
     * 修改时间
     */
	private Date modifyTime;
    /**
     * 最近访问时间
     */
	private Date lastLoginTime;
    /**
     * 性别 0男 1女 2保密
     */
	private String ssex;
    /**
     * 是否开启tab，0关闭 1开启
     */
	private String isTab;
    /**
     * 主题
     */
	private String theme;
    /**
     * 头像
     */
	private String avatar;
    /**
     * 描述
     */
	private String description;
    /**
     * 角色 ID组
     */
    @NotNull(when = {ValidatorConditionType.READ}, message = "用户角色不能为空")
    private String roleIds;
    /**
     * 部门id组
     */
    private String deptIds;

}
