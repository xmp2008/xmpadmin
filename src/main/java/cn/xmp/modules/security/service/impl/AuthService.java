package cn.xmp.modules.security.service.impl;


import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.xmp.modules.common.enums.ReturnCodeEnum;
import cn.xmp.modules.common.model.response.BackResponseUtil;
import cn.xmp.modules.common.model.response.BaseResponse;
import cn.xmp.modules.common.utils.JsonUtil;
import cn.xmp.modules.common.utils.RedisUtils;
import cn.xmp.modules.common.utils.RsaUtils;
import cn.xmp.modules.security.config.LoginProperties;
import cn.xmp.modules.security.config.RsaProperties;
import cn.xmp.modules.security.entity.AuthUserDto;
import cn.xmp.modules.security.jwt.constant.TokenType;
import cn.xmp.modules.security.jwt.utils.JwtTokenUtil;
import cn.xmp.modules.security.shiro.properties.ShiroProperties;
import com.wf.captcha.base.Captcha;
import io.undertow.util.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author xiemopeng
 * 登录 Service
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final RedisUtils redisUtils;
    @Resource
    private LoginProperties loginProperties;
    @Resource
    private ShiroProperties shiroProperties;
    @Resource
    private JwtTokenUtil jwtTokenUtil;
    /**
     * 获取验证码
     *
     * @param
     * @return
     * @throws Exception
     */
    public BaseResponse getCode() {
        // 获取运算的结果
        Captcha captcha = loginProperties.getCaptcha();
        String uuid = loginProperties.getCodeKey() + IdUtil.simpleUUID();
        // 保存
        redisUtils.set(uuid, captcha.text(), loginProperties.getLoginCode().getExpiration(), TimeUnit.MINUTES);
        // 验证码信息
        Map<String, Object> imgResult = new HashMap<String, Object>(2) {{
            put("img", captcha.toBase64());
            put("uuid", uuid);
        }};
        BaseResponse baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1000.getCode());
        baseResponse.setDataInfo(imgResult);
        return baseResponse;
    }

    /**
     * 登录
     *
     * @param authUser
     * @return
     * @throws Exception
     */
    public BaseResponse login(AuthUserDto authUser) throws Exception {
        BaseResponse response = new BaseResponse();
        String s = RsaUtils.encryptByPublicKey("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCGryvwNk7uTHMz97ptqYPn8m+dcRFPlzXtXTp3qUV+vBsa6Bl5Zm/zkFiVswsmTMuih4shgC2lOL0q81SPbA5ZuIOjuXGgLgbQIQ3iNaERFUWhsZLFa8W+xJBj/oCF2jrj3NkTcrTNUdI01WaT1G5l4tIocIeTp9aUC6PN8hNPWwIDAQAB", "123456");
        System.out.println("s = " + s);
        // 密码解密
        String password = RsaUtils.decryptByPrivateKey(RsaProperties.privateKey, authUser.getPassword());
        // 查询验证码
        String code = (String) redisUtils.get(authUser.getUuid());
        // 清除验证码
//        redisUtils.del(authUser.getUuid());
        if (StrUtil.isBlank(code)) {
            throw new BadRequestException("验证码不存在或已过期");
        }
        if (StrUtil.isBlank(authUser.getCode()) || !authUser.getCode().equalsIgnoreCase(code)) {
            throw new BadRequestException("验证码错误");
        }

        if (shiroProperties.isShiroEnabled()) {
            //获取当前用户
            Subject subject = SecurityUtils.getSubject();
            //封装用户登录数据
            UsernamePasswordToken token = new UsernamePasswordToken(authUser.getUsername(), password);
            //执行登录方法，如果没有异常说明正常登录
            subject.login(token);
            token.setRememberMe(true);
        } else {
            log.warn("未启用Shiro");
        }
        //使用jwt生成accessToken
        log.info("生成token账号信息: {}", JsonUtil.objectToJson(authUser));
//        UserDetails userDetails = new UserDetails(mobile);
        authUser.setDeviceId(authUser.getDeviceId());
        authUser.setDeviceType(authUser.getDeviceType());
        authUser.setLoginUsername(authUser.getAccount());
//        authUser.setUserId(null != loginVerifyResponse.getUser() ? loginVerifyResponse.getUser().getId() : null);
        String accessToken = jwtTokenUtil.generateToken(authUser, TokenType.ACCESS_TOKEN);
        response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1000.getCode());
        response.setDataInfo(accessToken);
//        UsernamePasswordAuthenticationToken authenticationToken =
//                new UsernamePasswordAuthenticationToken(authUser.getUsername(), password);
//        AuthenticationManager object = authenticationManagerBuilder.getObject();
//        Authentication authentication = object.authenticate(authenticationToken);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        // 生成令牌
//        String token = tokenProvider.createToken(authentication);
//        final JwtUserDto jwtUserDto = (JwtUserDto) authentication.getPrincipal();
//        // 保存在线信息
//        onlineUserService.save(jwtUserDto, token, request);
//        // 返回 token 与 用户信息
//        Map<String, Object> authInfo = new HashMap<String, Object>(2) {{
//            put("token", properties.getTokenStartWith() + token);
//            put("user", jwtUserDto);
//        }};
//        if (loginProperties.isSingleLogin()) {
//            //踢掉之前已经登录的token
//            onlineUserService.checkLoginOnUser(authUser.getUsername(), token);
//        }

        return response;
    }

}
