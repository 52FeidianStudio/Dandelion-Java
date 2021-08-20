package feidian.cloud.dandelion.controller;

import cn.hutool.http.HttpUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Zhang Ruilong
 * @email 970586718@qq.com
 * @date 2021-08-20 17:14
 * @description
 */
@RestController
public class ForwardController {

    public Map<String, Object> getParameterMap(HttpServletRequest request){
        //清洗参数，通常是一对一的情况，不过像多选框可能是一对多的情况
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, Object> map = new HashMap<>(parameterMap.size());
        parameterMap.forEach((k,v) -> {
            if (v.length==1) {
                map.put(k,v[0]);
            } else {
                map.put(k,v);
            }
        });
        return map;
    }

    @RequestMapping("/dandelion/forward")
    public String forward(HttpServletRequest request) {
        int parameterSize = request.getParameterMap().size();

        String method = request.getMethod();
        if ("GET".equals(method)) {
            //GET请求转发
            return "forward:/dandelion/get";
        } else if ("POST".equals(method) && parameterSize!=0) {
            //这种情况是发起了post请求，但是是用原始表单发的，下面的else是axios的默认post
            return "forward:/dandelion/formPost";
        } else if ("POST".equals(method)) {
            //POST请求转发
            return "forward:/dandelion/post";
        } else {
            return "不支持该方法";
        }
    }

    @RequestMapping("/dandelion/get")
    public String get(HttpServletRequest request) {
        //在GlobalController中已经写了要转发的url
        String url = (String) request.getAttribute("url");
        //获取参数
        Map<String, Object> parameterMap = getParameterMap(request);
        //请求转发
        String result= HttpUtil.get(url, parameterMap);
        return result;
    }

    /**
     * 这样支持axios情况，不支持情况表单post
     * @return
     */
    @RequestMapping("/dandelion/post")
    public String post(HttpServletRequest request,@RequestBody Map map) {
        //在GlobalController中已经写了要转发的url
        String url = (String) request.getAttribute("url");
        //请求转发
        String result= HttpUtil.post(url, map);
        return result;
    }
    /**
     * 还有一种情况是post表单
     */
    @RequestMapping("/dandelion/formPost")
    public String formPost(HttpServletRequest request,@RequestBody Map map) {
        //在GlobalController中已经写了要转发的url
        String url = (String) request.getAttribute("url");
        //获取参数
        Map<String, Object> parameterMap = getParameterMap(request);
        //请求转发
        String result= HttpUtil.post(url, parameterMap);
        return result;
    }
}
