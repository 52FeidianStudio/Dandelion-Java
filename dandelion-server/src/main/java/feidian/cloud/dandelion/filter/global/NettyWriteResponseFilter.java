package feidian.cloud.dandelion.filter.global;

import feidian.cloud.dandelion.filter.GatewayFilterChain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.server.ServerWebExchange;

/**
 * @author Zhang Ruilong
 * @email 970586718@qq.com
 * @date 2021-08-13 15:41
 * @description 一个全局过滤器的实现模板
 */
@Slf4j
public class NettyWriteResponseFilter implements GlobalFilter, Ordered {

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public void filter(ServerWebExchange exchange, GatewayFilterChain chain) {

    }
}
