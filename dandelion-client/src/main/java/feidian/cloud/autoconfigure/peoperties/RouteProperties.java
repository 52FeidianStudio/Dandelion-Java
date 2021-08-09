package feidian.cloud.autoconfigure.peoperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.UUID;

/**
 * @author Zhang Ruilong
 * @email 970586718@qq.com
 * @date 2021-08-08 19:28
 * Route类的配置属性
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@ConfigurationProperties(prefix = "dandelion.route")
public class RouteProperties {
    /**
     * 路由id，默认是uuid
     */
    private String id = UUID.randomUUID().toString();
    /**
     * uri
     */
    private String url = "localhost";
    /**
     * 排序
     */
    private int order = 0;
    /**
     * 是否是通过引入starter依赖添加的配置，用于判断是否要进行心跳检查
     */
    private boolean isFormClient = true;
}
