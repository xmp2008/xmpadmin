package cn.xmp.modules.security.shiro.filter;

import cn.xmp.modules.security.shiro.constant.AuthConstant;
import cn.xmp.modules.security.shiro.properties.ShiroProperties;
import cn.xmp.modules.security.shiro.util.AES;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by chenwei on 2018/4/28.
 */
@Slf4j
@EnableConfigurationProperties({ShiroProperties.class})
@AllArgsConstructor
public class CsrfFilter implements Filter {

    private ShiroProperties shiroProperties;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;

        String token = null;

        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(req, res);
        } else {
            String auth = request.getHeader("csrfToken");
            log.debug("csrfToken is : {}", auth);
            if (auth != null) {
                try {
                    token = AES.aesDecrypt(auth, shiroProperties.getCsrfKey());
                    if (null != token) {
                        Long requestTime = Long.parseLong(token);
                        long timeMillis = System.currentTimeMillis();
                        long check = timeMillis - requestTime;
                        if (check > shiroProperties.getCsrfExpiredTime()) {
                            log.debug("check is :{}",check);
                            redirectToExpired(request, response); //token过期
                        } else {
                            chain.doFilter(req, res);
                        }

                    } else {
                        redirectToLogin(request, response); //6、token 非法
                    }


                } catch (Exception e) {
                    redirectToLogin(request, response); //6、token 非法
                }
                log.debug("csrfToken Decrypt :{}", token);

            } else {
                redirectToLogin(request, response); //6、token 非法
            }

        }
    }

    @Override
    public void destroy() {

    }



    //token过期
    private void redirectToAnauthorized(ServletRequest request, ServletResponse response)
            throws IOException {
        log.error("csrfToken is expired  ");
        WebUtils.issueRedirect(request, response, AuthConstant.UNAUTHORIZED_URL);
    }
    //token 非法
    private void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpResponse.getWriter().write("{\n" +
                "  \"returnCode\": \"1009\",\n" +
                "  \"message\": \"csrfToken is invalid\",\n" +
                "  \"dataInfo\": null\n" +
                "}");
    }
    //token过期
    protected void redirectToExpired(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpResponse.getWriter().write("{\n" +
                "  \"returnCode\": \"1009\",\n" +
                "  \"message\": \"csrfToken is expired\",\n" +
                "  \"dataInfo\": null\n" +
                "}");
    }
}
