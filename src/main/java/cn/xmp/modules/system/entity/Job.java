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
 * 定时任务表
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */
@TableName("t_job")
public class Job extends Model<Job> {

    private static final long serialVersionUID = 1L;

    /**
     * 任务id
     */
    @TableId(value = "JOB_ID", type = IdType.AUTO)
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
     * cron表达式
     */
    @TableField("CRON_EXPRESSION")
    private String cronExpression;
    /**
     * 任务状态  0：正常  1：暂停
     */
    @TableField("STATUS")
    private String status;
    /**
     * 备注
     */
    @TableField("REMARK")
    private String remark;
    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;


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

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.jobId;
    }

    @Override
    public String toString() {
        return "Job{" +
        "jobId=" + jobId +
        ", beanName=" + beanName +
        ", methodName=" + methodName +
        ", params=" + params +
        ", cronExpression=" + cronExpression +
        ", status=" + status +
        ", remark=" + remark +
        ", createTime=" + createTime +
        "}";
    }
}
