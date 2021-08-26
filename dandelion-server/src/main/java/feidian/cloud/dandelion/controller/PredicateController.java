package feidian.cloud.dandelion.controller;

import feidian.cloud.dandelion.definition.PredicateDefinition;
import feidian.cloud.dandelion.definition.RouteDefinition;
import feidian.cloud.dandelion.predicate.PathPredicate;
import feidian.cloud.dandelion.vo.PredicateVo;
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
    public ArrayList<PredicateVo> getPredicateList(){
        Map<String, RouteDefinition> idRouteMap = DandelionController.idRouteMap;
        ArrayList<PredicateVo> list = new ArrayList<>();
        for(RouteDefinition routeDefinition : idRouteMap.values()){
            for (PredicateDefinition predicateDefinition : routeDefinition.getPredicates().values()){
                list.add(new PredicateVo(routeDefinition.getId(),
                        predicateDefinition.getName(),
                        predicateDefinition.getDes(),
                        predicateDefinition.getRemark(),
                        predicateDefinition.getArgs()));
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
            if (idRouteMap.get(id).getPredicates().get(predicateName)==null){
                //没有这个断言
                Map.put("code",400);
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
    @PostMapping("/update")
    public Object updatePredicate(@RequestBody Map map,@RequestBody List<String> config){
        Map<String, RouteDefinition> idRouteMap = DandelionController.idRouteMap;
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
