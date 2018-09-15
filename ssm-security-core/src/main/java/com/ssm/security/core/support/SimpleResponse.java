package com.ssm.security.core.support;

/**
 * @author 贾令强
 * @since 2018/7/28 15:27
 */
public class SimpleResponse {

    public SimpleResponse(Object content) {
        this.content = content;
    }

    private Object content;

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
