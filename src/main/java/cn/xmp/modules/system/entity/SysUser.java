package cn.xmp.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.util.Date;
import java.util.Set;

/**
 * <p>
 * 系统用户
 * </p>
 *
 * @author xiemopeng
 * @since 2020-12-01
 */
@Data
@TableName("sys_user")
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;
    /**
     * 部门名称
     */
    @TableField("dept_id")
    private Long deptId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;
    /**
     * 性别
     */
    private String gender;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 头像地址
     */
    @TableField("avatar_name")
    private String avatarName;
    /**
     * 头像真实路径
     */
    @TableField("avatar_path")
    private String avatarPath;
    /**
     * 密码
     */
    private String password;
    /**
     * 是否为admin账号
     */
    @TableField("is_admin")
    private Boolean isAdmin;
    /**
     * 状态：1启用、0禁用
     */
    private Long enabled;
    /**
     * 创建者
     */
    @TableField("create_by")
    private String createBy;
    /**
     * 更新着
     */
    @TableField("update_by")
    private String updateBy;
    /**
     * 修改密码的时间
     */
    @TableField("pwd_reset_time")
    private Date pwdResetTime;
    /**
     * 创建日期
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;

    @TableField(exist = false)
    private Set<String> stringPermissions;
    @TableField(exist = false)
    private Set<String> roles;
}
