package com.devapp.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/4.
 */
public class Topic implements Serializable {

    private static final long serialVersionUID = 5873363203180152961L;

    private int topic_id;
    private String topic_img;
    private String title;

    public int getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }

    public String getTopic_img() {
        return topic_img;
    }

    public void setTopic_img(String topic_img) {
        this.topic_img = topic_img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
