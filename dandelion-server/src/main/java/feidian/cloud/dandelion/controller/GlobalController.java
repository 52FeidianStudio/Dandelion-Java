package feidian.cloud.dandelion.controller;

import feidian.cloud.dandelion.definition.RouteDefinition;
import feidian.cloud.dandelion.utils.IPUtils;
import feidian.cloud.dandelion.utils.PathUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Zhang Ruilong
 * @email 970586718@qq.com
 * @date 2021-08-09 16:40
 */
@Slf4j
@RestController
public class GlobalController {
    /**
     * 路由地址map
     */
    Map<String, RouteDefinition> map= new HashMap<>();
    /**
     * 全局的入口，使用模糊匹配
     * 但是当访问已有的接口时会进入具体的接口，例如/dandelion/heart
     * @return 返回请求转发后的信息
     */
    @RequestMapping("/**")
    public String demo(HttpServletRequest request) {
        //请求的路径，例如/test
        String requestUri = request.getRequestURI();
        log.info("全局的入口，请求的地址是{}",requestUri);
        String ipAddr = IPUtils.getIpAddr(request);
        log.info("请求的IP地址是{}",ipAddr);
        String path = PathUtils.matchPath(requestUri);
        if (path==null) {
            return "没有匹配到路由";
        } else {
            RouteDefinition routeDefinition = DandelionController.pathRouteMap.get(path);
            //todo 开始进行全局过滤
            //转发请求
            return "匹配到的路由是"+routeDefinition.toString();
        }
    }
}
