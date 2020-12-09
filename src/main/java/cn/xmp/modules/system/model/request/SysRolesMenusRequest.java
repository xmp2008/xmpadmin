package cn.xmp.modules.system.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 角色菜单关联
 * </p>
 *
 * @author xiemopeng
 * @since 2020-12-01
 */

@Getter
@Setter
@ToString(callSuper = true)
public class SysRolesMenusRequest  {
    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
	private Long menuId;
    /**
     * 角色ID
     */
	private Long roleId;


}
