package cn.xmp.modules.system.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * <p>
 * 登录日志表
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */

@Getter
@Setter
@ToString(callSuper = true)
public class LoginLogRequest  {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
	private Long id;
    /**
     * 用户名
     */
	private String username;
    /**
     * 登录时间
     */
	private Date loginTime;
    /**
     * 登录地点
     */
	private String location;
    /**
     * IP地址
     */
	private String ip;
    /**
     * 操作系统
     */
	private String system;
    /**
     * 浏览器
     */
	private String browser;


}
