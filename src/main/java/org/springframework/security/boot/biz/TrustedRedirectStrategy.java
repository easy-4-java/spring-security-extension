package org.springframework.security.boot.biz;

import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A redirect strategy that only redirects to trusted URLs matching configured Ant-style patterns.
 * If the target URL is not trusted, redirects to a configurable default URL instead.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 3.0.0
 * @see org.springframework.security.web.DefaultRedirectStrategy
 * @see org.springframework.util.AntPathMatcher
 */
public class TrustedRedirectStrategy extends DefaultRedirectStrategy {

    /**
     * The Ant path matcher to use when comparing URLs.
     */
    private AntPathMatcher antpathMatcher = new AntPathMatcher();

    private String defaultRedirectUrl = "/";

    /**
     * The Trusted Redirects that are allowed to redirect to.
     */
    private List<String> trustedRedirects = new ArrayList<>(Arrays.asList("/**"));

    @Override
    public void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
        if (this.isTrustedTarget(url)) {
            super.sendRedirect(request, response, url);
        } else {
            super.sendRedirect(request, response, defaultRedirectUrl);
        }
    }

    private boolean isTrustedTarget(String url) {
        if (CollectionUtils.isEmpty(trustedRedirects)) {
            return true;
        }
        for (String trustedRedirectUrl : trustedRedirects) {
            if (StringUtils.hasText(trustedRedirectUrl) && antpathMatcher.match(trustedRedirectUrl, url)) {
                return true;
            }
        }
        return false;
    }

    public void setTrustedRedirects(List<String> trustedRedirects) {
        this.trustedRedirects = trustedRedirects;
    }

    public void setDefaultRedirectUrl(String defaultRedirectUrl) {
        this.defaultRedirectUrl = defaultRedirectUrl;
    }


}
