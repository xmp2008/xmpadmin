package cn.xmp.moses.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * <p>
 *
 * @author xiemopeng
 * @since 2020/11/25
 */
@Controller
public class MyController {
    @RequestMapping({"/", "/index"})
    public String toIndex(Model model) {
        model.addAttribute("msg", "hello shiro");
        return "index";
    }

    @RequestMapping({"/user/add"})
    public String add() {
        return "user/add";
    }

    @RequestMapping({"/user/update"})
    public String update() {
        return "user/update";
    }

    @RequestMapping({"/toLogin"})
    public String toLogin() {
        return "login";
    }

    @RequestMapping({"/login"})
    public String login(String userName, String password, Model model) {
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        //执行登录方法，如果没有异常说明正常登录
        try {
            subject.login(token);
            token.setRememberMe(true);
        } catch (UnknownAccountException e) {//用户不存在
            model.addAttribute("msg", "用户不存在");
            return "login";
        } catch (IncorrectCredentialsException e) {//密码错误
            model.addAttribute("msg", "密码错误");
            return "login";
        }
        return "index";
    }


    @RequestMapping({"/noauth"})
    @ResponseBody
    public String noauth() {
        return "未授权";
    }

}
