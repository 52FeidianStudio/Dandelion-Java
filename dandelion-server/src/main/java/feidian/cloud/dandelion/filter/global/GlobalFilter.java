package feidian.cloud.dandelion.filter.global;

import feidian.cloud.dandelion.filter.GatewayFilterChain;
import org.springframework.web.server.ServerWebExchange;

/**
 * @author Zhang Ruilong
 * @email 970586718@qq.com
 * @date 2021-08-13 15:37
 * @description 这是全局过滤器要实现的接口
 */

public interface GlobalFilter {
    /**
     * todo 返回值和参数希先不管呢，全局过滤器的设计也先不管
     *
     * @param exchange
     * @param chain
     */
    void filter(ServerWebExchange exchange, GatewayFilterChain chain);
}
