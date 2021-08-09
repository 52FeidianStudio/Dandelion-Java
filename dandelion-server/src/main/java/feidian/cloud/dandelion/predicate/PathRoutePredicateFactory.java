package feidian.cloud.dandelion.predicate;

import feidian.cloud.dandelion.definition.PredicateDefinition;
import feidian.cloud.dandelion.definition.RouteDefinition;
import java.util.List;

/**
 * 路径断言
 * @author Zhang Ruilong
 * @email 970586718@qq.com
 * @date 2021-08-09 15:43
 */

public class PathRoutePredicateFactory extends PredicateDefinition {

    public PathRoutePredicateFactory() {
        this.setName("PathRoutePredicateFactory");
    }

    @Override
    public boolean predicate(RouteDefinition routeDefinition, String toCheck) {
        String name = this.getName();
        List<PredicateDefinition> predicates = routeDefinition.getPredicates();
        //遍历列表，判断要检查的路径
        for (PredicateDefinition predicate : predicates) {
            if (predicate.getName().equals(this.getName())) {
                List<String> args = predicate.getArgs();
                return check(args, toCheck);
            }
        }
        //遍历的时候没返回true，就返回false
        return false;
    }

    /**
     * 用来检查的辅助函数
     * @param args 待检验的路由中配置好的路径模式
     * @param toCheck  需要被检验的请求路径
     * @return
     */
    private boolean check(List<String> args,String toCheck) {
        for (String arg : args) {
            String begin = arg;
            if (arg.endsWith("/**")) {
                //去掉/**后的路径，判断时候
                begin = arg.substring(0, arg.length() - 3);
            }
            if (toCheck.startsWith(begin)) {
                return true;
            }
        }
        return false;
    }
}
