package feidian.cloud.dandelion.controller;

import feidian.cloud.dandelion.definition.RouteDefinition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Zhang Ruilong
 * @email 970586718@qq.com
 * @date 2021-08-09 16:40
 */
@Slf4j
@Controller
public class GlobalController {
    /**
     * 路由地址map
     */
    Map<String, RouteDefinition> map = new HashMap<>();

    /**
     * 全局的入口，使用模糊匹配
     * 但是当访问已有的接口时会进入具体的接口，例如/dandelion/heart
     * @return 返回请求转发后的信息
     * 目前还有参数看不到的问题
     */
    @RequestMapping("/**")
    public Object demo(HttpServletRequest request, HttpServletResponse response, String username, String password) {
        //请求的路径，会自动去除ip+端口号，得到结果形如：/test
        String requestUri = request.getRequestURI();
        log.info("请求的地址是{}", requestUri);
        request.setAttribute("url",requestUri);


        //if (routeDefinition==null) {
        //    return "没有匹配到路由";
        //} else {
        //    //todo 开始进行全局过滤
        //
        //    //todo 进入请求前局部过滤器
        //    //这里的过滤可能已经包含了对requestUri的过滤
        //    //todo 进行转发请求
        //    String result;
        //    request.setAttribute("test","见到此值即测试成功");
        //
        //    String url = routeDefinition.getUrl()+requestUri;

            //if ("GET".equals(request.getMethod())) {
            //    //request和response会丢失
            //    result = HttpUtil.get(routeDefinition.getUrl()+requestUri);
            //    log.info("【GET】转发结果为:{}",result);
            //} else if ("POST".equals(request.getMethod())){
            //    //result = HttpUtil.post(routeDefinition.getUrl()+requestUri,paramMap);
            //    result = "xxxx";
            //    log.info("【POST】转发结果为:{}",result);
            //} else {
            //    return "该请求方法目前不支持";
            //}
            ////todo 进入请求后局部过滤器
            //
            //return result;
        request.setAttribute("url",requestUri);
        return "forward:/dandelion/forward";
    }
}

