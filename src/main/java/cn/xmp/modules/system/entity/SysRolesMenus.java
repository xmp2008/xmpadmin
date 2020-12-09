package cn.xmp.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * <p>
 * 角色菜单关联
 * </p>
 *
 * @author xiemopeng
 * @since 2020-12-01
 */
@TableName("sys_roles_menus")
public class SysRolesMenus extends Model<SysRolesMenus> {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    @TableId("menu_id")
    private Long menuId;
    /**
     * 角色ID
     */
    @TableField("role_id")
    private Long roleId;


    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    protected Serializable pkVal() {
        return this.menuId;
    }

    @Override
    public String toString() {
        return "SysRolesMenus{" +
        "menuId=" + menuId +
        ", roleId=" + roleId +
        "}";
    }
}
