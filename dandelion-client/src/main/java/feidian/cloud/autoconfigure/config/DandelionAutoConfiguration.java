package feidian.cloud.autoconfigure.config;

import feidian.cloud.autoconfigure.peoperties.RouteProperties;
import feidian.cloud.autoconfigure.peoperties.ServerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author Zhang Ruilong
 * @email 970586718@qq.com
 * @date 2021-08-08 19:29
 */

//@ConditionalOnClass(EnableConfigurationProperties)
@Configuration
@EnableConfigurationProperties({RouteProperties.class,
        ServerProperties.class})
public class DandelionAutoConfiguration {
    @Autowired
    RouteProperties routeProperties;
    @Autowired
    ServerProperties serverProperties;
}
