package com.devapp.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/14.
 */
public class CartProduct {

    private int productId;
    private int num;
    private String productName;
    private String thumb;
    private BigDecimal price;
    private BigDecimal discount;
    private List<ProductProp> props;
    private String propsStr;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public List<ProductProp> getProps() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<ProductProp>>(){}.getType();
        List<ProductProp> propsList = gson.fromJson(propsStr, type);
        return propsList;
    }

    public void setProps(List<ProductProp> props) {
        this.props = props;
    }

    public String getPropsStr() {
        return propsStr;
    }

    public void setPropsStr(String propsStr) {
        this.propsStr = propsStr;
    }

}
