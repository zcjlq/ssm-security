package com.ssm.web.controller.async;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;


/**
 * @author 贾令强
 * @since 2018/6/19 00:21
 */
@RestController
@RequestMapping("/async")
public class AsyncController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @GetMapping("/normal")
    public String normal() throws InterruptedException {
        logger.info("main thread start,thread name:" + Thread.currentThread().getName());
        long start = System.currentTimeMillis();
        Thread.sleep(1000);

        logger.info("main thread end,time:" + (System.currentTimeMillis() - start));
        return "normal";
    }

    @GetMapping("/async")
    public Callable<String> testAsync() {
        logger.info("main thread start,thread name:" + Thread.currentThread().getName());
        long start = System.currentTimeMillis();

        Callable<String> callable = () -> {
            logger.info("async thread start,thread name:" + Thread.currentThread().getName());
            Thread.sleep(1000);
            logger.info("async thread stop,time:" + (System.currentTimeMillis() - start));
            return "async";
        };

        System.out.println("callable thread return:" + callable);
        logger.info("main thread end,time:" + (System.currentTimeMillis() - start));
        return callable;
    }

    /**
     * 模拟下单，页面发起请求后，由消息队列传递到其他应用，处理后返回主调用方
     *
     * @return
     */
    @GetMapping("/defer")
    public DeferredResult<String> testDeferredResultHolder() throws InterruptedException {
        logger.info("testDeferredResultHolder start");
        long start = System.currentTimeMillis();

        String orderNum = RandomStringUtils.randomNumeric(8);
        mockQueue.setPlaceHolder(orderNum);

        DeferredResult<String> deferredResult = new DeferredResult<>();
        deferredResultHolder.getMap().put(orderNum, deferredResult);

        logger.info("testDeferredResultHolder 耗时：" + (System.currentTimeMillis() - start));
        return deferredResult;
    }


}
