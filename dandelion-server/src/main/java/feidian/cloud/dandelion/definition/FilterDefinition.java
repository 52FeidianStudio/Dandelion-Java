package feidian.cloud.dandelion.definition;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zrl
 * @date 2021-08-07 9:56
 */

public interface FilterDefinition {
    String name = null;
    Map<String, String> args = new LinkedHashMap<>();
}
