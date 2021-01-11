package cn.xmp.modules.system.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 登录日志表
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */
@TableName("t_login_log")
public class LoginLog extends Model<LoginLog> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;
    /**
     * 用户名
     */
    @TableField("USERNAME")
    private String username;
    /**
     * 登录时间
     */
    @TableField("LOGIN_TIME")
    private Date loginTime;
    /**
     * 登录地点
     */
    @TableField("LOCATION")
    private String location;
    /**
     * IP地址
     */
    @TableField("IP")
    private String ip;
    /**
     * 操作系统
     */
    @TableField("SYSTEM")
    private String system;
    /**
     * 浏览器
     */
    @TableField("BROWSER")
    private String browser;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "LoginLog{" +
        "id=" + id +
        ", username=" + username +
        ", loginTime=" + loginTime +
        ", location=" + location +
        ", ip=" + ip +
        ", system=" + system +
        ", browser=" + browser +
        "}";
    }
}
