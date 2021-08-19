package com.example.demo1.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync//开启多线程
public class ThreadConfig {

    @Bean("teskExcutor")
    public Executor test(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        //设置核心线程数量
        executor.setCorePoolSize(5);
        //设置最大线程数量
        executor.setMaxPoolSize(20);
        //配置队列大小
        executor.setQueueCapacity(Integer.MAX_VALUE);
        //设置线程活跃的时间
        executor.setKeepAliveSeconds(60);
        //设置默认线程的名称
        executor.setThreadNamePrefix("test1");
        //执行初始化
        executor.initialize();

        return executor;
    }
}
