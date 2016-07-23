package com.devapp.model;

import java.io.Serializable;
import java.util.List;

public class Cart implements Serializable {

    private static final long serialVersionUID = -8536908520449632328L;

    private String userName;
    private int userId;
    private List<CartProduct> cartProducts;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<CartProduct> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(List<CartProduct> cartProducts) {
        this.cartProducts = cartProducts;
    }
}
