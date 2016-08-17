package com.devapp.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/17.
 */
public class ProductImage implements Serializable {

    private static final long serialVersionUID = 2666473795298212083L;

    private String url;
    private String title;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
