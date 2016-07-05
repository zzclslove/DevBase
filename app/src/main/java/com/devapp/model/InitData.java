package com.devapp.model;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/5.
 */
public class InitData {

    private String httpRootUrl;
    private List<Topic> topicList;
    private RecommendProducts recommendProducts;
    private List<Category> categoryList;
    private Map findingProductList;
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

    public String getHttpRootUrl() {
        return httpRootUrl;
    }

    public void setHttpRootUrl(String httpRootUrl) {
        this.httpRootUrl = httpRootUrl;
    }
}
