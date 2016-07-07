package com.devapp.model;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/5.
 */
public class InitData {

    private int productImageHeight;
    private int productImageWidth;
    private String httpRootUrl;
    private List<Topic> topicList;
    private RecommendProducts recommendProducts;
    private List<Product> findingProductList;
    private List<Category> categoryList;
    private Cart cart;

    public List<Topic> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<Topic> topicList) {
        this.topicList = topicList;
    }

    public RecommendProducts getRecommendProducts() {
        return recommendProducts;
    }

    public void setRecommendProducts(RecommendProducts recommendProducts) {
        this.recommendProducts = recommendProducts;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public String getHttpRootUrl() {
        return httpRootUrl;
    }

    public void setHttpRootUrl(String httpRootUrl) {
        this.httpRootUrl = httpRootUrl;
    }

    public int getProductImageWidth() {
        return productImageWidth;
    }

    public void setProductImageWidth(int productImageWidth) {
        this.productImageWidth = productImageWidth;
    }

    public int getProductImageHeight() {
        return productImageHeight;
    }

    public void setProductImageHeight(int productImageHeight) {
        this.productImageHeight = productImageHeight;
    }

    public List<Product> getFindingProductList() {
        return findingProductList;
    }

    public void setFindingProductList(List<Product> findingProductList) {
        this.findingProductList = findingProductList;
    }
}
