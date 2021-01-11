//package cn.xmp.modules.security.shiro.filter;
//
//import cn.xmp.modules.common.enums.ReturnCodeEnum;
//import cn.xmp.modules.common.model.response.BaseResponse;
//import cn.xmp.modules.common.utils.JsonUtil;
//import cn.xmp.modules.security.jwt.constant.AuthConst;
//import cn.xmp.modules.security.jwt.utils.JwtTokenUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.shiro.web.servlet.AbstractFilter;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.util.PathMatcher;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
///**
// * jwt过滤器，校验token是否失效
// */
//@Slf4j
//public class JwtFilter extends AbstractFilter {
//    private static Logger logger = LoggerFactory.getLogger(JwtFilter.class);
//
//    private JwtTokenUtil jwtTokenUtil;
//    private PathMatcher pathMatcher;
//
//    /**
//     * 需要放行的url list
//     */
//    private static List<String> requestUriList;
//
//    public JwtFilter(JwtTokenUtil jwtTokenUtil) {
//        this.jwtTokenUtil = jwtTokenUtil;
//////        this.pathMatcher = new AntPathMatcher();
////
////        String contextPath = "";
////        if (serverProperties != null && StringUtils.isNotBlank(serverProperties.getServlet().getContextPath())) {
////            contextPath = serverProperties.getServlet().getContextPath();
////        }
////
////        //自定义配置请求放行
////        requestUriList = jwtTokenUtil.getJwtProperties().getRequestUris();
////        if (CollectionUtils.isNotEmpty(requestUriList) && StringUtils.isNotBlank(contextPath)) {
////            String finalContextPath = contextPath;
////            requestUriList = requestUriList.stream().map(pattern -> finalContextPath + pattern).collect(Collectors.toList());
////        }
////        requestUriList.add(contextPath + "/admin/**");
////        requestUriList.add(contextPath + "/unauthenticated*");
////        requestUriList.add(contextPath + "/unauthorized*");
////        requestUriList.add(contextPath + "/health");
////        requestUriList.add(contextPath + "/common/refreshToken");
//    }
//
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse res,
//                         FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) req;
//        String path = request.getRequestURI();
//        log.info("RequestURI is : {}", path);
//        //验证token，有效则放行
//        String token = request.getHeader(AuthConst.TOKEN);
//        if (token == null) {
//            //验证失败
//            AuthenticationFailure(req, res);
//            return;
//        }
//
//        if (!jwtTokenUtil.validateToken(token, null)) {
//            log.warn("token is invalid, uri: {}, token: {}", path, token);
//            AuthenticationFailure(req, res);
//            return;
//        }
//        chain.doFilter(request, res);
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//
//    /**
//     * 验证失败返回错误信息
//     *
//     * @param request
//     * @param response
//     * @throws IOException
//     */
//    protected void AuthenticationFailure(ServletRequest request, ServletResponse response) throws IOException {
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
////        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        httpResponse.setStatus(HttpServletResponse.SC_OK);
//        httpResponse.setContentType("application/json;charset=UTF-8");
//
//        BaseResponse baseResponse = new BaseResponse();
//        baseResponse.setReturnCode(ReturnCodeEnum.CODE_1009.getCode());
//        baseResponse.setMessage("token is invalid!");
//
//        httpResponse.getWriter().write(JsonUtil.objectToJson(baseResponse));
//    }
//}