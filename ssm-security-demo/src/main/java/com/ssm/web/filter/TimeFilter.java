package com.ssm.web.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author 贾令强
 * @since 2018/6/18 14:06
 */
@Component
public class TimeFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(TimeFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        log.info("...TimeFilter filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
//        log.info("...TimeFilter filter doFilter start");
        chain.doFilter(request, response);
//        log.info("...TimeFilter filter doFilter stop filter 耗时（秒）:" + (System.currentTimeMillis() - start));
    }

    @Override
    public void destroy() {
//        log.info("...TimeFilter filter destroy");
    }
}
