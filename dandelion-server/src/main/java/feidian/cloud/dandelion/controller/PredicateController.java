package feidian.cloud.dandelion.controller;

import feidian.cloud.dandelion.definition.PredicateDefinition;
import feidian.cloud.dandelion.definition.RouteDefinition;
import feidian.cloud.dandelion.predicate.PredicateFactory;
import feidian.cloud.dandelion.vo.PredicateVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Zhang Ruilong
 * @email 970586718@qq.com
 * @date 2021-08-26 12:30
 * @description
 */
@RestController
@RequestMapping("/predicate")
public class PredicateController {
    /**
     * 获取断言列表
     */
    @ApiOperation("获取断言列表")
    @GetMapping("/list")
    public ArrayList<PredicateVO> getPredicateList(){
        Map<String, RouteDefinition> idRouteMap = DandelionController.idRouteMap;
        ArrayList<PredicateVO> list = new ArrayList<>();
        for(RouteDefinition routeDefinition : idRouteMap.values()){
            for (PredicateDefinition predicateDefinition : routeDefinition.getPredicates().values()){
                list.add(new PredicateVO(routeDefinition.getId(),predicateDefinition));
            }
        }
        return list;
    }

    /**
     * 删除断言
     */
    @ApiOperation("删除断言")
    @PostMapping("/delete")
    public Object deletePredicate(@RequestBody Map map){
        Map<String, RouteDefinition> idRouteMap = DandelionController.idRouteMap;
        String id = (String) map.get("id");
        String predicateName = (String) map.get("predicate_name");
        HashMap<String, Integer> Map = new HashMap<>();
        if (idRouteMap.get(id)==null){
            //没有这个路由
            Map.put("code",400);
        }else {
            Map<String, PredicateDefinition> predicates = idRouteMap.get(id).getPredicates();
            if (predicates.get(predicateName)==null){
                //没有这个断言
                Map.put("code",400);
            }else {
                //有这个服务与断言，就删除这个断言，返回200
                predicates.remove(predicateName);
                Map.put("code",200);
            }
        }
        return Map;
    }

    /**
     * 修改断言
     */
    @ApiOperation("修改断言")
    @PostMapping("/update")
    public Object updatePredicate(@RequestBody Map map){
        Map<String, RouteDefinition> idRouteMap = DandelionController.idRouteMap;
        String id = (String) map.get("id");
        String name = (String) map.get("name");
        String remark = (String) map.get("remark");
        List<String> config = (List<String>) map.get("config");
        if (idRouteMap.get(id)==null){
            map.put("code",400);
        }else {
            Map<String, PredicateDefinition> predicates = idRouteMap.get(id).getPredicates();
            PredicateDefinition predicateDefinition = predicates.get(name);
            if (predicateDefinition ==null){
                map.put("code",400);
            }else {
                predicateDefinition.setRemark(remark);
                predicateDefinition.setConfig(config);
                map.put("code",200);
            }
        }
        return map;
        //学长，这里的config数据出不来
    }
    /**
     * 为服务创建一个断言
     */
    @PostMapping("/insert")
    public Map insert(@RequestBody Map map) {
        Map<String, RouteDefinition> idRouteMap = DandelionController.idRouteMap;
        Object id = map.get("id");
        RouteDefinition routeDefinition = idRouteMap.get(id);
        HashMap<Object, Object> result = new HashMap<>();
        if (!(routeDefinition==null)) {
            String name = (String) map.get("name");
            List config = (List) map.get("config");
            String remark = (String) map.get("remark");
            PredicateDefinition instance = PredicateFactory.getInstance(name, config, remark);
            routeDefinition.getPredicates().put(instance.getName(),instance);
            result.put("code",200);
            return result;
        }
        result.put("code",400);
        return result;
    }
}
