package cn.xmp.modules.security.shiro.filter;

import cn.xmp.modules.security.shiro.xss.MHttpServletRequest;
import org.apache.shiro.web.servlet.AbstractFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by  vpclub on 16-10-19.
 * PackageName cn.vpclub.spring.boot.shiro.autoconfigure.xss
 * ModifyDate  16-10-19
 * Description (非法字符过滤器，用来处理request.getParamater中的非法字符。如<script>alert('123');</script>)
 * ProjectName spring-boot-starters
 */
public class IllegalCharacterFilter extends AbstractFilter {
    private static Logger logger = LoggerFactory.getLogger(IllegalCharacterFilter.class);

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)req;
        logger.debug("RemoteHost is "+ request.getRemoteHost());
        request = new MHttpServletRequest(request);
        chain.doFilter(request, res);
    }
    @Override
    public void destroy() {

    }

}