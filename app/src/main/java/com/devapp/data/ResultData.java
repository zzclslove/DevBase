package com.devapp.data;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/3.
 */
public class ResultData implements Serializable {

    private static final long serialVersionUID = -8906778229424595497L;

    private boolean result;
    private boolean next;
    private String data;

    public boolean isNext() {
        return next;
    }

    public void setNext(boolean next) {
        this.next = next;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
