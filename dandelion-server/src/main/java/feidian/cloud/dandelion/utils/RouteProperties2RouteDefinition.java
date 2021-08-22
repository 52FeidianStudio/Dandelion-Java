package feidian.cloud.dandelion.utils;

import feidian.cloud.autoconfigure.peoperties.RouteProperties;
import feidian.cloud.dandelion.definition.RouteDefinition;
import org.springframework.beans.BeanUtils;

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
        //将String类型的断言配置，转化成PredicateDefinition
        PredicateUtils.changePredicateConfiguration(routeProperties,routeDefinition);
        //todo 转化过滤
        return routeDefinition;
    }


}
