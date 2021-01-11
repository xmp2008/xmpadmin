package cn.xmp.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * <p>
 * 角色菜单关联表
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */
@Data
@TableName("t_role_menu")
public class RoleMenu extends Model<RoleMenu> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @TableField("ROLE_ID")
    private Long roleId;
    /**
     * 菜单/按钮ID
     */
    @TableField("MENU_ID")
    private Long menuId;

}
