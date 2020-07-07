package com.test.firestorememo.model;

import java.io.Serializable;

public class Data implements Serializable {

    private String title;
    private String memo;
    private String id;

    public Data(){
    }

    public Data(String title, String memo) {
        this.title = title;
        this.memo = memo;
    }

    public Data(String title, String memo, String id) {
        this.title = title;
        this.memo = memo;
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
