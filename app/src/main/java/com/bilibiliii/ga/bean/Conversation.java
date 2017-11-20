package com.bilibiliii.ga.bean;

/**
 * Created by Administrator on 2017/11/14.
 */

public class Conversation {
    private String userName;
    private String time;
    private String lastConversation;

    public Conversation(String userName, String time, String lastConversation) {
        this.userName = userName;
        this.time = time;
        this.lastConversation = lastConversation;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLastConversation() {
        return lastConversation;
    }

    public void setLastConversation(String lastConversation) {
        this.lastConversation = lastConversation;
    }
}
