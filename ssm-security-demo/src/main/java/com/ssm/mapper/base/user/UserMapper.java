package com.ssm.mapper.base.user;


import com.ssm.dto.base.user.User;

import java.util.List;

/**
 * @author 贾令强
 * @since 2018/3/24 21:39
 */
public interface UserMapper {

    List<User> list();

    int deleteByPrimaryKey(Integer tid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer tid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User getUserByName(String userName);

}
