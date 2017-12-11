package com.bilibiliii.ga.bean;

/**
 * @author No.47 create at 2017/11/20.
 */
public class NewFriend implements java.io.Serializable{

    private Long id;

    private String uid;

    private String msg;

    private String name;

    private String avatar="tmp empty";

    private Integer status;

    private Long time;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar  ="tmp empty";
    }
    public NewFriend(){

    }
    public NewFriend(Long id, String uid, String msg, String name, String avatar, Integer status, Long time) {
        this.id = id;
        this.uid = uid;
        this.msg = msg;
        this.name = name;
        this.avatar ="tmp empty";
        this.status = status;
        this.time = time;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}