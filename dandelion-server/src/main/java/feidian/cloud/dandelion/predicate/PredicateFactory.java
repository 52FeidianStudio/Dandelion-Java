package feidian.cloud.dandelion.predicate;

import feidian.cloud.dandelion.definition.PredicateDefinition;

import java.util.List;
import java.util.ServiceLoader;

/**
 * @author Zhang Ruilong
 * @email 970586718@qq.com
 * @date 2021-08-16 21:29
 * @description 根据字符串获取断言类，使用了SPI机制
 * 教程可以参看：https://www.cnblogs.com/strongmore/p/13284433.html
 */

public class PredicateFactory {
    public static PredicateDefinition getInstance(String type, List<String> args) {
        ServiceLoader<PredicateDefinition> serviceLoader = ServiceLoader.load(PredicateDefinition.class);
        for (PredicateDefinition predicateDefinition : serviceLoader) {
            String name = predicateDefinition.getName();
            if (name.equals(type)) {
                predicateDefinition.setConfig(args);
                return predicateDefinition;
            }
        }
        return null;
    }
    public static PredicateDefinition getInstance(String type, List<String> args,String remark) {
        ServiceLoader<PredicateDefinition> serviceLoader = ServiceLoader.load(PredicateDefinition.class);
        for (PredicateDefinition predicateDefinition : serviceLoader) {
            String name = predicateDefinition.getName();
            if (name.equals(type)) {
                predicateDefinition.setRemark(remark);
                predicateDefinition.setConfig(args);
                return predicateDefinition;
            }
        }
        return null;
    }
}
