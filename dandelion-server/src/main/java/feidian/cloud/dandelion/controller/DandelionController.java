package feidian.cloud.dandelion.controller;

import feidian.cloud.autoconfigure.peoperties.RouteProperties;
import feidian.cloud.dandelion.definition.RouteDefinition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Zhang Ruilong
 * @email 970586718@qq.com
 * @date 2021-08-09 16:56
 * @description 客户端心跳服务的controller
 */
@Slf4j
@RestController
@RequestMapping("/dandelion")
public class DandelionController {
    /**
     * key是路由的id
     * value是RouteDefinition对象
     */
    public static Map<String, RouteDefinition> pathRouteMap = new HashMap<>();

    /**
     * 和客户端进行心跳检查的接口
     */
    @RequestMapping("/heart")
    public void heart() {
        System.out.println("和客户端进行心跳检查的接口");
    }

    /**
     * 接收配置信息的接口
     */
    @RequestMapping("/client")
    public Object client(@RequestBody RouteDefinition routeDefinition) {
        log.info("客户端连接，信息为{}",routeDefinition.toString());
        //todo 初始化配置信息到map中
        //返回信息
        Map<Object, Object> map = new HashMap<>();
        map.put("code",200);
        return map;
    }
}
