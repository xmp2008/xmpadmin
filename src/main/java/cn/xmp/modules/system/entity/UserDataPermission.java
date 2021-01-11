package cn.xmp.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * <p>
 * 用户数据权限关联表
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */
@TableName("t_user_data_permission")
public class UserDataPermission extends Model<UserDataPermission> {

    private static final long serialVersionUID = 1L;

    @TableId("USER_ID")
    private Long userId;
    @TableField("DEPT_ID")
    private Long deptId;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

    @Override
    public String toString() {
        return "UserDataPermission{" +
        "userId=" + userId +
        ", deptId=" + deptId +
        "}";
    }
}
