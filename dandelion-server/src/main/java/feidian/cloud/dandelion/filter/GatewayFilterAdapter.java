package feidian.cloud.dandelion.filter;

import feidian.cloud.dandelion.filter.global.GlobalFilter;
import lombok.ToString;
import org.springframework.web.server.ServerWebExchange;

/**
 * @author Zhang Ruilong
 * @email 970586718@qq.com
 * @date 2021-08-13 15:52
 * @description 适配器，在网关过滤器链 GatewayFilterChain 中会使用 GatewayFilter 过滤请求，
 * 全局过滤器适配成网关过滤器 GatewayFilter
 */
@ToString
public class GatewayFilterAdapter implements GatewayFilter {

    private final GlobalFilter delegate;

    public GatewayFilterAdapter(GlobalFilter delegate) {
        this.delegate = delegate;
    }

    @Override
    public void filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        this.delegate.filter(exchange, chain);
    }
}
