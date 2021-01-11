package cn.xmp.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * <p>
 * 用户角色关联表
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */
@Data
@TableName("t_user_role")
public class UserRole extends Model<UserRole> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableField("USER_ID")
    private Long userId;
    /**
     * 角色ID
     */
    @TableField("ROLE_ID")
    private Long roleId;

}
