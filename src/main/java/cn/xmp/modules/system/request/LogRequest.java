package cn.xmp.modules.system.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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

@Getter
@Setter
@ToString(callSuper = true)
public class LogRequest  {
    private static final long serialVersionUID = 1L;

    /**
     * 日志ID
     */
	private Long id;
    /**
     * 操作用户
     */
	private String username;
    /**
     * 操作内容
     */
	private String operation;
    /**
     * 耗时
     */
	private BigDecimal time;
    /**
     * 操作方法
     */
	private String method;
    /**
     * 方法参数
     */
	private String params;
    /**
     * 操作者IP
     */
	private String ip;
    /**
     * 创建时间
     */
	private Date createTime;
    /**
     * 操作地点
     */
	private String location;


}
