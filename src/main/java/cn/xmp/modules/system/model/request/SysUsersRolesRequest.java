package cn.xmp.modules.system.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 用户角色关联
 * </p>
 *
 * @author xiemopeng
 * @since 2020-12-01
 */

@Getter
@Setter
@ToString(callSuper = true)
public class SysUsersRolesRequest  {
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
