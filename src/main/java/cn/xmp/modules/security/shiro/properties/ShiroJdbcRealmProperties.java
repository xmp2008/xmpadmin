package cn.xmp.modules.security.shiro.properties;

import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * This guy is lazy, nothing left.
 *
 * @author John Deng
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "shiro.realm.jdbc")
public class ShiroJdbcRealmProperties {

    private boolean enabled;

    private JdbcRealm.SaltStyle salt;

    /**
     * select password from users where username = ?
     */
    private String authenticationQuery;

    /**
     * select role_name from user_roles where username = ?
     */
    private String userRolesQuery;

    /**
     * select permission from roles_permissions where role_name = ?
     */
    private String permissionsQuery;

    private boolean permissionsLookupEnabled = true;

}
