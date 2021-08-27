package feidian.cloud.dandelion.vo;

import feidian.cloud.dandelion.definition.PredicateDefinition;
import lombok.Data;

import java.util.List;

/**
 * @author Zhang Ruilong
 * @email 970586718@qq.com
 * @date 2021-08-26 15:05
 * @description 实体类的vo，构造方法使用routeId和PredicateDefinition
 */
@Data
public class PredicateVO <T>{
    private String id;
    private String predicate_name;
    private String des;
    private String remark;
    private List<T> config;

    public PredicateVO(String routeId, PredicateDefinition predicateDefinition) {
        this.id = routeId;
        this.predicate_name = predicateDefinition.getName();
        this.des = predicateDefinition.getDes();
        this.remark = predicateDefinition.getRemark();
        this.config = predicateDefinition.getConfig();
    }
}
