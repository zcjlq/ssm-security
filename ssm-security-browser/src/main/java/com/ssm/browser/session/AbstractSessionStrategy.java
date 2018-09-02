package com.ssm.browser.session;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssm.browser.support.SimpleResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 贾令强
 * @since 2018/8/27 下午9:55
 */
public class AbstractSessionStrategy {

    private static final Logger log = LoggerFactory.getLogger(AbstractSessionStrategy.class);

    private ObjectMapper objectMapper = new ObjectMapper();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private boolean createNewSession = true;

    private String destinationUrl;

    public AbstractSessionStrategy(String invalidSessionUrl) {
//        Assert.isTrue(UrlUtils.isValidRedirectUrl(invalidSessionUrl), "url must start with '/' or with 'http(s)'");
//        Assert.isTrue(StringUtils.endsWithIgnoreCase(invalidSessionUrl, ".html"), "url must end with '.html'");
//        this.destinationUrl = invalidSessionUrl;
        Assert.isTrue(UrlUtils.isValidRedirectUrl(invalidSessionUrl), "url must start with '/' or with 'http(s)'");
        this.destinationUrl = invalidSessionUrl;
    }

    public boolean isConcurrency() {
        return false;
    }

    public void onSessionInvalid(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("session 失效");
        if (createNewSession) {
            request.getSession();
        }

        String sourceUrl = request.getRequestURI();
        String targetUrl;

        if (StringUtils.endsWithIgnoreCase(sourceUrl, ".html")) {
            targetUrl = destinationUrl + ".html";
            log.info("跳转到：" + targetUrl);
            redirectStrategy.sendRedirect(request, response, targetUrl);
        } else {
            Object result = this.buildResponseContent(request);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(result));
        }
    }

    private Object buildResponseContent(HttpServletRequest request) {
        String message = "session失效";
        if (isConcurrency()) {
            message += ",可能是并发登陆导致的";
        }
        return new SimpleResponse(message);
    }

    public void setCreateNewSession(boolean createNewSession) {
        this.createNewSession = createNewSession;
    }
}
