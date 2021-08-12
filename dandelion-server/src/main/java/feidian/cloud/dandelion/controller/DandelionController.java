package feidian.cloud.dandelion.controller;

import feidian.cloud.dandelion.definition.RouteDefinition;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 客户端心跳服务的controller
 * @author Zhang Ruilong
 * @email 970586718@qq.com
 * @date 2021-08-09 16:56
 */
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


}
