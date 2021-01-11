package cn.xmp.modules.security.shiro;


import cn.xmp.modules.common.enums.ReturnCodeEnum;
import cn.xmp.modules.common.model.response.BaseResponse;
import cn.xmp.modules.common.utils.JsonUtil;
import cn.xmp.modules.system.entity.User;
import cn.xmp.modules.system.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Collection;

/**
 * <p>
 * <p>自定义UserRealm
 *
 * @author xiemopeng
 * @since 2020/11/25
 */
@Slf4j
public class UserRealm extends AuthorizingRealm implements ApplicationContextAware {
    //    @Autowired
//    @Lazy
//    private ISysUserService iSysUserService;
    protected ApplicationContext applicationContext;

    @Override
    public boolean isAuthorizationCachingEnabled() {
        return true;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User) principals.getPrimaryPrincipal();
        IUserService iSysUserService = applicationContext.getBean(IUserService.class);
        iSysUserService.doGetUserAuthorizationInfo(user);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(user.getRoles());
        simpleAuthorizationInfo.setStringPermissions(user.getStringPermissions());
        return simpleAuthorizationInfo;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher("MD5");
//        this.setCredentialsMatcher(hashedCredentialsMatcher);
        System.out.println("---------执行了认证doGetAuthenticationInfo---------");
//        //用户名密码
//        String userName = "admin";
//        String password = "123";
        UsernamePasswordToken passwordToken = (UsernamePasswordToken) token;
        User model = new User();
        model.setUsername(passwordToken.getUsername());
        //从数据库获取用户名密码
        IUserService iUserService = applicationContext.getBean(IUserService.class);
        BaseResponse response = iUserService.queryByName(model);
        log.info("根据用户名查询用户返回: ", JsonUtil.objectToJson(response));
        if (!ReturnCodeEnum.CODE_1000.getCode().equals(response.getReturnCode())) {//用户不存在
            return null;
        } else {
            User sysUser = (User) response.getDataInfo();

            //2.处理一个账号异地登录的问题，后期用户量上来需要做优化，比如登录用cas
            DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
            DefaultWebSessionManager sessionManager = (DefaultWebSessionManager)securityManager.getSessionManager();
            //获取当前已登录的用户session列表
            Collection<Session> sessions = sessionManager.getSessionDAO().getActiveSessions();
            for(Session session:sessions){
                //查找是否有当前登录账户的记录，有就清除该用户以前登录时保存的session
                Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
                if(null!=obj){
                    if(obj instanceof SimplePrincipalCollection){
                        //强转
                        SimplePrincipalCollection spc = (SimplePrincipalCollection)obj;
                        User user = new User();
                        BeanUtils.copyProperties(spc.getPrimaryPrincipal(),user);
                        //判断用户，匹配用户ID。
                        if(sysUser.getUserId().equals(user.getUserId())){
                            sessionManager.getSessionDAO().delete(session);
                        }
                    }
                }
            }

            //密码认证
            return new SimpleAuthenticationInfo(sysUser, sysUser.getPassword(), getName());
        }

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
