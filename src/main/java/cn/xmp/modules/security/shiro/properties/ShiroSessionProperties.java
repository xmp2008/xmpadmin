package cn.xmp.modules.security.shiro.properties;

import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * This guy is lazy, nothing left.
 *
 * @author John Deng
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "shiro.session")
public class ShiroSessionProperties {

    private long globalSessionTimeout = 30 * 60 * 1000L;

    private boolean deleteInvalidSessions = true;

    private long validationInterval = 60 * 60 * 1000L;

    private boolean validationSchedulerEnabled = true;

    private String activeSessionsCacheName = "shiro-activeSessionCache";

    private Class<? extends SessionIdGenerator> idGenerator = JavaUuidSessionIdGenerator.class;

}
