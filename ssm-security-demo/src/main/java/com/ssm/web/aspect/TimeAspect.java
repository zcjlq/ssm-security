package com.ssm.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author 贾令强
 * @since 2018/6/18 16:04
 */
@Aspect
@Component
public class TimeAspect {
    private static final Logger log = LoggerFactory.getLogger(TimeAspect.class);

    @Around("execution(* com.ssm.web.controller.base.user.UserController.*(..))")
    public Object timeAspect(ProceedingJoinPoint pjp) throws Throwable {
        log.info("...进入time aspect");
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            log.info("time aspect 参数为：" + arg);
        }
        return pjp.proceed();
    }
}
