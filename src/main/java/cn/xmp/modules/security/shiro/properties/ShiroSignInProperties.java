package cn.xmp.modules.security.shiro.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for Shiro FormAuthenticationFilter
 *
 * @author John Deng
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "shiro.sign-in")
public class ShiroSignInProperties {
    /**
     * Request parameter's name of user
     */
    private String userParam = "username";

    /**
     * Request parameter's name of password
     */
    private String passwordParam = "password";

    private String rememberMeParam = "rememberMe";

}
