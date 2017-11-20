package com.bilibiliii.ga.utils.bmob;

import android.util.Log;

import cn.bmob.newim.event.MessageEvent;
import cn.bmob.newim.event.OfflineMessageEvent;
import cn.bmob.newim.listener.BmobIMMessageHandler;

/**
 * @author No.47 create at 2017/11/10.
 */
public class UserMessageHandler extends BmobIMMessageHandler {
    private  final String TAG = getClass().getSimpleName();

    @Override
    public void onMessageReceive(final MessageEvent event) {
        Log.d(TAG, "onMessageReceive");
    }

    @Override
    public void onOfflineReceive(final OfflineMessageEvent event) {
        Log.d(TAG, "onOfflineReceive");
        Log.d(TAG, event.getTotalNumber()+"");
        Log.d(TAG, event.getEventMap().get(AddFriendMessage.ADD).get(0).getFromUserInfo().getName());
        Log.d(TAG, event.getEventMap().get(AddFriendMessage.ADD).get(0).getMessage().toString());
    }
}