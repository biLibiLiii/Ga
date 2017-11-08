package com.bilibiliii.ga.utils.bmob.I;

import com.bilibiliii.ga.bean.User;
import com.bilibiliii.ga.utils.bmob.CallBack;

/**
 * @author No.47 create at 2017/11/8.
 */
public interface IUserProxy {

    void login(String username, String password, CallBack callBack);

    void register(User user, CallBack callBack);

    void register(String username, String password, String email, CallBack callBack);

    void updateUser();

    void changePSW();

    void queryUser();

    void logOut();

    User getCurrentUser();

    void saveUserInfo(User user);
}
