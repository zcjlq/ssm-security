package com.ssm.web.controller.base.user;

import com.fasterxml.jackson.annotation.JsonView;
import com.ssm.dto.UserQueryCondition;
import com.ssm.dto.base.user.User;
import com.ssm.security.core.properties.SecurityProperties;
import com.ssm.service.base.user.UserService;
import com.ssm.web.exception.UserNotFoundException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Rest基本增删改查
 *
 * @author 贾令强
 * @since 2018/6/17 18:18
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(User user, HttpServletRequest request) {
        log.info("用户注册：{}", user);
        String userId = user.getUsername();
        providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
        return "注册成功";
    }

    @GetMapping("/me")
    public Object getCurrentUser(Authentication user) {
        log.info("controller+/user/me,用户信息为：{}", user);
        return user;
    }

    @GetMapping("/me1")
    public Object getCurrentUserJwt(Authentication user, HttpServletRequest request) {
        log.info("controller+/user/me,用户信息为：{}", user);
        String header = request.getHeader("Authorization");
        String token = StringUtils.substringAfter(header, "bearer ");

        Claims claims = Jwts.parser()
                .setSigningKey(securityProperties.getOauth2().getJwtSigningKey().getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token).getBody();
        String company = (String) claims.get("company");
        log.info("->> jwt company :" + company);

        return user;
    }


    /**
     * @param queryCondition
     * @param pageable
     * @return
     * @JsonView 返回实体json包含字段，依赖于实体配置
     * @ApiOperation swagger相关注解，方法相关说明，如果不写，默认展示方法名称
     * @PageableDefault(size = 4, page = 2, sort = "age:desc") 接收分页参数
     */
    @GetMapping
    @JsonView(User.UserSimpleView.class)
//    @ApiOperation("查询用户信息")
    public List<User> queryUser(UserQueryCondition queryCondition,
                                @PageableDefault(size = 4, page = 2, sort = "age:desc") Pageable pageable) {
        // 通过反射将实体toString
        log.info(ReflectionToStringBuilder.toString(queryCondition, ToStringStyle.MULTI_LINE_STYLE));
        log.info(ReflectionToStringBuilder.toString(pageable, ToStringStyle.MULTI_LINE_STYLE));

        User user = new User();
        user.setPassword("22222");
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }

    /**
     * "/{id:\\d+}" 传入值正则校验，如果不满足，返回400
     *
     * @param id
     * @return
     * @ApiParam("传入id") swagger文档 参数中文说明
     * @PathVariable("id") 括号内与传入参数名称匹配，赋值给形参，形参名称就可以和传入不同了
     */
    @GetMapping(value = "/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
//    public User getInfo(@ApiParam("传入id") @PathVariable("id") Integer id) {
    public User getInfo(@PathVariable("id") Integer id) {
        log.info("UserController getInfo方法传入参数id为:" + id);
        User user = new User();
        user.setUserId(id);
        user.setUsername("tom");

        // 用户测试Controller异常，spring boot返回到前台内容，通过工具发起的请求，得到的响应是json格式，浏览器发起的请求，得到的响应是html形式
//        throw new RuntimeException("RuntimeException");
        return user;
    }

    /**
     * 测试异常情况spring boot处理机制
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/test/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User getInfo1(@PathVariable("id") Integer id) {
        throw new RuntimeException();
    }

    /**
     * 测试自定义异常返回json内容
     * 抛出的异常先由标注@ControllerAdvice类处理，再返回到页面
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/test/ex/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User getInfo2(@PathVariable("id") Integer id) {
        throw new UserNotFoundException(id);
    }

    @PostMapping
    @JsonView(User.UserSimpleView.class)
    public User create(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                FieldError fieldError = (FieldError) error;
                log.info(fieldError.getField() +
                        ",值为：" + fieldError.getRejectedValue() +
                        ",错误消息为：" + error.getDefaultMessage());
            });
        }
        log.info(ReflectionToStringBuilder.toString(user, ToStringStyle.MULTI_LINE_STYLE));
        user.setUserId(1);
        return user;
    }

    /**
     * 验证400
     *
     * @param user
     * @return
     */
    @PostMapping("/test")
    @JsonView(User.UserSimpleView.class)
    public User create1(@Valid @RequestBody User user) {
        log.info(ReflectionToStringBuilder.toString(user, ToStringStyle.MULTI_LINE_STYLE));
        user.setUserId(1);
        return user;
    }

    @PutMapping(value = "/{id:\\d+}")
    @JsonView(User.UserSimpleView.class)
    public User update(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                FieldError fieldError = (FieldError) error;
                log.info(fieldError.getField() +
                        ",值为：" + fieldError.getRejectedValue() +
                        ",错误消息为：" + error.getDefaultMessage());
            });
        }
        log.info(ReflectionToStringBuilder.toString(user, ToStringStyle.MULTI_LINE_STYLE));
        user.setUserId(1);
        return user;
    }

    @DeleteMapping(value = "/{id:\\d+}")
    public void delete(@PathVariable("id") Integer id) {
        log.info("delete id:" + id);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<User> list() {
        List<User> user = userService.list();
        return user;
    }

    @RequestMapping(value = "removeListCache")
    public void removeListCache() {
        userService.removeListCache();
    }
}
