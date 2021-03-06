package com.bilibiliii.ga.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Cherie_No.47 on 2016/7/10 07:45.
 * Email jascal@163.com
 */
public class Murmur extends BmobObject {
    private String content;
    private User creater;
    private String imageUri;
    private String createrName;

    public String getCreaterName() {
        return createrName;
    }

    public void setCreaterName(String createrName) {
        this.createrName = createrName;
    }

    private int favor;

    public Murmur(){
        this.setTableName("Murmur");
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getCreater() {
        return creater;
    }

    public void setCreater(User creater) {
        this.creater = creater;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public int getFavor() {
        return favor;
    }

    public void setFavor(int favor) {
        this.favor = favor;
    }
}
