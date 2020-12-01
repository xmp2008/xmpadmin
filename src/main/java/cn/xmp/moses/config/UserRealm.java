package cn.xmp.moses.config;


import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>自定义UserRealm
 *
 * @author xiemopeng
 * @since 2020/11/25
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("---------------执行了授权doGetAuthorizationInfo-------");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Map<String, Object> objectMap =(Map<String, Object>) principals.getPrimaryPrincipal();
        authorizationInfo.addStringPermission((String) objectMap.get("create_by"));
        return authorizationInfo;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("---------执行了认证doGetAuthenticationInfo---------");
//        //用户名密码
//        String userName = "admin";
//        String password = "123";
        UsernamePasswordToken passwordToken = (UsernamePasswordToken) token;

        //从数据库获取用户名密码
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from sys_user where username = " + "\"" + passwordToken.getUsername() + "\"");
        System.out.println("maps = " + maps);
//        Map<String, Object> objectMap = maps.get(0);
        if (maps.size() == 0) {//用户不存在
            return null;
        } else {
            Map<String, Object> objectMap = maps.get(0);
            //密码认证
            return new SimpleAuthenticationInfo(objectMap, objectMap.get("password"), "");
        }
//        if (!objectMap.get("username").equals(passwordToken.getUsername())) {//用户不存在
//            return null;
//        }

    }
}
