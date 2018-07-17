package com.ssm.web.controller.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author 贾令强
 * @since 2018/6/19 00:57
 */
@Component
public class MockQueue {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private String placeHolder;

    private String completeHolder;

    public String getPlaceHolder() {
        return placeHolder;
    }

    /**
     * 下单后，placeHolder被赋值，1秒后，请求处理完毕，给completeHolder赋值，被监听到赋值后，进行其他逻辑处理
     *
     * @param placeHolder
     * @throws InterruptedException
     */
    public void setPlaceHolder(String placeHolder) throws InterruptedException {
        new Thread(() -> {
            logger.info("MockQueue setPlaceHolder 接到下单请求，placeHolder：" + placeHolder);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.completeHolder = placeHolder;
            logger.info("MockQueue setPlaceHolder 下单请求处理完成");
        }).start();
    }

    public String getCompleteHolder() {
        return completeHolder;
    }

    public void setCompleteHolder(String completeHolder) {
        this.completeHolder = completeHolder;
    }
}
