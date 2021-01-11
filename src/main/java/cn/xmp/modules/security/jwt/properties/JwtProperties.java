package cn.xmp.modules.security.jwt.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Jwt配置信息
 * </p>
 *
 * @author wangliang
 * @since 2017/12/22
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "xyjwt")
public class JwtProperties {
    /**
     * default enabled true.
     */
    private boolean enabled = true;
    /**
     * default secret xmp.
     */
    private String secret = "dnBjbHVi";
    /**
     * default accessToken expiration 30 days.
     */
    private Long accessTokenExpiration = 2592000L;
    /**
     * default refreshToken expiration 30 days.
     */
    private Long refreshTokenExpiration = 2592000L;
    /**
     * Request Headers ： Authorization
     */
    private String header = "accessToken";
    /**
     * requestUris cancel the filter
     */
    private List<String> requestUris = new ArrayList<>();
    /**
     * 需要拦截的uri list
     */
    private List<String> iterceptUris = new ArrayList<>();
}
