package org.springframework.security.boot.biz;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.WebSecurityExpressionRoot;
import org.springframework.security.web.util.matcher.IpAddressMatcher;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Objects;

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
