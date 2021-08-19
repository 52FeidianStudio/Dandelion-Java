package feidian.cloud.dandelion.config;

import feidian.cloud.dandelion.service.DandelionHeart;
import org.quartz.JobDataMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import java.util.Date;


/**
 * @author Zhao Yongsheng
 * @email 1902975393@qq.com
 * @date 2021/8/14 15:25
 * 执行DandelionHeart类，用来做定时任务，每20秒去扫描route，做心跳检查
 */

@Configuration
public class QuartzDandelionHeart {
    /**
     * 创建job类对象
     */
    @Bean( name="TimeOutJob" )
    @SuppressWarnings("static-access")
    public JobDetailFactoryBean jobDetailFactoryBean() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(DandelionHeart.class);
        JobDataMap map = new JobDataMap();
        bean.setJobDataMap(map);
        return bean;
    }

    /**
     * 创建Trigger对象 ,在这里设置为每20秒就去检查一边Map列表，去除超时的route
     * 目前只要服务开启就不会暂停
     */
    @Bean
    @SuppressWarnings("static-access")
    public SimpleTriggerFactoryBean simpleTriggerFactoryBean() {
        SimpleTriggerFactoryBean bean = new SimpleTriggerFactoryBean();
        bean.setStartTime(new Date());
        bean.setJobDetail(jobDetailFactoryBean().getObject());
        bean.setRepeatInterval(20000);
        return bean;
    }

    /**
     * 创建触发器实现定时任务
     */
    @Bean
    @SuppressWarnings("static-access")
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setTriggers(simpleTriggerFactoryBean().getObject());
        return bean;
    }

}
