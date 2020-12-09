package cn.xmp.modules.security.shiro.xss;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.Serializable;

/**
 * Created by  vpclub on 16-10-19.
 * PackageName cn.vpclub.spring.boot.shiro.autoconfigure.xss
 * ModifyDate  16-10-19
 * Description (参数特殊字符过滤)
 * ProjectName spring-boot-starters
 */
public class MHttpServletRequest extends HttpServletRequestWrapper implements Serializable{

    public MHttpServletRequest(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        // 返回值之前 先进行过滤
        System.out.println("name = [ 过滤 参数 " + name + "]");
        return XssShieldUtil.stripXss(super.getParameter(XssShieldUtil.stripXss(name)));
    }

    @Override
    public String[] getParameterValues(String name) {
        // 返回值之前 先进行过滤
        String[] values = super.getParameterValues(XssShieldUtil.stripXss(name));
        if(values != null){
            for (int i = 0; i < values.length; i++) {
                values[i] = XssShieldUtil.stripXss(values[i]);
            }
        }
        return values;
    }

}