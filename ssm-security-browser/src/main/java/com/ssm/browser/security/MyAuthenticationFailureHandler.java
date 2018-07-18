package com.ssm.browser.security;

import com.ssm.security.core.properties.LoginType;
import com.ssm.security.core.properties.SecurityProperties;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败后处理逻辑
 *
 * @author 贾令强
 * @since 2018/6/24 21:33
 */
@Component
public class MyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private static final Logger log = LoggerFactory.getLogger(MyAuthenticationFailureHandler.class);

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.info("登陆失败 MyAuthenticationSuccessHandler,异常信息为:{}", exception.getMessage());

        // 如果是json，返回json，否则是html调用父类处理逻辑
        if (securityProperties.getBrowser().getLoginType().equals(LoginType.JSON)) {
            // 登陆失败后，把用户信息写到响应中
            response.setContentType("application/json;charset=UTF-8");
            String message = exception.getMessage();
            log.error("异常信息为：{}", message);
            response.getWriter().write(objectMapper.writeValueAsString(message));
        } else {
            super.onAuthenticationFailure(request, response, exception);
        }

    }
}
