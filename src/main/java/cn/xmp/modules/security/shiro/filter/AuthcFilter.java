package cn.xmp.modules.security.shiro.filter;

import cn.xmp.modules.security.shiro.constant.AuthConstant;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * This guy is lazy, nothing left.
 *
 * @author John
 */
public class AuthcFilter extends FormAuthenticationFilter {

  @Override
  protected void setFailureAttribute(ServletRequest request, AuthenticationException ae) {
    request.setAttribute(getFailureKeyAttribute(), ae);
  }

  @Override
  protected void redirectToLogin(ServletRequest request, ServletResponse response)
      throws IOException {
    WebUtils.issueRedirect(request, response, AuthConstant.UNAUTHENTICATED_URL);
  }
}
