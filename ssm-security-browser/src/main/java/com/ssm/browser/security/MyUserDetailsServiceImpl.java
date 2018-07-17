package com.ssm.browser.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author 贾令强
 * @since 2018/6/23 21:51
 */
@Component
public class MyUserDetailsServiceImpl implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;
    // 注入mapper根据此处用户名去数据库查找用户信息

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        logger.info("MyUserDetailsService.loadUserByUsername接受到的参数为：" + name);
        // 此处使用spring自带user对象，实际项目用自定义的，
        // isEnabled true 是否可用（一般用来标志用户是否被删除）
        // isAccountNonExpired true 没有过期
        // isCredentialsNonExpired true 密码没有过期，false过期
        // isAccountNonLocked true 账户没冻结（锁定）
        // 最后一个参数代表权限
        String password = passwordEncoder.encode("123");
        logger.info("数据库密码（加密后的）:" + password);
        return new User(name, password,
                true, true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
