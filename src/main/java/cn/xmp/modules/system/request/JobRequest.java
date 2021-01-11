package cn.xmp.modules.system.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * <p>
 * 定时任务表
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */

@Getter
@Setter
@ToString(callSuper = true)
public class JobRequest  {
    private static final long serialVersionUID = 1L;

    /**
     * 任务id
     */
	private Long jobId;
    /**
     * spring bean名称
     */
	private String beanName;
    /**
     * 方法名
     */
	private String methodName;
    /**
     * 参数
     */
	private String params;
    /**
     * cron表达式
     */
	private String cronExpression;
    /**
     * 任务状态  0：正常  1：暂停
     */
	private String status;
    /**
     * 备注
     */
	private String remark;
    /**
     * 创建时间
     */
	private Date createTime;


}
