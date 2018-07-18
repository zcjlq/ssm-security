package com.ssm.browser.security;

import com.ssm.security.core.properties.LoginType;
import com.ssm.security.core.properties.SecurityProperties;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义登陆成功处理
 *
 * @author 贾令强
 * @since 2018/6/24 21:11
 */
// 由 AuthenticationSuccessHandler 改为继承 SavedRequestAwareAuthenticationSuccessHandler spring 默认的成功处理器
@Component
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private static final Logger log = LoggerFactory.getLogger(MyAuthenticationSuccessHandler.class);

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 用户登录成功到进此方法
     *
     * @param request
     * @param response
     * @param authentication 封装认证信息
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        log.info("登陆成功 MyAuthenticationSuccessHandler:", ReflectionToStringBuilder.reflectionToString(authentication));

        // 如果是json，返回json，否则是html调用父类处理逻辑
        if (securityProperties.getBrowser().getLoginType().equals(LoginType.JSON)) {
            // 登陆成功后，把用户信息写到响应中
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(authentication));
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
