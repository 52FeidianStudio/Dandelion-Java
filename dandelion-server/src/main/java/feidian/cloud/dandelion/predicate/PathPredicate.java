package feidian.cloud.dandelion.predicate;

import feidian.cloud.dandelion.definition.PredicateDefinition;
import feidian.cloud.dandelion.definition.RouteDefinition;
import lombok.Data;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

/**
 * 路径断言
 *
 * @author Zhang Ruilong
 * @email 970586718@qq.com
 * @date 2021-08-09 15:43
 */
@Data
public class PathPredicate implements PredicateDefinition {
    /**
     * 断言的名字
     */
    private String name="Path";
    /**
     * 断言的描述
     */
    private String des="路径匹配断言器";
    /**
     * 这个路由断言的备
     */
    private String remark;
    /**
     * 配置的信息
     */
    private List<String> args;
    public PathPredicate(List<String> args) {
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
     * @return
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
