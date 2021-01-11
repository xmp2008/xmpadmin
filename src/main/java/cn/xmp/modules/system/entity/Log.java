package cn.xmp.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 操作日志表
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */
@TableName("t_log")
public class Log extends Model<Log> {

    private static final long serialVersionUID = 1L;

    /**
     * 日志ID
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;
    /**
     * 操作用户
     */
    @TableField("USERNAME")
    private String username;
    /**
     * 操作内容
     */
    @TableField("OPERATION")
    private String operation;
    /**
     * 耗时
     */
    @TableField("TIME")
    private BigDecimal time;
    /**
     * 操作方法
     */
    @TableField("METHOD")
    private String method;
    /**
     * 方法参数
     */
    @TableField("PARAMS")
    private String params;
    /**
     * 操作者IP
     */
    @TableField("IP")
    private String ip;
    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;
    /**
     * 操作地点
     */
    private String location;


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

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public BigDecimal getTime() {
        return time;
    }

    public void setTime(BigDecimal time) {
        this.time = time;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Log{" +
        "id=" + id +
        ", username=" + username +
        ", operation=" + operation +
        ", time=" + time +
        ", method=" + method +
        ", params=" + params +
        ", ip=" + ip +
        ", createTime=" + createTime +
        ", location=" + location +
        "}";
    }
}
