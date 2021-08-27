package feidian.cloud.dandelion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Zhang Ruilong
 * @email 970586718@qq.com
 * @date 2021-08-26 22:13
 * @description
 */
@RestController
@RequestMapping("/url")
public class UrlController {
    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;
    @Autowired
    private HttpServletRequest request;
    /**
     * 获取当前项目中的全部接口
     */
    @RequestMapping(value = "/getAllUrl")
    public String getAllUrl() {
        StringBuilder sb = new StringBuilder();
        sb.append("URL").append("--").append("Class").append("--").append("Function").append('\n');
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
        int i=1;
        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
            RequestMappingInfo info = m.getKey();
            HandlerMethod method = m.getValue();
            sb.append(i+":").append(info.getPatternsCondition()).append("--");
            sb.append(method.getMethod().getDeclaringClass()).append("--");
            sb.append(method.getMethod().getName()).append('\n');
            i++;
        }
        System.out.println(sb);
        return sb.toString();
    }

    /**
     * 获取当前访问url
     */
    @RequestMapping(value = "/getUrl")
    public String getUrl() {
        String url = "";
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();
        String servletPath = request.getServletPath();
        url = scheme +"://" + serverName +":" +serverPort+servletPath;
        if (request.getQueryString() != null){
            url += "?" + request.getQueryString();
        }
        System.out.println(url);
        return url;
    }
}
