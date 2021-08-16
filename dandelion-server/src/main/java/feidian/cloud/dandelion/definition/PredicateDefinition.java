package feidian.cloud.dandelion.definition;


/**
 * @author zrl
 * @date 2021-08-07 9:52
 */

public interface PredicateDefinition {
    /**
     * 默认实现的返回结果是false
     * @param routeDefinition 是待校验的路由对象
     * @param toCheck         是要检验的信息
     * @return
     */
    public boolean predicate(RouteDefinition routeDefinition, String toCheck);
}
