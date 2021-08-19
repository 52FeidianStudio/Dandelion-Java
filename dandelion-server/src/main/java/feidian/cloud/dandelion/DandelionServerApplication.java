package feidian.cloud.dandelion;

import cn.hutool.core.lang.Console;
import cn.hutool.cron.CronUtil;
import cn.hutool.cron.task.Task;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableScheduling
public class DandelionServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DandelionServerApplication.class, args);
    }

}
