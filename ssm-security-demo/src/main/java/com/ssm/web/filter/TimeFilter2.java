package com.ssm.web.filter;

import com.ssm.web.config.WebConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

/**
 * 不使用@Component注解，使用配置类方式
 * 比@Component注解更灵活，可以配置具体拦截url
 *
 * @author 贾令强
 * @see WebConfig
 * @since 2018/6/18 14:35
 */
//@Component
public class TimeFilter2 implements Filter {
    private static final Logger log = LoggerFactory.getLogger(TimeFilter2.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("...TimeFilter2 filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        log.info("...TimeFilter2 filter doFilter start");
        chain.doFilter(request, response);
        log.info("...TimeFilter2 filter doFilter stop filter 耗时（秒）:" + (System.currentTimeMillis() - start));
    }

    @Override
    public void destroy() {
        log.info("...TimeFilter2 filter destroy");
    }
}
