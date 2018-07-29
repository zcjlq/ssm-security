package com.ssm.browser.security;

import com.ssm.browser.support.SimpleResponse;
import com.ssm.browser.support.SocialUserInfo;
import com.ssm.security.core.properties.SecurityConstants;
import com.ssm.security.core.properties.SecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * TODO
 *
 * @author 贾令强
 * @since 2018/6/23 22:36
 */
@RestController
public class BrowserSecurityController {

    private static final Logger log = LoggerFactory.getLogger(BrowserSecurityController.class);

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    // 缓存了之前的请求
    private RequestCache requestCache = new HttpSessionRequestCache();

    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @RequestMapping(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String redirectUrl = savedRequest.getRedirectUrl();
            log.info("引发跳转的html为：" + redirectUrl);
            if (StringUtils.endsWithIgnoreCase(redirectUrl, ".html")) {
                redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());

            }
        }
        return new SimpleResponse("访问页面需要身份认证，请引导用户到认证页");
    }

    @GetMapping("/social/user")
    public SocialUserInfo getSocialUserInfo(HttpServletRequest request) {
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
        SocialUserInfo userInfo = new SocialUserInfo();
        userInfo.setProvideId(connection.getKey().getProviderId());
        userInfo.setNickName(connection.getDisplayName());
        userInfo.setProvideUserId(connection.getKey().getProviderUserId());
        userInfo.setHeadimg(connection.getImageUrl());
        return userInfo;
    }

}
