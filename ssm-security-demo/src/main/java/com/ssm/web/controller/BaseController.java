package com.ssm.web.controller;

import com.ssm.dto.base.log.OperLog;
import com.ssm.dto.base.user.User;
import com.ssm.mapper.base.log.OperLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.ObjectError;

import java.util.Date;
import java.util.List;

/**
 * @author 贾令强
 * @since 2018/10/23 2:28 PM
 */
public class BaseController {

    protected static final Logger log = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private OperLogMapper operLogMapper;

    protected User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        log.info(principal.toString());
        return null;
    }

    protected String getUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        log.info(principal.toString());
        return null;
    }

    protected String validatorErrors2String(List<ObjectError> allErrors) {
        StringBuilder sb = new StringBuilder();
        allErrors.forEach(error -> sb.append(error.getDefaultMessage()).append(";"));
        log.info("Bean Validator校验结果为:{}", sb.toString());
        return sb.toString();
    }

    protected void addLog(long start, String menu, String operType, String remark) {
        OperLog operLog = new OperLog();
        operLog.setModuleName(menu);
        operLog.setOperType(operType);
//        operLog.setController();
//        operLog.setMethod();
//        operLog.setOperUser();
//        operLog.setIp();
        operLog.setEndTime(new Date());
        operLog.setRemark(remark);
        operLog.setUseTime((System.currentTimeMillis() - start) / 1000.00 + "秒");
        operLogMapper.insertSelective(operLog);
    }
}
