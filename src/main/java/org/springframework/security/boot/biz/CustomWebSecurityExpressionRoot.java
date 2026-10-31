package org.springframework.security.boot.biz;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.expression.WebSecurityExpressionRoot;
import org.springframework.security.web.util.matcher.IpAddressMatcher;
import org.springframework.util.StringUtils;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Custom implementation of {@link WebSecurityExpressionRoot}
 * that provides enhanced IP address matching by using the remote address from the servlet request.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 3.0.0
 * @see WebSecurityExpressionRoot
 * @see IpAddressMatcher
 */
public class CustomWebSecurityExpressionRoot extends WebSecurityExpressionRoot {

    public final HttpServletRequest request;

    public CustomWebSecurityExpressionRoot(Supplier<Authentication> authentication, HttpServletRequest request) {
        super(authentication, request);
        this.request = request;
    }

    @Override
    public boolean hasIpAddress(String ipAddress) {
        String remoteAddr = request.getRemoteAddr();
        if (Objects.isNull(remoteAddr)) {
            return false;
        }
        return (new IpAddressMatcher(ipAddress).matches(remoteAddr));
    }

}
