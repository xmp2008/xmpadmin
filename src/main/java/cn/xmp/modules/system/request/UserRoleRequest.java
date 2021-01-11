package cn.xmp.modules.system.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 用户角色关联表
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */

@Getter
@Setter
@ToString(callSuper = true)
public class UserRoleRequest  {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
	private Long userId;
    /**
     * 角色ID
     */
	private Long roleId;


}
