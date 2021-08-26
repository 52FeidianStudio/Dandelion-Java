package feidian.cloud.dandelion.predicate;

import feidian.cloud.dandelion.definition.PredicateBase;
import feidian.cloud.dandelion.definition.PredicateDefinition;
import feidian.cloud.dandelion.definition.RouteDefinition;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

/**
 * 路径断言器
 * @author Zhang Ruilong
 * @email 970586718@qq.com
 * @date 2021-08-09 15:43
 */
@NoArgsConstructor
public class PathPredicate extends PredicateBase implements PredicateDefinition {

    public PathPredicate(List<String> args) {
        init();
        setArgs(args);
    }

    @Override
    public void init() {
        this.name = "Path";
        this.des = "路径匹配断言器";
    }

    @Override
    public void setArgs(List args) {
        this.args = args;
    }


    @Override
    public boolean predicate(RouteDefinition routeDefinition, HttpServletRequest request) {
        Collection<PredicateDefinition> predicates = routeDefinition.getPredicates().values();
        //遍历断言器列表
        for (PredicateDefinition predicate : predicates) {
            //找到这个类型的断言
            if (predicate.getName().equals(this.getName())) {
                boolean check = check(predicate.getArgs(), request.getRequestURI());
                if (check) {
                    return true;
                }
            }
        }
        //遍历的时候没返回true，就返回false
        return false;
    }

    /**
     * 用来检查的辅助函数
     * @param args    待检验的路由中配置好的路径模式
     * @param toCheck 需要被检验的请求路径
     */
    private boolean check(List<String> args, String toCheck) {
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
