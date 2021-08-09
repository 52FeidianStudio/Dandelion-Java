package feidian.cloud.dandelion.predicate;

import feidian.cloud.dandelion.definition.RouteDefinition;

public interface RoutePredicateBase {
    /**
     * @param routeDefinition 是待校验的路由对象
     * @param toCheck 是要检验的信息
     * @return 返回断言成功与否
     */
    boolean predicate(RouteDefinition routeDefinition, String toCheck);

}
