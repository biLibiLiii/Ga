package com.bilibiliii.ga.bean;

/**
 * Created by Administrator on 2017/11/8.
 */

public class Msg {
    public static final int TYPE_RECEIVED=0;
    public static final int TYPE_SEND=1;
    private  String content;

    public int getType() {
        return type;
    }

    private int type;

    public Msg(String content, int type) {
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
