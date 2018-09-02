package com.ssm.web.service.impl;

import com.ssm.generator.model.User;
import com.ssm.mapper.UserMapper;
import com.ssm.web.service.UserDatabaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 贾令强
 * @since 2018/8/23 21:41
 */
@Service
//@Transactional(propagation = Propagation.REQUIRES_NEW)
public class UserDatabaseServiceImpl implements UserDatabaseService {

    private static final Logger log = LoggerFactory.getLogger(UserDatabaseServiceImpl.class);

    @Autowired
    private UserMapper userMapper;


    @Override
    public User getUser(String id) {
        // 默认PROPAGATION_REQUIRED,ISOLATION_DEFAULT
        User user = userMapper.selectByPrimaryKey(Integer.valueOf(id));
        log.info("从数据库查到的用户信息为：" + user.toString());
        return user;
    }

    //    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public User addUser(String id) {
        // 默认PROPAGATION_REQUIRED,ISOLATION_DEFAULT
//        User user = userMapper.selectByPrimaryKey(Integer.valueOf(id));
        User user1 = new User();
        user1.setId(55);
        user1.setName("111111");
        userMapper.insertSelective(user1);
        User user = userMapper.selectByPrimaryKey(55);
        log.info("add 从数据库查到的用户信息为：" + user.toString());
        return user;
    }
}
