package com.bilibiliii.ga.main.presenter;

import android.os.Message;
import android.util.Log;

import com.bilibiliii.ga.base.BasePresenter;
import com.bilibiliii.ga.bean.User;
import com.bilibiliii.ga.utils.bmob.CallBack;
import com.bilibiliii.ga.utils.bmob.I.IUserProxy;
import com.bilibiliii.ga.utils.bmob.MessageProxy;
import com.bilibiliii.ga.utils.bmob.UserProxy;

import java.util.List;

import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMMessage;
import cn.bmob.newim.bean.BmobIMUserInfo;

/**
 * @author No.47 create at 2017/11/10.
 */
public class MainPresenter extends BasePresenter {
    private UserProxy userProxy;

    public MainPresenter() {
        this.userProxy = UserProxy.getInstance(this);
        ;
    }

    public void login() {
        userProxy.login("AJCARE", "111111", new CallBack<User>() {
            @Override
            public void onSuccess(User result) {
                Log.d(TAG, "success");
                Log.d(TAG, "userId:" + userProxy.getCurrentUser().getObjectId());
                Log.d(TAG, "imUserId:" + BmobIM.getInstance().getCurrentUid());
            }

            @Override
            public void onFail(String errorInfo) {
                Log.d(TAG, "fail");
            }
        });

    }

    // xWLqKKKP AJCARE
    // 928444c27b 123
    private void test() {
        MessageProxy messageProxy = new MessageProxy();
        BmobIMUserInfo userInfo = new BmobIMUserInfo();
        userInfo.setUserId("928444c27b");
        userInfo.setName("123");
        messageProxy.createNewConversation(userInfo);
        messageProxy.queryMessages(new CallBack<List<BmobIMMessage>>() {
            @Override
            public void onSuccess(List<BmobIMMessage> result) {
                Log.d(TAG, "onSuccess query message");
                Log.d(TAG, result.get(0).getContent());
            }

            @Override
            public void onFail(String errorInfo) {
                Log.d(TAG, "onFail query message");
            }
        });
//        messageProxy.sendMessage("hello, aj!", new CallBack<BmobIMMessage>() {
//            @Override
//            public void onSuccess(BmobIMMessage result) {
//                Log.d(TAG, "onSuccess send message");
//            }
//
//            @Override
//            public void onFail(String errorInfo) {
//                Log.d(TAG, "onFail send message");
//            }
//        });
    }

    @Override
    public boolean handleMessage(Message message) {
        switch (message.what) {
            case IUserProxy.STATE_CONNECTED:
                Log.d(TAG, "STATE_CONNECTED");
                test();
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
