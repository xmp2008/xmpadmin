package cn.xmp.modules.system.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 角色菜单关联表
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */

@Getter
@Setter
@ToString(callSuper = true)
public class RoleMenuRequest  {
    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
	private Long roleId;
    /**
     * 菜单/按钮ID
     */
	private Long menuId;


}
