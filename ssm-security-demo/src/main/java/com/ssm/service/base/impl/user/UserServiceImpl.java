package com.ssm.service.base.impl.user;

import com.ssm.dto.base.user.User;
import com.ssm.mapper.base.user.UserMapper;
import com.ssm.service.base.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 贾令强
 * @since 2018/3/24 21:39
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Cacheable(value = "HelloWorldCache")
    @Override
    public List<User> list() {
        List<User> list = userMapper.list();
        return list;
    }

    @CacheEvict(value = "HelloWorldCache")
    @Override
    public void removeListCache() {
        System.out.println("移除缓存");
    }
}
