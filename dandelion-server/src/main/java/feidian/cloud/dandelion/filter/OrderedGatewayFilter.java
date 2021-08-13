package feidian.cloud.dandelion.filter;

import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;

/**
 * @author Zhang Ruilong
 * @email 970586718@qq.com
 * @date 2021-08-13 15:55
 * @description
 */

public class OrderedGatewayFilter implements GatewayFilter, Ordered {
    private final GatewayFilter delegate;
    private final int order;

    public OrderedGatewayFilter(GatewayFilter delegate, int order) {
        this.delegate = delegate;
        this.order = order;
    }

    @Override
    public void filter(ServerWebExchange exchange, GatewayFilterChain chain) {

    }

    @Override
    public int getOrder() {
        return 0;
    }
}
