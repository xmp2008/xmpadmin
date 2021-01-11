package cn.xmp.modules.security.shiro;

import cn.xmp.modules.security.shiro.constant.AuthConstant;
import cn.xmp.modules.security.shiro.controller.ExceptionController;
import cn.xmp.modules.security.shiro.controller.RequirePermissionController;
import cn.xmp.modules.security.shiro.filter.AuthcFilter;
import cn.xmp.modules.security.shiro.filter.CsrfFilter;
import cn.xmp.modules.security.shiro.filter.IllegalCharacterFilter;
import cn.xmp.modules.security.shiro.properties.ShiroCookieProperties;
import cn.xmp.modules.security.shiro.properties.ShiroProperties;
import cn.xmp.modules.security.shiro.properties.ShiroSessionProperties;
import cn.xmp.modules.security.shiro.properties.ShiroSignInProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.crypto.CipherService;
import org.apache.shiro.io.Serializer;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.ExecutorServiceSessionValidationScheduler;
import org.apache.shiro.session.mgt.SessionValidationScheduler;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author John Deng
 */
@Configuration
//@EnableShiroWebSupport
@ConditionalOnWebApplication
@Import(ShiroConfiguration.class)
@EnableConfigurationProperties({
        ShiroProperties.class,
        ShiroSignInProperties.class,
        ShiroCookieProperties.class,
        ShiroSessionProperties.class,
//        ShiroJdbcRealmProperties.class
})
@Slf4j
//@RequiredArgsConstructor
public class ShiroAutoConfiguration {


    @Autowired
    private ShiroProperties properties;

    @Autowired
    private ShiroSignInProperties signInProperties;

    @Autowired
    private ShiroCookieProperties shiroCookieProperties;

    @Autowired
    private ShiroSessionProperties shiroSessionProperties;


    @Autowired(required = false)
    private CipherService cipherService;

    @Autowired(required = false)
    private Serializer<PrincipalCollection> serializer;

    @Autowired(required = false)
    private Collection<SessionListener> listeners;
    @Autowired
    private RedisProperties redisProperties;

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(realm());
        defaultWebSecurityManager.setSessionManager(sessionManager());
//        defaultWebSecurityManager.setCacheManager(cacheManager());
        SecurityUtils.setSecurityManager(defaultWebSecurityManager);
        return defaultWebSecurityManager;
    }

    @Bean(name = "mainRealm")
    @ConditionalOnMissingBean(name = "mainRealm")
    public Realm realm() {
        Realm realm = null;
        Class<?> realmClass = properties.getRealmClass();
        if (null != realmClass) {
            realm = (Realm) BeanUtils.instantiate(realmClass);
            if (realm instanceof AuthenticatingRealm) {
                if (properties.isCredentialsEnabled()) {
                    ((AuthenticatingRealm) realm).setCredentialsMatcher(credentialsMatcher());
                }
            }
        }
        return realm;
    }

    @Bean(name = "credentialsMatcher")
    public CredentialsMatcher credentialsMatcher() {
        RetryLimitHashedCredentialsMatcher credentialsMatcher = null;
        if (properties.isJwtCredentialsMatcherEnabled()) {
            //使用jwt认证
            credentialsMatcher = new JwtCredentialsMatcher(cacheManager());
        } else {
            credentialsMatcher = new RetryLimitHashedCredentialsMatcher(
                    cacheManager());
            credentialsMatcher.setHashAlgorithmName(properties.getHashAlgorithmName());
            credentialsMatcher.setHashIterations(properties.getHashIterations());
            credentialsMatcher.setStoredCredentialsHexEncoded(properties.isStoredCredentialsHexEncoded());
            credentialsMatcher.setRetryMax(properties.getRetryMax());
        }
        return credentialsMatcher;
    }

    @Bean(name = "sessionValidationScheduler")
    @DependsOn(value = {"sessionManager"})
    @ConditionalOnMissingBean(SessionValidationScheduler.class)
    public SessionValidationScheduler sessionValidationScheduler(DefaultWebSessionManager sessionManager) {
        ExecutorServiceSessionValidationScheduler validationScheduler = new ExecutorServiceSessionValidationScheduler(sessionManager);
        sessionManager.setDeleteInvalidSessions(shiroSessionProperties.isDeleteInvalidSessions());
        sessionManager.setSessionValidationInterval(shiroSessionProperties.getValidationInterval());
        sessionManager.setSessionValidationSchedulerEnabled(shiroSessionProperties.isValidationSchedulerEnabled());
        sessionManager.setSessionValidationScheduler(validationScheduler);
        return validationScheduler;
    }


    /**
     * shiro 中配置 redis 缓存
     *
     * @return RedisManager
     */
    private RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(redisProperties.getHost() + ":" + redisProperties.getPort());
        if (org.apache.commons.lang3.StringUtils.isNotBlank(redisProperties.getPassword())) {
            redisManager.setPassword(redisProperties.getPassword());
        }
        redisManager.setTimeout(redisManager.getTimeout());
        redisManager.setDatabase(redisProperties.getDatabase());
        return redisManager;
    }

    @Bean(name = "redisCacheManager")
    public RedisCacheManager cacheManager() {
        System.out.println("redis缓存生效");
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        // 权限缓存超时时间，和session超时时间一致
        redisCacheManager.setExpire((int) shiroSessionProperties.getGlobalSessionTimeout());
        redisCacheManager.setRedisManager(redisManager());
        redisCacheManager.setPrincipalIdFieldName("userId");
        return redisCacheManager;
    }

//    @Bean(name = "hazelcastCacheManager")
//    public HazelcastCacheManager cacheManager() {
//        System.out.println("shiro缓存配置生效");
//        HazelcastCacheManager hazelcastCacheManager = new HazelcastCacheManager();
//
//        hazelcastCacheManager.setHazelcastInstance(hazelcastInstance);
//
//        return hazelcastCacheManager;
//    }

    @Bean
    public DefaultWebSessionManager sessionManager() {
        final DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        Collection<SessionListener> listeners = new ArrayList<>();
        listeners.add(new ShiroSessionListener());
        sessionManager.setSessionListeners(listeners);
        sessionManager.setSessionDAO(sessionDao());
        sessionManager.setGlobalSessionTimeout(shiroSessionProperties.getGlobalSessionTimeout());
        sessionManager.setSessionValidationSchedulerEnabled(false);
        sessionManager.setSessionIdCookieEnabled(true);
        return sessionManager;
    }

    @Bean
    @DependsOn("redisCacheManager")
    public SessionDAO sessionDao() {
        EnterpriseCacheSessionDAO sessionDAO = new EnterpriseCacheSessionDAO();
        sessionDAO.setCacheManager(cacheManager());
        return sessionDAO;
    }

    @Bean(name = "shiroFilter")
    @DependsOn("securityManager")
    @ConditionalOnMissingBean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(SecurityManager securityManager)
            throws Exception {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
//        shiroFilter.setLoginUrl(AuthConstant.UNAUTHENTICATED_URL);
        shiroFilter.setLoginUrl(properties.getLoginUrl());
        shiroFilter.setSuccessUrl(properties.getSuccessUrl());
        shiroFilter.setUnauthorizedUrl(AuthConstant.UNAUTHORIZED_URL);

        Map<String, String> filterChains = new LinkedHashMap<>();

        if (properties.getFilterChainDefinitions() != null) {
            filterChains.putAll(properties.getFilterChainDefinitions());
            //添加authc for getPermissions method
            filterChains.put("/getPermissions", "anon");
        }

        if (properties.isCsrfEnabled()) {
            shiroFilter.getFilters().put("csrf", crsfFilter().getFilter());
            //同步authc到csrf
            if (properties.isCsrfEnabled() && properties.isCsrfAfterAuthc()) {
                if (CollectionUtils.isNotEmpty(filterChains.keySet())) {
                    filterChains.keySet().stream().forEach(key -> {
                        String value = filterChains.get(key);
                        if (value.contains("authc")) {
                            filterChains.put(key, value + ",csrf");
                        }
                    });
                }
            }
        }
        shiroFilter.setFilterChainDefinitionMap(filterChains);
//        shiroFilter.setFilters(filterChains);
        //判断是否有illegalCharacterFilter要求
        if (CollectionUtils.isNotEmpty(filterChains.keySet()) && filterChains.containsValue("illegalCharacterFilter")) {
            shiroFilter.getFilters().put("illegalCharacterFilter", illegalCharacterFilter());
        }
//        shiroFilter.getFilters().put("jwtFilter", jwtFilter());

        return shiroFilter;
    }

    @Bean(name = "illegalCharacterFilter")
    public IllegalCharacterFilter illegalCharacterFilter() {
        return new IllegalCharacterFilter();
    }

//    @Bean(name = "JwtFilter")
//    public JwtFilter jwtFilter() {
//        return new JwtFilter(new JwtTokenUtil(new JwtProperties()));
//    }

    @Bean(name = "authcFilter")
    public AuthcFilter authcFilter() {
        return new AuthcFilter();
    }

    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver simpleMappingExceptionResolver = new SimpleMappingExceptionResolver();
        simpleMappingExceptionResolver.setDefaultErrorView(properties.getDefaultViewUrl());
        return simpleMappingExceptionResolver;
    }

    /**
     * 实例化获取permission字段参数
     */
    @Bean
    public RequirePermissionController requirePermissionController() {
        return new RequirePermissionController(properties.getPermissionPackage());
    }

    @Bean
    public ExceptionController exceptionController() {
        return new ExceptionController();
    }

    @ConditionalOnProperty(prefix = "shiro", name = "csrf-enabled", havingValue = "true")
    @Bean(name = "csrfFilter")
    public FilterRegistrationBean crsfFilter() {
        if (StringUtils.isEmpty(properties.getCsrfKey())) {
            throw new RuntimeException("you must set shiro.csrf-key value");
        }

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new CsrfFilter(properties));

        Map<String, String> filterChains = new LinkedHashMap<>();
        if (properties.getFilterChainDefinitions() != null) {
            filterChains.putAll(properties.getFilterChainDefinitions());
            //添加authc for getPermissions method
            filterChains.put("/getPermissions", "anon,csrf");
        }
        //需要authc的接口同时需要使用csrf的filter
        if (CollectionUtils.isNotEmpty(filterChains.keySet()) && properties.isCsrfAfterAuthc()) {
            filterChains.keySet().stream().forEach(key -> {
                String value = filterChains.get(key);
                if (value.contains("authc")) {
                    registration.addUrlPatterns(key);
                }
            });
        }
        //无需同步authc的mapping接口则使用自定义的包含Csrf的mapping
        if (CollectionUtils.isNotEmpty(filterChains.keySet()) && !properties.isCsrfAfterAuthc()) {
            filterChains.keySet().stream().forEach(key -> {
                String value = filterChains.get(key);
                if (value.contains("csrf")) {
                    registration.addUrlPatterns(key);
                }
            });
        }


        log.debug("csrfFilter is active ..");
        registration.setName("csrfFilter");
        registration.setOrder(20);
        return registration;
    }

}
