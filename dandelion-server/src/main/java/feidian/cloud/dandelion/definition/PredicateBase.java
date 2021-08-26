package feidian.cloud.dandelion.definition;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.List;

/**
 * @author Zhang Ruilong
 * @email 970586718@qq.com
 * @date 2021-08-26 15:15
 * @description
 */
@Data
public class PredicateBase <T>{
    /**
     * 断言的名字
     */
    public String name;
    /**
     * 断言的描述
     */
    public String des;
    /**
     * 这个路由断言的备注
     */
    public String remark;
    /**
     * 配置的信息
     */
    @Setter(AccessLevel.NONE)
    public List<T> args;
}
