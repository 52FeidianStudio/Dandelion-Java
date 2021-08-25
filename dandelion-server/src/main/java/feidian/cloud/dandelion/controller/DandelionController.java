package feidian.cloud.dandelion.controller;

import feidian.cloud.autoconfigure.peoperties.RouteProperties;
import feidian.cloud.dandelion.add.AddPreString;
import feidian.cloud.dandelion.add.PredicateList;
import feidian.cloud.dandelion.definition.PredicateDefinition;
import feidian.cloud.dandelion.definition.RouteDefinition;
import feidian.cloud.dandelion.predicate.PathPredicate;
import feidian.cloud.dandelion.utils.RouteProperties2RouteDefinition;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import lombok.var;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Zhang Ruilong
 * @email 970586718@qq.com
 * @date 2021-08-09 16:56
 * @description 客户端心跳服务的controller
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/dandelion")
public class DandelionController {

    /**
     * key是路由的id
     * value是RouteDefinition对象
     */
    public static Map<String, RouteDefinition> idRouteMap = new HashMap<>();
    /**
     * 接收配置信息的接口
     */
    @RequestMapping("/client")
    public Object client(@RequestBody RouteProperties routeProperties, HttpServletRequest request) {
        //返回信息
        Map<String, Integer> map = new HashMap<>();
        //RouteProperties转化为RouteDefinition
        try {
            RouteDefinition routeDefinition = RouteProperties2RouteDefinition.change(routeProperties);
            //对url地址进行清洗
            String url = routeDefinition.getUrl();
            if (url.split(":").length<2) {
                //如果没加https://或http请求协议的就默认加位http://
                url = "http://"+url;
            }
            if (url.lastIndexOf("/") == url.length()-1) {
                //如果最后加上了/就给他删掉，即http://localhost:8000/-->http://localhost:8000
                url = url.substring(0, url.length() - 2);
            }
            routeDefinition.setUrl(url);
            System.out.println(url);
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

    /**
     *获取配置列表
     */
    @ApiOperation("获取配置列表")
    @GetMapping("/config/list")
    public ArrayList<RouteDefinition> getConfigList(){
        ArrayList<RouteDefinition> routeDefinitions = new ArrayList<>();
        for(RouteDefinition routeDefinition : idRouteMap.values()){
            routeDefinitions.add(routeDefinition);
        }
        return routeDefinitions;
    }

    /**
     * 删除某一个路由的配置
     */
    @ApiOperation("删除某一个路由的配置")
    @GetMapping("/route/delete")
    public Object DeleteRoute(@RequestParam("routeId") String routeId){
        Map<String, Integer> map = new HashMap<>();
        if (idRouteMap.get(routeId)==null){
            map.put("code",400);
            //如果没有此配置，就返回400错误
        }else {
            idRouteMap.remove(routeId);
            map.put("code",200);
            //如果有此配置，就删除，返回200
        }
        return map;
    }

    /**
     * 查找某一个路由的配置
     */
    @ApiOperation("查找某一个路由的配置")
    @GetMapping("/route/findById")
    public Object findRouteById(@RequestParam("routeId") String routeId){
        Map<String, Integer> map = new HashMap<>();
        System.out.println(routeId);
        if (idRouteMap.get(routeId)==null){
            map.put("code",400);
            return map;
        }else {
            return idRouteMap.get(routeId);
        }
    }

    /**
     * 更新配置
     */
    @ApiOperation("更新配置")
    @PostMapping("/route/update")
    public Object updateRoute(@RequestBody RouteDefinition routeDefinition){
        System.out.println(routeDefinition);
        Map<String, Integer> map = new HashMap<>();
        if (idRouteMap.get(routeDefinition.getId())==null){
            map.put("code",400);
            //没有此配置，就返回400错误
        }else {
            idRouteMap.put(routeDefinition.getId(),routeDefinition);
            map.put("code",200);
        }
        return map;
    }

    /**
     * 创建配置
     */
    @ApiOperation("创建配置")
    @PostMapping ("/route/insert")
    public Object insertRoute(@RequestBody RouteDefinition routeDefinition){
        Map<String, Integer> map = new HashMap<>();
        if (idRouteMap.get(routeDefinition.getId())==null){
            idRouteMap.put(routeDefinition.getId(),routeDefinition);
            map.put("code",200);
        }else {
            map.put("code",400);
        }//先判断有与该配置id相同的配置存在，若有就返回400，没有就创建，返回200
        return map;
    }

    /**
     * 获取断言器列表
     */
    @ApiOperation("获取断言器列表")
    @GetMapping("/route/predicate/list")
    public Object getRoutePredicateList(){
        Map<String,String> map = new HashMap<>();
        map.put("name","Path");
        map.put("remark","路径匹配断言器");
        return map;
    }

    /**
     * 获取断言列表
     */
    @ApiOperation("获取断言列表")
    @GetMapping("/predicate/list")
    public ArrayList<PredicateList> getPredicateList(){
        ArrayList<PredicateList> list = new ArrayList<>();
        for(RouteDefinition routeDefinition : idRouteMap.values()){
            for (PredicateDefinition predicateDefinition : routeDefinition.getPredicates().values()){
                list.add(new PredicateList(routeDefinition.getId(),predicateDefinition.getName(),predicateDefinition.getDes(),predicateDefinition.getRemark(),predicateDefinition.getArgs()));
            }
        }
        return list;
    }

    /**
     * 删除断言
     */
    @ApiOperation("删除断言")
    @PostMapping("/predicate/delete")
    public Object deletePredicate(@RequestBody Map map){
        String id = (String) map.get("id");
        String predicateName = (String) map.get("predicate_name");
        HashMap<String, Integer> Map = new HashMap<>();
        if (idRouteMap.get(id)==null){
            Map.put("code",400);//没有这个服务
        }else {
            if (idRouteMap.get(id).getPredicates().get(predicateName)==null){
                Map.put("code",400);//没有这个断言
            }else {
                idRouteMap.get(id).getPredicates().remove(predicateName);
                Map.put("code",200);
                //有这个服务与断言，就删除这个断言，返回200
            }
        }
        return Map;
    }

    /**
     * 修改断言
     */
    @ApiOperation("修改断言")
    @PostMapping("/predicate/update")
    public Object updatePredicate(@RequestBody Map map,@RequestBody List<String> config){
        String id = (String) map.get("id");
        String predicate_name = (String) map.get("predicate_name");
        String des = (String) map.get("des");
        String remark = (String) map.get("remark");
        if (idRouteMap.get(id)==null){
            map.put("code",400);
        }else {
            if (idRouteMap.get(id).getPredicates().get(predicate_name)==null){
                map.put("code",400);
            }else {

                PathPredicate predicate= (PathPredicate) idRouteMap.get(id).getPredicates().get(predicate_name);
                predicate.setRemark(remark);
                predicate.setName(predicate_name);
                predicate.setDes(des);
                predicate.setArgs(config);
                map.put("code",200);
            }
        }
        return map;
//学长，这里的config数据出不来
    }
}
