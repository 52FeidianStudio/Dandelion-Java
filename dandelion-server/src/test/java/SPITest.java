import feidian.cloud.dandelion.definition.PredicateDefinition;
import org.junit.jupiter.api.Test;

import java.util.ServiceLoader;

/**
 * @author Zhang Ruilong
 * @email 970586718@qq.com
 * @date 2021-08-27 0:35
 * @description
 */

public class SPITest {
    @Test
    public static void main(String[] args) {
        ServiceLoader<PredicateDefinition> serviceLoader = ServiceLoader.load(PredicateDefinition.class);
        for (PredicateDefinition predicateDefinition : serviceLoader) {
            System.out.println(predicateDefinition.hashCode());
        }
        ServiceLoader<PredicateDefinition> serviceLoader1 = ServiceLoader.load(PredicateDefinition.class);
        for (PredicateDefinition predicateDefinition : serviceLoader1) {
            System.out.println(predicateDefinition);
        }
    }
}
