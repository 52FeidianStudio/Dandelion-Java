package feidian.cloud.dandelion.service;

import cn.hutool.cron.CronUtil;
import cn.hutool.cron.task.Task;
import cn.hutool.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.annotation.PostConstruct;
import static feidian.cloud.dandelion.controller.DandelionController.*;


/**
 * @author Zhang Ruilong
 * @email 970586718@qq.com
 * @date 2021-08-20 0:02
 * @description 使用hutool做定时任务
 */

/**
 * 注册到spring容器中
 */
@Component
@CrossOrigin
public class DandelionHeart {
    @PostConstruct
    public void heart() {
        CronUtil.schedule("0/20 * * * * ? ", new Task() {
            @Override
            public void execute() {
                System.out.println("开始心跳检查");
                for ( String key : idRouteMap.keySet() ) {
                    try {
                        if( idRouteMap.get(key).isClient()) {
                            String url = idRouteMap.get(key).getUrl();
                            url = url + "/dandelion/heart";
                            System.out.println(url);
                            HttpRequest.post(url)
                                    .timeout(3000)
                                    .execute().body();
                        }
                    }catch (Exception e){
                        idRouteMap.remove(key);
                    }
                }
                System.out.println("结束心跳检查");
                System.out.println(idRouteMap.keySet());
            }
        });
        // 支持秒级别定时任务
        CronUtil.setMatchSecond(true);
        CronUtil.start();
    }
}


