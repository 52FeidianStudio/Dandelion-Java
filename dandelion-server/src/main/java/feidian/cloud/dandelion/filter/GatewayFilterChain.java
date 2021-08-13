package feidian.cloud.dandelion.filter;

import org.springframework.web.server.ServerWebExchange;

/**
 * @author Zhang Ruilong
 * @email 970586718@qq.com
 * @date 2021-08-13 15:43
 * @description
 */

public interface GatewayFilterChain {
    /**
     * todo 返回值先不管
     *
     * @param exchange ServerWebExchange是一个HTTP请求-响应交互的契约。提供对HTTP请求和响应的访问
     */
    void filter(ServerWebExchange exchange);
}
