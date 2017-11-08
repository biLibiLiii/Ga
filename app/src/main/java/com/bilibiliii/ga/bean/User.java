package com.bilibiliii.ga.bean;

import cn.bmob.v3.BmobUser;

/**
 * @author No.47 create at 2017/11/8.
 */
public class User extends BmobUser {

    public User(UserBuilder userBuilder) {
        this.setUsername(userBuilder.username);
        this.setPassword(userBuilder.password);
        this.setEmail(userBuilder.email);
    }

    public static class UserBuilder{
        private String username;
        private String password;
        private String email;

        public UserBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public User build(){
            return new User(this);
        }
    }
}
