package cn.xmp.modules.system.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */

@Getter
@Setter
@ToString(callSuper = true)
public class RoleRequest  {
    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
	private Long roleId;
    /**
     * 角色名称
     */
	private String roleName;
    /**
     * 角色描述
     */
	private String remark;
    /**
     * 创建时间
     */
	private Date createTime;
    /**
     * 修改时间
     */
	private Date modifyTime;


}
