package com.devapp.model;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2016/7/22.
 */
public class ProductSpecValue {

    private String label;
    private BigDecimal price;
    private int id;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
