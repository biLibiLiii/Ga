package com.bilibiliii.ga.utils.bmob;

import com.bilibiliii.ga.bean.User;

/**
 * @author No.47 create at 2017/11/8.
 */
public interface CallBack {
    void onSuccess(User user);
    void onFail();
}
