package org.springframework.security.boot.biz;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.WebSecurityExpressionRoot;
import org.springframework.security.web.util.matcher.IpAddressMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * Custom implementation of {@link org.springframework.security.web.access.expression.WebSecurityExpressionRoot}
 * that provides enhanced IP address matching by using the remote address from the servlet request.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 3.0.0
 * @see org.springframework.security.web.access.expression.WebSecurityExpressionRoot
 * @see org.springframework.security.web.util.matcher.IpAddressMatcher
 */
public class CustomWebSecurityExpressionRoot  extends WebSecurityExpressionRoot {

    public final HttpServletRequest request;

    public CustomWebSecurityExpressionRoot(Authentication a, FilterInvocation fi) {
        super(a, fi);
        this.request = fi.getRequest();
    }

    @Override
    public boolean hasIpAddress(String ipAddress) {
        String remoteAddr = request.getRemoteAddr();
        if (remoteAddr == null) {
            remoteAddr = "";
        }
        return (new IpAddressMatcher(ipAddress).matches(remoteAddr));
    }

}
