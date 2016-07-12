package com.devapp.base;

import android.app.Application;

import com.devapp.model.InitData;

public class Token extends Application {

    @Override
    public void onCreate()
    {
        super.onCreate();
    }

    private String rootUrl;

    private int windowWidth;
    private int windowHeight;
    private int productImageHeight;
    private int productImageWidth;
    private double productImageScale;   //产品相关图片宽高比
    private InitData initData;

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

    public String getRootUrl() {
        return rootUrl;
    }

    public void setRootUrl(String rootUrl) {
        this.rootUrl = rootUrl;
    }

    public InitData getInitData() {
        return initData;
    }

    public void setInitData(InitData initData) {
        this.initData = initData;
    }

    public int getProductImageHeight() {
        return productImageHeight;
    }

    public void setProductImageHeight(int productImageHeight) {
        this.productImageHeight = productImageHeight;
    }

    public int getProductImageWidth() {
        return productImageWidth;
    }

    public void setProductImageWidth(int productImageWidth) {
        this.productImageWidth = productImageWidth;
    }
}
