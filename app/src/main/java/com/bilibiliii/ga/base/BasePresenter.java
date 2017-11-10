package com.bilibiliii.ga.base;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * @author No.47 create at 2017/11/10.
 */
public class BasePresenter implements Handler.Callback{
    protected String TAG = getClass().getSimpleName();

    @Override
    public boolean handleMessage(Message message) {
        return false;
    }
}
