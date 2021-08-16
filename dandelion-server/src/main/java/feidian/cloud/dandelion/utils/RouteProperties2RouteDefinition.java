package feidian.cloud.dandelion.utils;

import feidian.cloud.autoconfigure.peoperties.RouteProperties;
import feidian.cloud.dandelion.definition.PredicateDefinition;
import feidian.cloud.dandelion.definition.RouteDefinition;
import feidian.cloud.dandelion.predicate.PathPredicate;
import feidian.cloud.dandelion.predicate.PredicateFactory;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Zhang Ruilong
 * @email 970586718@qq.com
 * @date 2021-08-16 17:06
 * @description 这个工具类用来将RouteProperties类转化为RouteDefinition类
 */

public class RouteProperties2RouteDefinition {
    /**
     * 提供一个静态方法，将RouteProperties类转化为RouteDefinition类
     */
    public static RouteDefinition change(RouteProperties routeProperties) {
        RouteDefinition routeDefinition = new RouteDefinition();
        //将routeProperties的属性复制到routeDefinition
        BeanUtils.copyProperties(routeProperties,routeDefinition);
        //转化routeProperties的List<String>断言为List<PredicateDefinition>
        List<PredicateDefinition> predicateDefinitionList = null;
        if (routeProperties.getPredicates()==null) {
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
            predicateDefinitionList = predicateConversion(routeProperties.getPredicates());
        }
        routeDefinition.setPredicates(predicateDefinitionList);
        //todo 转化过滤

        return routeDefinition;
    }
    /**
     * 将字符串列表转化成断言列表
     */
    public static List<PredicateDefinition> predicateConversion(List<String> predicateStringList) {
        List<PredicateDefinition> predicateDefinitionList = new ArrayList<>();
        for (String s : predicateStringList) {
            PredicateDefinition predicateDefinition = getPredicateDefinition(s);
        }
        return predicateDefinitionList;
    }
    /**
     * 根据字符串获取对应的断言类
     */
    public static PredicateDefinition getPredicateDefinition(String properties) {
        //获取断言头
        String[] split = properties.split("=");
        String title = split[0];
        //获取断言内容
        List<String> args = Arrays.asList(split);
        //删除最前面的断言头
        args.remove(0);
        //根据断言头，使用简单工厂方式获取断言类
        PredicateDefinition instance = PredicateFactory.getInstance(title,args);
        return instance;
    }
}
