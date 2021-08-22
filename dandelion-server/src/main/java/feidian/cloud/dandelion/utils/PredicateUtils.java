package feidian.cloud.dandelion.utils;

import feidian.cloud.autoconfigure.peoperties.RouteProperties;
import feidian.cloud.dandelion.controller.DandelionController;
import feidian.cloud.dandelion.definition.PredicateDefinition;
import feidian.cloud.dandelion.definition.RouteDefinition;
import feidian.cloud.dandelion.predicate.PathPredicate;
import feidian.cloud.dandelion.predicate.PredicateFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

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
     * 将String类型的断言配置，转化成PredicateDefinition
     */
    public static void changePredicateConfiguration(RouteProperties routeProperties, RouteDefinition routeDefinition) {
        //转化routeProperties的List<String>断言为List<PredicateDefinition>
        List<PredicateDefinition> predicateDefinitionList = null;
        List<String> predicateStringList = routeProperties.getPredicates();
        if (predicateStringList ==null) {
            //如果没有配置断言，就进行默认配置一个path断言，要不无法进行服务转发
            predicateDefinitionList = new ArrayList<>();
            //创建一个路径断言
            ArrayList<String> args = new ArrayList<>();
            args.add("/"+routeProperties.getId()+"/**");
            PathPredicate pathPredicate = new PathPredicate(args);
            //将断言添加到断言列表中去
            predicateDefinitionList.add(pathPredicate);
        } else {
            //如果有断言配置信息就不用管了
            predicateDefinitionList = new ArrayList<>();
            for (String s : predicateStringList) {
                PredicateDefinition predicateDefinition = getPredicateDefinition(s);
                if (predicateDefinition == null) {
                    //如果没找到就抛出错误
                    throw new RuntimeException("没找到所配置断言类型");
                }
                predicateDefinitionList.add(predicateDefinition);
            }
        }
        ConcurrentHashMap<String, PredicateDefinition> predicateDefinitionMap = new ConcurrentHashMap<>();
        for (PredicateDefinition predicateDefinition : predicateDefinitionList) {
            predicateDefinitionMap.put(predicateDefinition.getName(),predicateDefinition);
        }
        routeDefinition.setPredicates(predicateDefinitionMap);
    }
    /**
     * 根据字符串获取对应的断言类
     */
    public static PredicateDefinition getPredicateDefinition(String properties) {
        //获取断言头
        String[] split = properties.split("=");
        String title = split[0];
        //获取断言内容
        List<String> args = Arrays.asList(split[1].split(","));
        //根据断言头，使用简单工厂方式获取断言类
        PredicateDefinition instance = PredicateFactory.getInstance(title,args);
        return instance;
    }


}
