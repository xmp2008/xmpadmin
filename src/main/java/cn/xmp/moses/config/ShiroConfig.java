package cn.xmp.moses.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 * <p>
 *
 * @author xiemopeng
 * @since 2020/11/25
 */
@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(securityManager);

        /**
         * Shiro内置过滤器，可以实现权限相关的拦截器
         *    常用的过滤器：
         *       anon: 无需认证（登录）可以访问
         *       authc: 必须认证才可以访问
         *       user: 如果使用rememberMe的功能可以直接访问
         *       perms： 该资源必须得到资源权限才可以访问
         *       role: 该资源必须得到角色权限才可以访问
         */
        Map<String, String> map = new LinkedHashMap<>();
//        map.put("/user/add", "authc");
//        map.put("/user/update", "authc");


        //授权
        map.put("/user/add","perms[user:add]");
        map.put("/user/update","perms[user:update]");

        map.put("/user/*", "authc");

        bean.setFilterChainDefinitionMap(map);
        //设置登录请求
        bean.setLoginUrl("/toLogin");
        //设置未授权页面
        bean.setUnauthorizedUrl("/noauth");
        return bean;
    }

    //DefaultWebSecurityManager
    @Bean("securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //关联realm
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }

    //创建realm对象，需要自定义类
    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }
    //整合thymeleaf
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
}
