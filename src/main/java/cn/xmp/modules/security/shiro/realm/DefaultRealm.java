package cn.xmp.modules.security.shiro.realm;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Default Realm
 * Created by johnd on 8/20/16.
 */

public class DefaultRealm extends AuthorizingRealm implements ApplicationContextAware {

    protected ApplicationContext applicationContext;

    public static final String SHIRO_NS_REALM = "shiro-ns-realm";

    /**
     * Returns {@code true} if authorization caching should be utilized if a {@link CacheManager} has been
     * {@link #setCacheManager(org.apache.shiro.cache.CacheManager) configured}, {@code false} otherwise.
     * <p/>
     * The default value is {@code true}.
     *
     * @return {@code true} if authorization caching should be utilized, {@code false} otherwise.
     */
    @Override
    public boolean isAuthorizationCachingEnabled() {
        return false;
    }

//    private Cache<Object, AuthorizationInfo> getAuthorizationCacheLazy() {
//        return null;
//    }
    /**
     * 授权认证
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        HazelcastInstance hazelcastInstance = applicationContext.getBean("hazelcastInstance", com.hazelcast.client.impl.HazelcastClientProxy.class);
        IMap<String, SimpleAuthorizationInfo> cacheMap = hazelcastInstance.getMap(SHIRO_NS_REALM);
        return cacheMap.get(session.getId().toString());
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;

    }
}
