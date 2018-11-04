package com.ssm.web.config;

import com.ssm.web.filter.TimeFilter2;
import com.ssm.web.interceptors.OperLogInterceptor;
import com.ssm.web.interceptors.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置拦截器
 *
 * @author 贾令强
 * @since 2018/6/18 14:37
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private TimeInterceptor timeInterceptor;
    @Autowired
    private OperLogInterceptor operLogInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor);
        registry.addInterceptor(operLogInterceptor);
    }

    @Bean
    public FilterRegistrationBean timeFilter2() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        TimeFilter2 timeFilter2 = new TimeFilter2();
        filterRegistrationBean.setFilter(timeFilter2);

        List<String> urls = new ArrayList<>();
        urls.add("/*");
        filterRegistrationBean.setUrlPatterns(urls);

        return filterRegistrationBean;
    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        // 拦截异步请求，设置超时时间
//        configurer.registerCallableInterceptors();
//        configurer.registerDeferredResultInterceptors();
//        configurer.setDefaultTimeout();
        // spring处理异步请求有一个默认的任务线程池，可以替换为自己的
//        configurer.setTaskExecutor();

    }
}
