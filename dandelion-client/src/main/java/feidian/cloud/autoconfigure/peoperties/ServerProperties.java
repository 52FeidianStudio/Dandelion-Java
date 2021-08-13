package feidian.cloud.autoconfigure.peoperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Zhang Ruilong
 * @email 970586718@qq.com
 * @date 2021-08-08 21:41
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@ConfigurationProperties(prefix = "dandelion.server")
public class ServerProperties {
    /**
     * 服务器地址
     */
    private String serverAddr = "http://localhost:8000";
    /**
     * 连接超时时间默认为3秒
     */
    private Integer timeOut = 3000;
}
