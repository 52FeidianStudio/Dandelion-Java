package feidian.cloud.dandelion.filter.global;

/**
 * @author Zhang Ruilong
 * @email 970586718@qq.com
 * @date 2021-08-13 15:39
 * @description 用来排序的接口
 */

public interface Ordered {
    int HIGHEST_PRECEDENCE = -2147483648;
    int LOWEST_PRECEDENCE = 2147483647;

    int getOrder();
}
