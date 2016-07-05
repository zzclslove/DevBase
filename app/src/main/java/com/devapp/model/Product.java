package com.devapp.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Product implements Serializable {

    private String title;
    private String description;
    private BigDecimal price;
    private BigDecimal discount;
    private int stock;
    private int sales;
    private String thumb;
    private List<ProductProp> props;
    private String propsStr;

    public Product(String title, String description, BigDecimal price, BigDecimal discount, int stock, int sales ,String thumb)
    {
        this.title = title;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.stock = stock;
        this.sales = sales;
        this.thumb = thumb;
    }

    public Product(String title, BigDecimal price, BigDecimal discount, String thumb){
        this.title = title;
        this.price = price;
        this.discount = discount;
        this.thumb = thumb;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String mainImageUrl) {
        this.thumb = mainImageUrl;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
