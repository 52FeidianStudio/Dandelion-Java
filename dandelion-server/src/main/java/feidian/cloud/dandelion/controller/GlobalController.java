package feidian.cloud.dandelion.controller;

import cn.hutool.http.HttpUtil;
import feidian.cloud.dandelion.definition.RouteDefinition;
import feidian.cloud.dandelion.utils.IPUtils;
import feidian.cloud.dandelion.utils.PredicateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    Map<String, RouteDefinition> map = new HashMap<>();

    /**
     * 全局的入口，使用模糊匹配
     * 但是当访问已有的接口时会进入具体的接口，例如/dandelion/heart
     *
     * @return 返回请求转发后的信息
     */
    @RequestMapping("/**")
    public String demo(HttpServletRequest request, HttpServletResponse response, HashMap<String, Object> paramMap) {
        //请求的路径，例如/test
        String remoteAddr = request.getRemoteHost();
        System.out.println(remoteAddr);
        String requestUri = request.getRequestURI();
        log.info("全局的入口，请求的地址是{}", requestUri);
        String ipAddr = IPUtils.getIpAddr(request);
        log.info("发起请求的IP地址是{}", ipAddr);
        Set<RouteDefinition> routeDefinitionList = PredicateUtils.matchRoute();
        if (routeDefinitionList == null) {
            return "没有匹配到路由";
        } else {
            //todo 开始进行全局过滤
            for (RouteDefinition routeDefinition : routeDefinitionList) {
                //todo 进入请求前局部过滤器
                //这里的过滤可能已经包含了对requestUri的过滤
                //todo 进行转发请求
                if ("GET".equals(request.getMethod())) {
                    String result = HttpUtil.get(routeDefinition.getUrl()+requestUri,paramMap);
                } else {

                }

                //todo 进入请求后局部过滤器
            }

            return "匹配到的路由是" + routeDefinitionList.toString();
        }
    }
}
