package cn.xmp.modules.system.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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

@Getter
@Setter
@ToString(callSuper = true)
public class JobLogRequest  {
    private static final long serialVersionUID = 1L;

    /**
     * 任务日志id
     */
	private Long logId;
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
     * 任务状态    0：成功    1：失败
     */
	private String status;
    /**
     * 失败信息
     */
	private String error;
    /**
     * 耗时(单位：毫秒)
     */
	private BigDecimal times;
    /**
     * 创建时间
     */
	private Date createTime;


}
