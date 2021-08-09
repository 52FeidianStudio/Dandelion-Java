package feidian.cloud.dandelion.base;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.springframework.util.StringUtils.tokenizeToStringArray;

/**
 * @author zrl
 * @date 2021-08-07 9:52
 */
@Validated
@Data
public class PredicateProperties {
    @NotNull
    private String name;
    private Map<String, String> args = new LinkedHashMap<>();


    public PredicateProperties(String text) {
        int eqIdx = text.indexOf('=');
        if (eqIdx <= 0) {
            throw new ValidationException("Unable to parse PredicateDefinition text '" + text + "'" +
                    ", must be of the form name=value");
        }
    }
}
