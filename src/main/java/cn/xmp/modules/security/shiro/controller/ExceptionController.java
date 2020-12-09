package cn.xmp.modules.security.shiro.controller;

import cn.xmp.modules.security.shiro.constant.AuthConstant;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by kellen on 2017/7/19.
 */
@RestController
@RequestMapping("/")
public class ExceptionController {

  @RequestMapping(AuthConstant.UNAUTHENTICATED_URL)
  public void unauthenticatedException(HttpServletRequest request, HttpServletResponse response) {
    throw new UnauthenticatedException("登录状态已失效");

  }


  @RequestMapping(AuthConstant.UNAUTHORIZED_URL)
  public void unauthorizedException(HttpServletRequest request, HttpServletResponse response) {
    throw new UnauthorizedException("无权限访问");
  }
}
