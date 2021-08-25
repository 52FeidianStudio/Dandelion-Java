package feidian.cloud.dandelion.definition;

import feidian.cloud.dandelion.predicate.PathPredicate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;


/**
 * @author zrl
 * @email 970586718@qq.com
 * @date 2021-08-07 9:36
 * 这是路由类，包含路由的所有配置信息
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RouteDefinition {
    /**
     * 路由id，默认是uuid
     */
    private String id = UUID.randomUUID().toString();
    /**
     * 服务的url
     */
    private String url = "localhost";
    /**
     * 排序
     */
    private int order = 0;
    /**
     * 是否是通过引入starter依赖添加的配置，用于判断是否要进行心跳检查
     */
    private boolean client = true;
    /**
     * 断言
     */
    private Map<String,PredicateDefinition> predicates = new HashMap<>();

    /**
     * 过滤器
     */
    private Map<String,FilterDefinition> filters = new HashMap<>();

}
