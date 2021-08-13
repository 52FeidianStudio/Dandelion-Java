package feidian.cloud.dandelion.filter;

import org.springframework.web.server.ServerWebExchange;

/**
 * @author Zhang Ruilong
 * @email 970586718@qq.com
 * @date 2021-08-13 15:51
 * @description 是所有类的网关过滤器
 */

public interface GatewayFilter {
    String NAME_KEY = "name";
    String VALUE_KEY = "value";

    /**
     * @param exchange
     * @param chain
     */
    void filter(ServerWebExchange exchange, GatewayFilterChain chain);
}
