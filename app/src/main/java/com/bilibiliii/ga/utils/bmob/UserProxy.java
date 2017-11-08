package com.bilibiliii.ga.utils.bmob;

import android.content.SharedPreferences;

import com.bilibiliii.ga.bean.User;
import com.bilibiliii.ga.utils.bmob.I.IUserProxy;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * @author No.47 create at 2017/11/8.
 */
public class UserProxy implements IUserProxy {
    private String TAG = getClass().getSimpleName();
    private static UserProxy instance;

    public static UserProxy getInstance() {
        if (instance == null) {
            instance = new UserProxy();
        }
        return instance;
    }

    @Override
    public void login(String username, String password, final CallBack callBack) {
        User user = new User.UserBuilder()
                .setUsername(username)
                .setPassword(password)
                .build();
        user.login(new SaveListener<User>() {
            @Override
            public void done(User s, BmobException e) {
                if (e == null) {
                    callBack.onSuccess(s);
                } else {
                    callBack.onFail();
                }
            }
        });
    }

    @Override
    public void register(User user, final CallBack callBack) {
        user.signUp(new SaveListener<User>() {
            @Override
            public void done(User s, BmobException e) {
                if (e == null) {
                    callBack.onSuccess(s);
                } else {
                    callBack.onFail();
                }
            }
        });
    }

    @Override
    public void register(String username, String password, String email, final CallBack callBack) {
        User user = new User.UserBuilder()
                .setUsername(username)
                .setPassword(password)
                .setEmail(email)
                .build();
        register(user, callBack);
    }

    @Override
    public void updateUser() {

    }

    @Override
    public void changePSW() {

    }

    @Override
    public void queryUser() {

    }

    @Override
    public void logOut() {

    }

    @Override
    public User getCurrentUser() {
        return null;
    }

    @Override
    public void saveUserInfo(User user) {

    }

}
