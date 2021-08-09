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
    /**
     * 初始化的时候，检查服务端是否是开启状态
     * @return
     */
    @Bean
    public void getRoute() {
        String serverAddr = serverProperties.getServerAddr();
        RestTemplate restTemplate = getRestTemplate();
        /**
         * 将返回结果通过map解析
         */
        Map<String, Object> response =
                restTemplate.getForObject(serverAddr, Map.class);
        /**
         * 如果连接超时就抛出异常
         */
    }
    @Bean
    public RestTemplate getRestTemplate() {
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        /**
         * 从自动配置中获取超时时间
         */
        Integer timeOut = serverProperties.getTimeOut();
        /**
         * ConnectTimeout： 链接建立的超时时间；
         */
        httpRequestFactory.setConnectionRequestTimeout(timeOut);
        return new RestTemplate(httpRequestFactory);
    }
}
