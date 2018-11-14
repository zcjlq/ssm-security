package com.ssm.web.interceptors;

import com.ssm.dto.base.log.OperLog;
import com.ssm.service.base.log.OperLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 系统自动记录日志
 *
 * @author 贾令强
 * @since 2018/10/22 4:01 PM
 */
@Component
public class OperLogInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(OperLogInterceptor.class);

    @Autowired
    private OperLogService operLogService;

    /**
     * 记录开始时间
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("startTime", System.currentTimeMillis());
        return true;
    }

    /**
     * 有异常不会进入
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Long startTime = (Long) request.getAttribute("startTime");
        log.info("...TimeInterceptor postHandle耗时：" + (System.currentTimeMillis() - startTime));
    }

    /**
     * 最终进入
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Long startTime = (Long) request.getAttribute("startTime");
        String userName = this.getUserName();

        OperLog operLog = new OperLog();
        operLog.setOperUser(userName);
//        operLog.setOperUserTid();
        operLog.setLogType("1");
        operLog.setStartTime(new Date(startTime));
        operLog.setEndTime(new Date(System.currentTimeMillis()));
        double useTime = (System.currentTimeMillis() - startTime) / 1000.00;
        operLog.setUseTime(useTime + "秒");
        operLog.setIp(this.getIp());

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            operLog.setController(handlerMethod.getBean().getClass().getSimpleName());
            operLog.setMethod(handlerMethod.getMethod().getName());
            operLog.setRemark("");
            int i = operLogService.saveLog(operLog);
            if (i > 0) {
                log.info("操作日志拦截器，记录日志成功，插入数量[{}]", i);
            } else {
                log.info("操作日志拦截器，记录日志失败");
            }
        } else {
            log.info("没有访问Controller");
        }
    }

    private String getUserName() {
        SecurityContext context = SecurityContextHolder.getContext();
        Assert.notNull(context, "获取[SecurityContext]信息失败");
        Authentication authentication = context.getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof User) {
                User user = (User) principal;
                return user.getUsername();
            }
        }
        return "未登录";
    }

    private String getIp() {
        SecurityContext context = SecurityContextHolder.getContext();
        Assert.notNull(context, "获取[SecurityContext]信息失败");
        Authentication authentication = context.getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getDetails();
            if (principal instanceof WebAuthenticationDetails) {
                return ((WebAuthenticationDetails) principal).getRemoteAddress();
//                if ("127.0.0.1".equals(remoteAddress) || remoteAddress.startsWith("0.0.0")) {
//                    try {
//                        return IPUtil.getIp();
//                    } catch (SocketException e) {
//                        return "1";
//                    }
//                }
            }

        }
        return "0";
    }
}
