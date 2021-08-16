package feidian.cloud.dandelion.predicate;

import feidian.cloud.dandelion.definition.PredicateDefinition;

import java.util.List;

/**
 * @author Zhang Ruilong
 * @email 970586718@qq.com
 * @date 2021-08-16 21:29
 * @description 根据字符串获取断言类
 */

public class PredicateFactory {
    public static PredicateDefinition getInstance(String type, List<String> args) {
        if ("Path".equals(type)) {
            return new PathPredicate(args);
        } else {
            return null;
        }
    }
}
