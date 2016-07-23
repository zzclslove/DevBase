package com.devapp.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/21.
 */
public class BuyNote implements Serializable {

    private static final long serialVersionUID = -6105814205666110386L;

    private String user_name;
    private int goods_number;
    private String add_time;
    private int order_status;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getGoods_number() {
        return goods_number;
    }

    public void setGoods_number(int goods_number) {
        this.goods_number = goods_number;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public int getOrder_status() {
        return order_status;
    }

    public void setOrder_status(int order_status) {
        this.order_status = order_status;
    }
}
