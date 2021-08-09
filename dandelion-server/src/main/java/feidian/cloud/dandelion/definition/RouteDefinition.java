package feidian.cloud.dandelion.definition;

import com.sun.istack.internal.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * @author zrl
 * @email 970586718@qq.com
 * @date 2021-08-07 9:36
 *这是路由类，包含路由的所有配置信息
 */
//@Validated
@NoArgsConstructor
@Data
public class RouteDefinition {
    /**
     * 路由id，默认是uuid
     */
    //@NotEmpty
    private String id = UUID.randomUUID().toString();

    /**
     * 断言
     */
    //@NotEmpty
    //@Valid
    private List<PredicateDefinition> predicates = new ArrayList<>();

    /**
     *过滤器
     */
    //@Valid
    private List<FilterDefinition> filters = new ArrayList<>();

    /**
     * 服务的url
     */
    //@NotNull
    private String url;

    /**
     * 排序
     */
    private int order = 0;

    /**
     * 是否是通过引入starter依赖添加的配置，用于判断是否要进行心跳检查
     */
    private boolean isStarter = true;
}
