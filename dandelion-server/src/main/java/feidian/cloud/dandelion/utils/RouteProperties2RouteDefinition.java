package feidian.cloud.dandelion.utils;

import cn.hutool.core.bean.BeanUtil;
import com.sun.org.apache.bcel.internal.generic.NEW;
import feidian.cloud.autoconfigure.peoperties.RouteProperties;
import feidian.cloud.dandelion.definition.PredicateDefinition;
import feidian.cloud.dandelion.definition.RouteDefinition;
import feidian.cloud.dandelion.predicate.PathRoutePredicateFactory;
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
        //todo 转化断言

        //todo 转化过滤

        return routeDefinition;
    }
    /**
     * 将字符串列表转化成断言列表
     */
    public static List<PredicateDefinition> predicateConversion(List<String> predicateStringList) {
        List<PredicateDefinition> predicateDefinitionList = new ArrayList<>();
        for (String s : predicateStringList) {

        }
        return predicateDefinitionList;
    }
    /**
     * 根据字符串获取对应的断言类
     */
    public static PredicateDefinition getPredicateDefinition(String properties) {
        //处理字符串，先去除空格
        //properties = properties.replace(" ","");
        //获取断言头
        String[] split = properties.split("=");
        String title = split[0];
        //获取断言内容
        List<String> content = Arrays.asList(split);
        //删除最前面的断言头
        content.remove(0);
        //根据断言头，使用简单工厂方式获取断言类
        PathRoutePredicateFactory pathRoutePredicateFactory = new PathRoutePredicateFactory();
        return pathRoutePredicateFactory;
    }
}
