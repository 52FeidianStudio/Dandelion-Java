package feidian.cloud.dandelion.definition;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zrl
 * @date 2021-08-07 9:52
 */

public interface PredicateDefinition {

    List<String> getArgs();
    String getName();
    /**
     * 默认实现的返回结果是false
     * @param routeDefinition 是待校验的路由对象
     * @param request         封装了要检验的信息
     * @return
     */
    boolean predicate(RouteDefinition routeDefinition, HttpServletRequest request);
}
