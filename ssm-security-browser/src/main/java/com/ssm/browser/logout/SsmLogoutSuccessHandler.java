package com.ssm.browser.logout;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssm.security.core.properties.SecurityProperties;
import com.ssm.security.core.support.SimpleResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 贾令强
 * @since 2018/9/2 下午12:53
 */
public class SsmLogoutSuccessHandler implements LogoutSuccessHandler {
    private static final Logger log = LoggerFactory.getLogger(SsmLogoutSuccessHandler.class);
    private SecurityProperties securityProperties;
    private ObjectMapper objectMapper = new ObjectMapper();


    public SsmLogoutSuccessHandler(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("退出成功");
        String signOutUrl = securityProperties.getBrowser().getSignOutUrl();
        if (StringUtils.isBlank(signOutUrl)) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse("退出成功")));
        } else {
            response.sendRedirect(signOutUrl);
        }
    }
}
