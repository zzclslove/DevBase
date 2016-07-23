package com.devapp.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/21.
 */
public class Comment implements Serializable {

    private static final long serialVersionUID = 5629134440929122591L;

    private int id;
    private String email;
    private String username;
    private String content;
    private int rank;
    private String add_time;
    private String re_add_time;
    private String re_content;
    private String re_email;
    private String re_username;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRe_add_time() {
        return re_add_time;
    }

    public void setRe_add_time(String re_add_time) {
        this.re_add_time = re_add_time;
    }

    public String getRe_content() {
        return re_content;
    }

    public void setRe_content(String re_content) {
        this.re_content = re_content;
    }

    public String getRe_email() {
        return re_email;
    }

    public void setRe_email(String re_email) {
        this.re_email = re_email;
    }

    public String getRe_username() {
        return re_username;
    }

    public void setRe_username(String re_username) {
        this.re_username = re_username;
    }
}
