package feidian.cloud.dandelion.definition;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zrl
 * @date 2021-08-07 9:52
 */

public interface PredicateDefinition <T> {
    /**
     * 在init方法中添加断言的name和des
     */
    void init();
    void setName(String name);
    String getName();
    void setRemark(String remark);
    String getRemark();
    void setDes(String des);
    String getDes();

    /**
     * 使用此方法，将字符串类型参数转化为断言所需类型参数
     * @param args 字符串类型参数
     */
    void setArgs(List<String> args);
    List<T> getArgs();
    /**
     * @param routeDefinition 是待校验的路由对象
     * @param request         封装了要检验信息的对象
     */
    boolean predicate(RouteDefinition routeDefinition, HttpServletRequest request);
}
