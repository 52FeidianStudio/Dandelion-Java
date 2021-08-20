package feidian.cloud.dandelion.controller;

import feidian.cloud.autoconfigure.peoperties.RouteProperties;
import feidian.cloud.dandelion.definition.RouteDefinition;
import feidian.cloud.dandelion.utils.RouteProperties2RouteDefinition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Zhang Ruilong
 * @email 970586718@qq.com
 * @date 2021-08-09 16:56
 * @description 接收客户端配置信息的接口，同时idRouteMap存放了客户端的路由信息
 */
@Slf4j
@RestController
@RequestMapping("/dandelion")
public class DandelionController {
    /**
     * 存放了客户端的路由信息
     */
    public static Map<String, RouteDefinition> idRouteMap = new HashMap<>();
    /**
     * 接收配置信息的接口
     */
    @RequestMapping("/client")
    public Object client(@RequestBody RouteProperties routeProperties) {
        //返回信息
        Map<String, Integer> map = new HashMap<>();
        //RouteProperties对象转化为RouteDefinition对象
        try {
            RouteDefinition routeDefinition = RouteProperties2RouteDefinition.change(routeProperties);
            //对url地址进行清洗
            String url = cleanURL(routeDefinition.getUrl());
            routeDefinition.setUrl(url);
            //初始化配置信息到map中
            idRouteMap.put(routeDefinition.getId(),routeDefinition);
            map.put("code",200);
            log.info("客户端连接成功，信息为{}",routeProperties.toString());
        } catch (Exception e) {
            log.error("客户端配置有误");
            e.printStackTrace();
            map.put("code",400);
        }
        return map;
    }

    private String cleanURL(String url) {
        if (url.split(":").length<2) {
            //如果没加https://或http请求协议的就默认加位http://
            url = "http://"+url;
        }
        if (url.lastIndexOf("/") == url.length()-1) {
            //如果最后加上了/就给他删掉，即http://localhost:8000/-->http://localhost:8000
            url = url.substring(0, url.length() - 2);
        }
        return url;
    }
}
