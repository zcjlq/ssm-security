package com.ssm.security.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author 贾令强
 * @since 2018/7/7 13:13
 */
@RestController
public class ValidateCodeController {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";
//    @Autowired
//    private ValidateCodeGenerator imageCodeGenerator;
//    @Autowired
//    private ValidateCodeGenerator smsCodeGenerator;
//    @Autowired
//    private SmsCodeSender smsCodeSender;

    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;

    /**
     * 重构后代码
     *
     * @param request
     * @param response
     * @param type
     * @throws Exception
     */
    @GetMapping("/code/{type}")
    public void createCode(HttpServletRequest request, HttpServletResponse response,
                           @PathVariable String type) throws Exception {
        validateCodeProcessors.get(type + "ValidateCodeProcessor").create(new ServletWebRequest(request, response));

    }

    /**
     * 重构前
     *
     * @param request
     * @param response
     * @throws IOException
     */
/*   @GetMapping("/code/image")
    public void createImageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        ImageCode imageCode = generateCode(new ServletWebRequest(request));
        ImageCode imageCode = (ImageCode) imageCodeGenerator.generateCode(new ServletWebRequest(request));
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
        ImageIO.write(imageCode.getBufferedImage(), "JPEG", response.getOutputStream());

    }*/

    /**
     * 重构前
     *
     * @param request
     * @throws ServletRequestBindingException
     */
/*/    @GetMapping("/code/sms")
    public void createSmsCode(HttpServletRequest request) throws ServletRequestBindingException {
        ValidateCode smsCode = smsCodeGenerator.generateCode(new ServletWebRequest(request));
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, smsCode);
        String mobileCode = ServletRequestUtils.getRequiredStringParameter(request, "mobile");
        // 封装一个发送验证码的接口，通过不同厂商发送
        smsCodeSender.send(mobileCode, smsCode.getCode());
    }*/
}
