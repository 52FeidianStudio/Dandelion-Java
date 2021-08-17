package feidian.cloud.dandelion.service;


import cn.hutool.http.HttpException;
import cn.hutool.http.HttpRequest;
import feidian.cloud.dandelion.definition.RouteDefinition;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static feidian.cloud.dandelion.controller.DandelionController.idRouteMap;


/**
 * @author Zhao Yongsheng
 * @email 1902975393@qq.com
 * @date 2021/8/14 17:29
 */

public class ForwardPost {

    public static void forward(RouteDefinition routeDefinition) {
        System.out.println("进行转发");
        if( routeDefinition!=null ) {
            System.out.println(routeDefinition.getUrl());
            try {
                HttpRequest.post(routeDefinition.getUrl())
                        .execute().body();
                System.out.println("转发成功");
            } catch (HttpException e) {
                System.out.println("转发失败");
                e.printStackTrace();
            }
        }
    }
}
