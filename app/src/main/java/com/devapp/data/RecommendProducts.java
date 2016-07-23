package com.devapp.data;

import com.devapp.model.Product;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/7/5.
 */
public class RecommendProducts implements Serializable {

    private static final long serialVersionUID = -5316913991909691631L;

    private List<Product> hotProductList;
    private List<Product> bestProductList;
    private List<Product> newProductList;

    public List<Product> getHotProductList() {
        return hotProductList;
    }

    public void setHotProductList(List<Product> hotProductList) {
        this.hotProductList = hotProductList;
    }

    public List<Product> getBestProductList() {
        return bestProductList;
    }

    public void setBestProductList(List<Product> bestProductList) {
        this.bestProductList = bestProductList;
    }

    public List<Product> getNewProductList() {
        return newProductList;
    }

    public void setNewProductList(List<Product> newProductList) {
        this.newProductList = newProductList;
    }
}
