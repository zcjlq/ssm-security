package com.ssm.web.controller.async;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 系统启动时执行
 *
 * @author 贾令强
 * @since 2018/6/19 01:10
 */
@Component
public class QueueListener implements ApplicationListener {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        // 如果不单开线程，将阻塞系统启动
        new Thread(() -> {
            while (true) {
                if (StringUtils.isNotBlank(mockQueue.getCompleteHolder())) {
                    String completeHolder = mockQueue.getCompleteHolder();
                    logger.info("返回订单处理结果：" + completeHolder);
                    deferredResultHolder.getMap().get(completeHolder).setResult("最终返回的信息");
                    mockQueue.setCompleteHolder(null);
                } else {
//                logger.info("监听器没有监听到订单处理结果");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
