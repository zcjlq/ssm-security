package com.ssm.dto.base.user;

import com.fasterxml.jackson.annotation.JsonView;
import com.ssm.validate.MyConstraint;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * @author 贾令强
 * @since 2018/3/25 21:21
 */
public class User implements UserDetails, Serializable {

    private static final long serialVersionUID = 6573820289166353158L;

    private Integer tid;

    @JsonView(value = UserSimpleView.class)
    private Integer userId;

    @JsonView(value = UserSimpleView.class)
    @MyConstraint(message = "自定义校验没有通过")
    private String username;

    private String realName;

    @JsonView(value = UserDetailView.class)
    @NotBlank(message = "密码不能为空")
    private String password;

    private String sex;

    private Integer errorCount;

    private String createUser;

    private String remark;

    private Integer status;

    @JsonView(value = UserSimpleView.class)
    @Past(message = "生日只能是过去日期")
    private Date birthday;

    private Date createTime;

    private Date updateTime;


    public User() {
    }

    public User(Integer tid, String username, String password, String sex) {
        this.tid = tid;
        this.username = username;
        this.password = password;
        this.sex = sex;
    }


    public interface UserSimpleView {
    }

    public interface UserDetailView extends UserSimpleView {
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(Integer errorCount) {
        this.errorCount = errorCount;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
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
        return true;
    }
}
