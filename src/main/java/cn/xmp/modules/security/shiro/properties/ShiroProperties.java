package cn.xmp.modules.security.shiro.properties;

import cn.xmp.modules.security.shiro.realm.DefaultRealm;
import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.realm.Realm;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * Configuration properties for Shiro.
 *
 * @author John Deng
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "shiro")
public class ShiroProperties {
    /**
     * Custom Realm
     */
    private Class<? extends Realm> realmClass = DefaultRealm.class;

    /**
     * URL of login
     */
    private String loginUrl = "/login";
    /**
     * URL of success
     */
    private String successUrl = "/index";
    /**
     * URL of unauthorized
     */
    private String unauthorizedUrl = "/unauthorized";

    /**
     * URL of default view
     */
    private String defaultViewUrl = "/login.html";

    //是否开启密码加密
    private boolean credentialsEnabled = false;
    //是否开启缓存
    private boolean cacheEnabled = true;
    //是否开启shiro认证
    private boolean shiroEnabled = true;
    //是否使用jwt认证，默认shiro密码认证
    private boolean jwtCredentialsMatcherEnabled = false;

    private String hashAlgorithmName = "MD5";

    private int hashIterations = 1;

    /**
     * 密码重试次数上限
     */
    private boolean customCredentials = false;

    private int retryMax = 5;

    private boolean storedCredentialsHexEncoded = true;

    private String permissionPackage = "cn.xmp";
    /**
     * Filter chain
     */
    private Map<String, String> filterChainDefinitions;

    private String filterChainSql;

    //authc与csrf是否同步支持
    private boolean csrfAfterAuthc = false;

    //csrf token aes 秘钥
    private String csrfKey ;

    //csrf token valid time-默认6秒钟
    private Long csrfExpiredTime = 6000L;

    //是否支持csrf过滤mapping自定义
    private boolean csrfEnabled = false;
}
