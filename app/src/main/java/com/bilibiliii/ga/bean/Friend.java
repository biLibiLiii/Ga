package com.bilibiliii.ga.bean;

import cn.bmob.v3.BmobObject;

/**
 * @author No.47 create at 2017/11/17.
 */
public class Friend extends BmobObject{
    private User user;
    private User friendUser;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getFriendUser() {
        return friendUser;
    }

    public void setFriendUser(User friendUser) {
        this.friendUser = friendUser;
    }
}
