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
 */

public class PredicateUtils {
    /**
     * 返回一个路由，表示符合断言的路由
     */
    public static RouteDefinition matchRoute(HttpServletRequest request) {
        Collection<RouteDefinition> routeDefinitions = DandelionController.idRouteMap.values();
        for (RouteDefinition routeDefinition : routeDefinitions) {
            List<PredicateDefinition> predicates = routeDefinition.getPredicates();
            for (PredicateDefinition predicate : predicates) {
                if (predicate.predicate(routeDefinition, request)) {
                    return routeDefinition;
                }
            }
        }
        return null;
    }

    ///**
    // * 根据用户请求的路径，返回对应的path断言的路径，然后程序员就可以通过path获取到route对象了
    // */
    //public static String matchPath(String urlPath) {
    //    Set<String> pathKeySet = DandelionController.pathRouteMap.keySet();
    //    /**
    //     * 存放可能出现的路径断言列表，举个例子：
    //     * 访问/order/findById，这时候有两个路径断言：/order/**、/order/findById，我们应该匹配/order/findById
    //     * 不过仔细想想也没人会把这两个路径断言放在不同的服务，因此我们现在就先默认用户是正常的
    //     */
    //    for (String s : pathKeySet) {
    //        if (urlPath.startsWith(stripTail(s))) {
    //            return s;
    //        }
    //    }
    //    return null;
    //}

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
