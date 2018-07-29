package com.ssm.web.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 贾令强
 * @since 2018/6/18 15:29
 */
@Component
public class TimeInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(TimeInterceptor.class);

    /**
     * 进入拦截器之前
     * 返回true继续往下走，返回false将不会进入Controller
     * 拦截器无法获取Controller入参，只能通过aspect获取
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("...TimeInterceptor preHandle");

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        log.info("要访问的Controller为：" + handlerMethod.getBean().getClass().getName() + "，方法为：" + handlerMethod.getMethod().getName());
        request.setAttribute("startTime", System.currentTimeMillis());
        return true;
    }

    /**
     * Controller有异常并不会进入此方法
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("...TimeInterceptor postHandle");

        Long startTime = (Long) request.getAttribute("startTime");
        log.info("...TimeInterceptor postHandle耗时：" + (System.currentTimeMillis() - startTime));
    }

    /**
     * 最终进入此方法，不管Controller是否有异常
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("...TimeInterceptor afterCompletion");

        Long startTime = (Long) request.getAttribute("startTime");
        log.info("...TimeInterceptor afterCompletion 耗时：" + (System.currentTimeMillis() - startTime));
        log.info("ex:" + ex);
    }
}
