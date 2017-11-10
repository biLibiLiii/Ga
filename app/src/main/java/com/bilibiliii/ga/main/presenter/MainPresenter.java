package com.bilibiliii.ga.main.presenter;

import android.os.Message;
import android.util.Log;
import com.bilibiliii.ga.base.BasePresenter;
import com.bilibiliii.ga.bean.User;
import com.bilibiliii.ga.utils.bmob.CallBack;
import com.bilibiliii.ga.utils.bmob.I.IUserProxy;
import com.bilibiliii.ga.utils.bmob.UserProxy;

/**
 * @author No.47 create at 2017/11/10.
 */
public class MainPresenter extends BasePresenter {

    public void login(){
        UserProxy.getInstance(this).login("gan", "111111", new CallBack<User>() {
            @Override
            public void onSuccess(User result) {
                Log.d(TAG, "onSuccess");
            }

            @Override
            public void onFail(String errorInfo) {
                Log.d(TAG, "onFail");
            }
        });
    }

    @Override
    public boolean handleMessage(Message message) {
        switch (message.what) {
            case IUserProxy.STATE_CONNECTED:
                Log.d(TAG, "STATE_CONNECTED");
                break;
            case IUserProxy.STATE_CONNECTING:
                Log.d(TAG, "STATE_CONNECTED");
                break;
            case IUserProxy.STATE_DISCONNECT:
                Log.d(TAG, "STATE_CONNECTED");
                break;
            case IUserProxy.STATE_NETWORK_ERROR:
                Log.d(TAG, "STATE_CONNECTED");
                break;
            case IUserProxy.STATE_KICK_OFF_BY_OTHERS:
                Log.d(TAG, "STATE_CONNECTED");
                break;
            default:
                break;
        }
        return false;
    }
}
