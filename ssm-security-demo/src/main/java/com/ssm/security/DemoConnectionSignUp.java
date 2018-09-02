package com.ssm.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;

/**
 * @author 贾令强
 * {@link JdbcUsersConnectionRepository} 84
 * @since 2018/7/29 12:45
 */
//@Component
public class DemoConnectionSignUp implements ConnectionSignUp {
    private static final Logger log = LoggerFactory.getLogger(DemoConnectionSignUp.class);

    /**
     * 根据connection信息默认创建用户，并返回用户唯一标志
     *
     * @param connection
     * @return
     */
    @Override
    public String execute(Connection<?> connection) {
        String displayName = connection.getDisplayName();
        log.info("用户唯一标志{}" + displayName);
        return displayName;
    }
}
