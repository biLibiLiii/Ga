package com.bilibiliii.ga.utils.bmob;

import com.bilibiliii.ga.bean.User;

/**
 * @author No.47 create at 2017/11/8.
 */
public interface CallBack<T> {

    void onSuccess(T result);

    void onFail(String errorInfo);

}
