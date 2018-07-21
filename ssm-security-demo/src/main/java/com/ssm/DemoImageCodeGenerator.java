package com.ssm;

import com.ssm.security.core.validate.code.ValidateCodeGenerator;
import com.ssm.security.core.validate.code.image.ImageCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author 贾令强
 * @since 2018/7/10 21:55
 */
//@Component("imageCodeGenerator")
// 容器启动后声明了bean imageCodeGenerator，访问登陆页面将调用下面的方法生成验证码，由于此处返回空，将报错
// 这里只是为了演示如何自定义配置 为了正常登陆，展示注释掉
public class DemoImageCodeGenerator implements ValidateCodeGenerator {

    @Override
    public ImageCode generateCode(ServletWebRequest request) {
        System.out.println("测试具体应用中实现某个接口，从而实现验证码可配置");
        return null;
    }
}
