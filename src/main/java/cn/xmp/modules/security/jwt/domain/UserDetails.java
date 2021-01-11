package cn.xmp.modules.security.jwt.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * Token数据声明
 * </p>
 *
 * @author wangliang
 * @since 2017/12/20
 */
@Data
public class UserDetails implements Serializable {

    /**
     * 帐号
     */
    private String account;

    /**
     * 用户登录时输入的账号 有可能是4A账号、4A手机号、店员手机号
     */
    private String loginUsername;

    /**
     * 设备唯一标识
     */
    private String deviceId;

    /**
     * 设备型号
     */
    private String deviceType;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 无参构造
     */
    public UserDetails() {

    }

    public UserDetails(String account) {
        this.account = account;
    }

}
