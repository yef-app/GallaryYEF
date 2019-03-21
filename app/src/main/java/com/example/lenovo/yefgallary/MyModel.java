package com.example.lenovo.yefgallary;

public class MyModel {

    String pageurl,tag;

    public MyModel(String pageurl, String tag) {
        this.pageurl = pageurl;
        this.tag = tag;
    }

    public String getPageurl() {
        return pageurl;
    }

    public void setPageurl(String pageurl) {
        this.pageurl = pageurl;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
