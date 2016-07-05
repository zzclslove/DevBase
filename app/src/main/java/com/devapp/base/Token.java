package com.devapp.base;

import android.app.Application;

import com.devapp.model.Cart;
import com.devapp.model.Category;
import com.devapp.model.Topic;
import org.xutils.x;
import java.util.List;
import java.util.Map;

public class Token extends Application {

    @Override
    public void onCreate()
    {
        super.onCreate();
        x.Ext.init(this);
    }

    private String rootUrl;

    private int windowWidth;
    private int windowHeight;
    private double productImageScale;   //产品相关图片宽高比
    private List<Category> categoryList;
    private List<Topic> topicList;
    private Map findingProductList;
    private Cart cart;

    public int getWindowWidth() {
        return windowWidth;
    }

    public void setWindowWidth(int windowWidth) {
        this.windowWidth = windowWidth;
    }

    public int getWindowHeight() {
        return windowHeight;
    }

    public void setWindowHeight(int windowHeight) {
        this.windowHeight = windowHeight;
    }

    public double getProductImageScale(){
        return productImageScale;
    }

    public void setProductImageScale(double productImageScale){
        this.productImageScale = productImageScale;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public String getRootUrl() {
        return rootUrl;
    }

    public void setRootUrl(String rootUrl) {
        this.rootUrl = rootUrl;
    }

    public List<Topic> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<Topic> topicList) {
        this.topicList = topicList;
    }

    public Map getFindingProductList() {
        return findingProductList;
    }

    public void setFindingProductList(Map findingProductList) {
        this.findingProductList = findingProductList;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
