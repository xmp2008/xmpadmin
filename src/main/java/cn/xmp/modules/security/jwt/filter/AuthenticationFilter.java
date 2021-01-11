package cn.xmp.modules.security.jwt.filter;

import cn.xmp.modules.common.enums.ReturnCodeEnum;
import cn.xmp.modules.common.model.response.BaseResponse;
import cn.xmp.modules.common.utils.JsonUtil;
import cn.xmp.modules.security.jwt.constant.AuthConst;
import cn.xmp.modules.security.jwt.properties.JwtProperties;
import cn.xmp.modules.security.jwt.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * filter自动注入方式有两种：
 * 1.实现FilterRegistrationBean接口，自动配置
 * 2.在 SpringBootApplication 上使用@ServletComponentScan 注解后,在filter类上加@WebFilter注解自动注入
 * @author wangliang
 * @since 2017/12/20
 */
@Slf4j
//@WebFilter(filterName = "AuthenticationFilter", urlPatterns = "/*")
public class AuthenticationFilter implements Filter {

    private FilterConfig config;

    private JwtTokenUtil jwtTokenUtil;

    private PathMatcher pathMatcher;

    private JwtProperties jwtProperties;

    /**
     * 需要放行的url list
     */
    private static List<String> requestUriList;
    /**
     * 需要拦截的uri list
     */
    private static List<String> iterceptUriList;


    public AuthenticationFilter(JwtTokenUtil jwtTokenUtil, ServerProperties serverProperties,JwtProperties jwtProperties) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.pathMatcher = new AntPathMatcher();
        this.jwtProperties = new JwtProperties();

        String contextPath = "";
        if (serverProperties != null && StringUtils.isNotBlank(serverProperties.getServlet().getContextPath())) {
            contextPath = serverProperties.getServlet().getContextPath();
        }

        //自定义配置请求放行
        requestUriList = jwtTokenUtil.getJwtProperties().getRequestUris();
        if (CollectionUtils.isNotEmpty(requestUriList) && StringUtils.isNotBlank(contextPath)) {
            String finalContextPath = contextPath;
            requestUriList = requestUriList.stream().map(pattern -> finalContextPath + pattern).collect(Collectors.toList());
        }
//        requestUriList.add(contextPath + "/admin/**");
        requestUriList.add(contextPath + "/unauthenticated*");
        requestUriList.add(contextPath + "/unauthorized*");
        requestUriList.add(contextPath + "/health");
        requestUriList.add(contextPath + "/common/refreshToken");
    }

    @Override
    public void destroy() {
    }

    /**
     * 验证token
     * @param req
     * @param res
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;

        String path = request.getRequestURI();
        log.info("RequestURI is : {}", path);

        // 放行所有options请求
        if (RequestMethod.OPTIONS.name().equals(request.getMethod())) {
            chain.doFilter(req, res);
            return;
        }

        //登录、刷新令牌、验证令牌请求放行
        if (AuthConst.LOGIN_URL.equals(path) || AuthConst.VALIDATE_TOKEN.equals(path) || AuthConst.REFRESH_TOKEN.equals(path)) {
            chain.doFilter(req, res);
            return;
        }

        //自定义配置请求放行
        if (CollectionUtils.isNotEmpty(requestUriList)) {
            for (String pattern : requestUriList) {
                if (pathMatcher.match(pattern, path)) {
                    chain.doFilter(req, res);
                    return;
                }
            }
        }

        //验证token，有效则放行
        String token = request.getHeader(jwtProperties.getHeader());
        if (token == null) {
            //验证失败
            AuthenticationFailure(req, res);
            return;
        }

        if (!jwtTokenUtil.validateToken(token, null)) {
            log.warn("token is invalid, uri: {}, token: {}", path, token);
            AuthenticationFailure(req, res);
            return;
        }

        chain.doFilter(req, res);
        return;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        config = filterConfig;
    }

    /**
     * 验证失败返回错误信息
     * @param request
     * @param response
     * @throws IOException
     */
    protected void AuthenticationFailure(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
//        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpResponse.setStatus(HttpServletResponse.SC_OK);
        httpResponse.setContentType("application/json;charset=UTF-8");

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setReturnCode(ReturnCodeEnum.CODE_1009.getCode());
        baseResponse.setMessage("token is invalid!");

        httpResponse.getWriter().write(JsonUtil.objectToJson(baseResponse));
    }

}
