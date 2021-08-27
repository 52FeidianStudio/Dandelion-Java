package feidian.cloud.dandelion.controller;

import feidian.cloud.dandelion.definition.RouteDefinition;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Zhang Ruilong
 * @email 970586718@qq.com
 * @date 2021-08-26 12:31
 * @description
 */
@RestController
@RequestMapping("/route")
public class RouteController {
    /**
     *获取配置列表
     */
    @ApiOperation("获取配置列表")
    @GetMapping("/list")
    public ArrayList<RouteDefinition> getConfigList(){
        Map<String, RouteDefinition> idRouteMap = DandelionController.idRouteMap;
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
    @GetMapping("/delete")
    public Object DeleteRoute(@RequestParam("routeId") String routeId){
        Map<String, RouteDefinition> idRouteMap = DandelionController.idRouteMap;
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
    @GetMapping("/findById")
    public Object findRouteById(@RequestParam("routeId") String routeId){
        Map<String, RouteDefinition> idRouteMap = DandelionController.idRouteMap;
        Map<String, Integer> map = new HashMap<>();
        if (idRouteMap.get(routeId)==null){
            map.put("code",400);
            return map;
        }else {
            RouteDefinition routeDefinition = idRouteMap.get(routeId);
            return routeDefinition;
        }
    }

    /**
     * 更新配置
     */
    @ApiOperation("更新配置")
    @PostMapping("/update")
    public Object updateRoute(@RequestBody Map map){
        Map<String, RouteDefinition> idRouteMap = DandelionController.idRouteMap;
        Map<String, Integer> result = new HashMap<>();
        Object id = map.get("id");
        RouteDefinition routeDefinition = idRouteMap.get(id);
        if (routeDefinition ==null){
            result.put("code",400);
            //没有此配置，就返回400错误
        } else {
            routeDefinition.setId((String) map.get("id"));
            routeDefinition.setOrder(Integer.valueOf((Integer) map.get("order")));
            routeDefinition.setUrl((String) map.get("url"));
            routeDefinition.setClient(Boolean.getBoolean((String) map.get("client")));
            result.put("code",200);
        }
        return result;
    }

    /**
     * 创建配置
     */
    @ApiOperation("创建配置")
    @PostMapping ("/insert")
    public Object insertRoute(@RequestBody RouteDefinition routeDefinition){
        Map<String, RouteDefinition> idRouteMap = DandelionController.idRouteMap;
        Map<String, Integer> map = new HashMap<>();
        map.put("code",400);
        if (routeDefinition.getId().equals("")) {
            return map;
        }
        //先判断有与该配置id相同的配置存在，若有就修改个别信息，没有就创建，返回200
        RouteDefinition routeDefinition1 = idRouteMap.get(routeDefinition.getId());
        if (routeDefinition1==null){
            idRouteMap.put(routeDefinition.getId(),routeDefinition);
            map.put("code",200);
        } else {
            routeDefinition1.setId(routeDefinition.getId());
            routeDefinition1.setUrl(routeDefinition.getUrl());
            routeDefinition1.setOrder(routeDefinition.getOrder());
        }
        return map;
    }
    /**
     * 获取断言器列表
     */
    @ApiOperation("获取断言器列表")
    @GetMapping("/predicate/list")
    public Object getRoutePredicateList(){
        Map<String,String> map = new HashMap<>();
        map.put("Path","路径匹配断言器");
        map.put("Date","日期断言器");
        return map;
    }
}
