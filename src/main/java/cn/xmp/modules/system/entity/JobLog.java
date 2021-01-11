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
 * 调度日志表
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */
@TableName("t_job_log")
public class JobLog extends Model<JobLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 任务日志id
     */
    @TableId(value = "LOG_ID", type = IdType.AUTO)
    private Long logId;
    /**
     * 任务id
     */
    @TableField("JOB_ID")
    private Long jobId;
    /**
     * spring bean名称
     */
    @TableField("BEAN_NAME")
    private String beanName;
    /**
     * 方法名
     */
    @TableField("METHOD_NAME")
    private String methodName;
    /**
     * 参数
     */
    @TableField("PARAMS")
    private String params;
    /**
     * 任务状态    0：成功    1：失败
     */
    @TableField("STATUS")
    private String status;
    /**
     * 失败信息
     */
    @TableField("ERROR")
    private String error;
    /**
     * 耗时(单位：毫秒)
     */
    @TableField("TIMES")
    private BigDecimal times;
    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;


    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public BigDecimal getTimes() {
        return times;
    }

    public void setTimes(BigDecimal times) {
        this.times = times;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.logId;
    }

    @Override
    public String toString() {
        return "JobLog{" +
        "logId=" + logId +
        ", jobId=" + jobId +
        ", beanName=" + beanName +
        ", methodName=" + methodName +
        ", params=" + params +
        ", status=" + status +
        ", error=" + error +
        ", times=" + times +
        ", createTime=" + createTime +
        "}";
    }
}
