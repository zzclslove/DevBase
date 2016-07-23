package com.devapp.data;

import com.devapp.model.Product;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/7/9.
 */
public class ResultProducts implements Serializable {

    private static final long serialVersionUID = -7945714090623058395L;

    private int page_count;
    private int record_count;
    private List<Product> goods;

    public int getPage_count() {
        return page_count;
    }

    public void setPage_count(int page_count) {
        this.page_count = page_count;
    }

    public int getRecord_count() {
        return record_count;
    }

    public void setRecord_count(int record_count) {
        this.record_count = record_count;
    }

    public List<Product> getGoods() {
        return goods;
    }

    public void setGoods(List<Product> goods) {
        this.goods = goods;
    }
}
