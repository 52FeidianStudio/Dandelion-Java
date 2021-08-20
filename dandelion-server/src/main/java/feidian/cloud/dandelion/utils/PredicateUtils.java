package feidian.cloud.dandelion.utils;

import feidian.cloud.dandelion.controller.DandelionController;
import feidian.cloud.dandelion.definition.PredicateDefinition;
import feidian.cloud.dandelion.definition.RouteDefinition;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author Zhang Ruilong
 * @email 970586718@qq.com
 * @date 2021-08-10 17:17
 * 其实断言的判断包含两种方式，一是遍历一遍网关组件，再遍历一遍里面的断言器，二是根据断言类型提取出来map，依次遍历列表
 * 我们采取第一种方式
 */

public class PredicateUtils {
    /**
     * 返回一个路由，表示符合断言的路由
     */
    public static RouteDefinition matchRoute(HttpServletRequest request) {
        Collection<RouteDefinition> routeDefinitions = DandelionController.idRouteMap.values();
        for (RouteDefinition routeDefinition : routeDefinitions) {
            Map<String, PredicateDefinition> predicateDefinitionMap = routeDefinition.getPredicates();
            Collection<PredicateDefinition> predicateDefinitions = predicateDefinitionMap.values();
            for (PredicateDefinition predicateDefinition : predicateDefinitions) {
                if (predicateDefinition.predicate(routeDefinition, request)) {
                    return routeDefinition;
                }
            }
        }
        return null;
    }

    /**
     * 将路径的/**进行处理，返回干净的路径
     */
    public static String stripTail(String path) {
        if (path.endsWith("**")) {
            return path.substring(0, path.length() - 2);
        } else {
            return path;
        }
    }
}
