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
    @PostMapping("/update")
    public Object updateRoute(@RequestBody RouteDefinition routeDefinition){
        Map<String, RouteDefinition> idRouteMap = DandelionController.idRouteMap;
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
    @PostMapping ("/insert")
    public Object insertRoute(@RequestBody RouteDefinition routeDefinition){
        Map<String, RouteDefinition> idRouteMap = DandelionController.idRouteMap;
        Map<String, Integer> map = new HashMap<>();
        //先判断有与该配置id相同的配置存在，若有就返回400，没有就创建，返回200
        if (idRouteMap.get(routeDefinition.getId())==null){
            idRouteMap.put(routeDefinition.getId(),routeDefinition);
            map.put("code",200);
        } else {
            map.put("code",400);
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
        map.put("name","Path");
        map.put("des","路径匹配断言器");
        return map;
    }
}
