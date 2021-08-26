package feidian.cloud.dandelion.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author Zhang Ruilong
 * @email 970586718@qq.com
 * @date 2021-08-26 15:05
 * @description
 */
@Data
@AllArgsConstructor
public class PredicateVo <T>{
    private String id;
    private String predicate_name;
    private String des;
    private String remark;
    private List<T> config;
}
