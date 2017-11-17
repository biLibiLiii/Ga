package com.bilibiliii.ga.utils.bmob.I;

import com.bilibiliii.ga.bean.User;
import com.bilibiliii.ga.utils.bmob.CallBack;

import java.util.List;

import cn.bmob.v3.BmobUser;

/**
 * @author No.47 create at 2017/11/8.
 */
public interface IUserProxy {
    int STATE_DISCONNECT = 0;
    int STATE_CONNECTING = 1;
    int STATE_CONNECTED = 2;
    int STATE_NETWORK_ERROR = -1;
    int STATE_KICK_OFF_BY_OTHERS = -2;

    void login(String username, String password, CallBack<User> callBack);

    void register(User user, CallBack<User> callBack);

    void register(String username, String password, CallBack<User> callBack);

    void updateUser(String email, final CallBack<User> callBack);

    void changePSW(String oldPsw, String newPsw, CallBack<User> callBack);

    void queryUser(String username, CallBack<List<User>> callBack);

    void queryUserById(String userID, CallBack<User> callBack);

    void logOut();

    User getCurrentUser();

    void reLoginIM();

    void logOutIM();
}
