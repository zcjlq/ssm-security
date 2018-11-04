package com.ssm.web.service;

import com.ssm.dto.base.user.User;

/**
 * @author 贾令强
 * @since 2018/8/23 21:41
 */
public interface UserDatabaseService {
    User getUser(String id);

    User addUser(String id);
}
