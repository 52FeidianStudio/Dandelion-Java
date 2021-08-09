package feidian.cloud.autoconfigure.controller;

import feidian.cloud.autoconfigure.peoperties.RouteProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Zhang Ruilong
 * @email 970586718@qq.com
 * @date 2021-08-09 9:51
 */
@RequestMapping("/dandelion")
@RestController
public class DandelionController {
    @Autowired
    RouteProperties routeProperties;

    /**
     * 和服务器进行心跳检查的接口
     */
    @RequestMapping("/heart")
    public Object heart() {
        Map<String, Object> response = new HashMap<>();
        /**
         * 200表示正确响应
         */
        response.put("code",200);
        /**
         * 将配置信息返回给dandelion服务器，方便配置中心动态修改
         */
        response.put("config",routeProperties);
        return response;
    }
}
