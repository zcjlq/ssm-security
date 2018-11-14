package com.ssm.service.base.user;


import com.ssm.dto.base.user.User;

import java.util.List;

/**
 * @author 贾令强
 * @since 2018/3/24 21:38
 */
public interface UserService {

    List<User> list();

    void removeListCache();
}
