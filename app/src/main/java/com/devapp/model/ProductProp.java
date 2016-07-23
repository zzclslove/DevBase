package com.devapp.model;

import java.io.Serializable;

public class ProductProp implements Serializable {

    private static final long serialVersionUID = 4838288823163629953L;

    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
