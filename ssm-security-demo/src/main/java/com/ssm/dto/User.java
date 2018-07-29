package com.ssm.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.ssm.validate.MyConstraint;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Past;
import java.util.Collection;
import java.util.Date;

/**
 * @author 贾令强
 * @since 2018/6/17 21:37
 */
public class User implements UserDetails {

    @JsonView(value = UserSimpleView.class)
    private Integer userId;

    @JsonView(value = UserSimpleView.class)
    @MyConstraint(message = "自定义校验没有通过")
    private String username;

    @JsonView(value = UserDetailView.class)
    @NotBlank(message = "密码不能为空")
    private String password;

    @JsonView(value = UserSimpleView.class)
    @Past(message = "生日只能是过去日期")
    private Date birthday;

    public interface UserSimpleView {
    }

    public interface UserDetailView extends UserSimpleView {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void setUsername(String username) {
        this.username = username.trim();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
