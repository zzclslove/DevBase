package com.devapp.base;

import android.app.Application;
import android.support.v4.view.ViewPager;

import com.devapp.data.InitData;

import java.io.Serializable;

public class Token extends Application implements Serializable {

    private static final long serialVersionUID = 5042251448446037356L;
    
    private String rootUrl;
    private boolean logined;
    private int windowWidth;
    private int windowHeight;
    private int productImageHeight;
    private int productImageWidth;
    private double productImageScale;   //产品相关图片宽高比
    private InitData initData;
    private ViewPager loginStepViewPager;
    private String phoneNumber;
    private int sendCodeTimeLeft;

    @Override
    public void onCreate()
    {
        super.onCreate();
    }

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

    public boolean isLogined() {
        return logined;
    }

    public void setLogined(boolean logined) {
        this.logined = logined;
    }

    public ViewPager getLoginStepViewPager() {
        return loginStepViewPager;
    }

    public void setLoginStepViewPager(ViewPager loginStepViewPager) {
        this.loginStepViewPager = loginStepViewPager;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getSendCodeTimeLeft() {
        return sendCodeTimeLeft;
    }

    public void setSendCodeTimeLeft(int sendCodeTimeLeft) {
        this.sendCodeTimeLeft = sendCodeTimeLeft;
    }
}
