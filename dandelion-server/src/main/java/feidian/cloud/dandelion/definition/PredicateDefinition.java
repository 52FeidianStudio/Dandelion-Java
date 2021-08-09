package feidian.cloud.dandelion.definition;

import feidian.cloud.dandelion.predicate.RoutePredicateBase;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.util.StringUtils.tokenizeToStringArray;

/**
 * @author zrl
 * @date 2021-08-07 9:52
 */
@Validated
@Data
public class PredicateDefinition implements RoutePredicateBase {
    /**
     * 断言的名字
     */
    private String name;
    /**
     * 配置的信息
     */
    private List<String> args = new ArrayList<>();

    /**
     * 默认实现的返回结果是false
     * @param routeDefinition 是待校验的路由对象
     * @param toCheck 是要检验的信息
     * @return
     */
    @Override
    public boolean predicate(RouteDefinition routeDefinition, String toCheck) {
        return false;
    }
}
