package com.ssm.security;

import com.ssm.dto.base.user.User;
import com.ssm.mapper.base.user.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

/**
 * 自定义用户信息获取业务类
 *
 * @author 贾令强
 * @since 2018/6/23 21:51
 */
@Component
public class MyUserDetailsServiceImpl implements UserDetailsService, SocialUserDetailsService {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return this.buildUser(name);
    }

    /**
     * 根据用户id查询用户
     *
     * @param userId
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        return this.buildUser(userId);
    }

    /**
     * isEnabled true 是否可用（一般用来标志用户是否被删除）
     * isAccountNonExpired true 没有过期
     * isCredentialsNonExpired true 密码没有过期，false过期
     * isAccountNonLocked true 账户没冻结（锁定）
     *
     * @param userName
     * @return
     */
    private SocialUserDetails buildUser(String userName) {
        log.info("MyUserDetailsService.loadUserByUsername接受到的参数为：{}", userName);

        // 1.校验用户名是否存在
        User user = userMapper.getUserByName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("无此用户");
        }
        log.info("用户信息为：{}", user.toString());

        // 2.从数据库获取密码
        String password = user.getPassword();

        // 3.校验用户状态 0 禁用 1 启用
        boolean userStatus = user.getStatus() == 1;

        return new SocialUser(userName, password,
                true, true, true, userStatus,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_USER"));
    }


}