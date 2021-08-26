package feidian.cloud.dandelion.add;

import java.util.List;

public class PredicateList {
    private String id;
    private String predicate_name;
    private String des;
    private String remark;
    private List<String> config;

    public PredicateList(String id, String predicate_name, String des, String remark, List<String> config) {
        this.id = id;
        this.predicate_name = predicate_name;
        this.des = des;
        this.remark = remark;
        this.config = config;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPredicate_name() {
        return predicate_name;
    }

    public void setPredicate_name(String predicate_name) {
        this.predicate_name = predicate_name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<String> getConfig() {
        return config;
    }

    public void setConfig(List<String> config) {
        this.config = config;
    }
}
