package com.ssm.web.controller;

import com.ssm.dto.base.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author 贾令强
 * @since 2018/10/23 2:28 PM
 */
public class BaseController {

    public static final Logger log = LoggerFactory.getLogger(BaseController.class);

    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        log.info(principal.toString());
        return null;
    }
}
