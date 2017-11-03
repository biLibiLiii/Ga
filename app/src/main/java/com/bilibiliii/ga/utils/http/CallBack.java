package com.bilibiliii.ga.utils.http;

import com.bilibiliii.ga.bean.Result;

/**
 * @author No.47 create at 2017/11/3.
 */
public interface CallBack<T> {
    void onNext(Result<T> value);

    void onError(Throwable e);

    void onComplete();
}
