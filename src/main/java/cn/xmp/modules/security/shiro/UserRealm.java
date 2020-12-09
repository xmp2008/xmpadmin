package cn.xmp.modules.security.shiro;


import cn.xmp.modules.system.entity.SysUser;
import cn.xmp.modules.common.enums.ReturnCodeEnum;
import cn.xmp.modules.common.model.response.BaseResponse;
import cn.xmp.modules.system.service.ISysUserService;
import cn.xmp.modules.security.shiro.realm.DefaultRealm;
import cn.xmp.modules.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

/**
 * <p>
 * <p>自定义UserRealm
 *
 * @author xiemopeng
 * @since 2020/11/25
 */
@Slf4j
public class UserRealm extends DefaultRealm {
    @Autowired
    @Lazy
    private ISysUserService iSysUserService;

    @Override
    public boolean isAuthorizationCachingEnabled() {
        return true;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SysUser user = (SysUser) principals.getPrimaryPrincipal();
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
        SysUser model = new SysUser();
        model.setUsername(passwordToken.getUsername());
        //从数据库获取用户名密码
        BaseResponse response = iSysUserService.queryByName(model);
        log.info("根据用户名查询用户返回: ", JsonUtil.objectToJson(response));
        if (!ReturnCodeEnum.CODE_1000.getCode().equals(response.getReturnCode())) {//用户不存在
            return null;
        } else {
            SysUser sysUser = (SysUser) response.getDataInfo();
            //密码认证
            return new SimpleAuthenticationInfo(sysUser, sysUser.getPassword(), "");
        }

    }

}
