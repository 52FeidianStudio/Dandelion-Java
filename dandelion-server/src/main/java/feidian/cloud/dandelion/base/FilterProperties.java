package feidian.cloud.dandelion.base;

import lombok.Data;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotNull;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zrl
 * @date 2021-08-07 9:56
 */
@Validated
@Data
public class FilterProperties {
    @NotNull
    private String name;
    private Map<String, String> args = new LinkedHashMap<>();
}
