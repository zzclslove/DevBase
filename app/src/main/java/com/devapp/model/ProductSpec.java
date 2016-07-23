package com.devapp.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/7/22.
 */
public class ProductSpec implements Serializable {

    private static final long serialVersionUID = 3127471827352607078L;

    private int attr_id;
    private int attr_type;
    private String name;
    private List<ProductSpecValue> values;

    public int getAttr_id() {
        return attr_id;
    }

    public void setAttr_id(int attr_id) {
        this.attr_id = attr_id;
    }

    public int getAttr_type() {
        return attr_type;
    }

    public void setAttr_type(int attr_type) {
        this.attr_type = attr_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductSpecValue> getValues() {
        return values;
    }

    public void setValues(List<ProductSpecValue> values) {
        this.values = values;
    }
}
