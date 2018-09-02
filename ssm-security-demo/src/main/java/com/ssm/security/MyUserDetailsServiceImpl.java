package com.ssm.security;

import com.ssm.generator.model.User;
import com.ssm.mapper.UserMapper;
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

    // 注入mapper 根据此处用户名去数据库查找用户信息
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return this.buildUser(name, true);
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
        return this.buildUser(userId, false);
    }

    private SocialUserDetails buildUser(String userName, boolean b) {
        log.info("MyUserDetailsService.loadUserByUsername接受到的参数为：{}", userName);

        // 表单登陆时，userName代表用户名，社交登陆时，代表用户id
        if (b) {
            User user = userMapper.getUserByName(userName);
            log.info("从数据库查到的用户信息" + user.toString());
        }

        // 此处使用spring自带user对象，实际项目用自定义的，
        // isEnabled true 是否可用（一般用来标志用户是否被删除）
        // isAccountNonExpired true 没有过期
        // isCredentialsNonExpired true 密码没有过期，false过期
        // isAccountNonLocked true 账户没冻结（锁定）

        // 最后一个参数代表权限
        String password = passwordEncoder.encode("123");
        log.info("数据库密码（加密后的）:" + password);
        return new SocialUser(userName, password,
                true, true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }


}