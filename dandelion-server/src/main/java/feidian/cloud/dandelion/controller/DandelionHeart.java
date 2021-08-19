package feidian.cloud.dandelion.controller;

import cn.hutool.http.HttpRequest;
import feidian.cloud.dandelion.definition.RouteDefinition;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static feidian.cloud.dandelion.controller.DandelionController.*;

@RestController
public class DandelionHeart  extends QuartzJobBean {

    private static long overTime = 5*1000;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("开始心跳检查");
        Set<String> keySet = idRouteMap.keySet();
        long beginTime = System.currentTimeMillis();
        for ( String key : keySet ) {
            if( idRouteMap.get(key).isClient()) {
                long nowTime = System.currentTimeMillis();
                if(((nowTime - beginTime) > overTime)) {
                    String url=idRouteMap.get(key).getUrl();
                    String[] split = url.split("/");
                    for (int i = 0; i < split.length; i++) {
                        System.out.println(i+" "+split[i]);
                    }
                    url=split[0]+"//"+split[2]+"/dandelion/heart";
                    System.out.println(url);
                    HttpRequest.post(url)
                            .execute().body();
                    idRouteMap.remove(key);
                }else {
                    continue;
                }
            }else{
                continue;
            }
        }
        System.out.println("结束心跳检查");
    }
}
