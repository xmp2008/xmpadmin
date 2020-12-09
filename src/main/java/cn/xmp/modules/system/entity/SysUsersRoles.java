package cn.xmp.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * <p>
 * 用户角色关联
 * </p>
 *
 * @author xiemopeng
 * @since 2020-12-01
 */
@TableName("sys_users_roles")
public class SysUsersRoles extends Model<SysUsersRoles> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId("user_id")
    private Long userId;
    /**
     * 角色ID
     */
    @TableField("role_id")
    private Long roleId;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

    @Override
    public String toString() {
        return "SysUsersRoles{" +
        "userId=" + userId +
        ", roleId=" + roleId +
        "}";
    }
}
