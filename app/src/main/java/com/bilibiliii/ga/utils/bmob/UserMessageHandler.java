package com.bilibiliii.ga.utils.bmob;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.bilibiliii.ga.utils.bmob.I.UpdateCacheListener;

import org.greenrobot.eventbus.EventBus;

import cn.bmob.newim.bean.BmobIMMessage;
import cn.bmob.newim.bean.BmobIMMessageType;
import cn.bmob.newim.event.MessageEvent;
import cn.bmob.newim.event.OfflineMessageEvent;
import cn.bmob.newim.listener.BmobIMMessageHandler;
import cn.bmob.v3.exception.BmobException;

/**
 * @author No.47 create at 2017/11/10.
 */
public class UserMessageHandler extends BmobIMMessageHandler {
    private  final String TAG = getClass().getSimpleName();

    @Override
    public void onMessageReceive(final MessageEvent event) {
        executeMessage(event);
        Log.d(TAG, "onMessageReceive");
    }

    @Override
    public void onOfflineReceive(final OfflineMessageEvent event) {
        Log.d(TAG, "onOfflineReceive");
        Log.d(TAG, event.getTotalNumber()+"");
        Log.d(TAG, event.getEventMap().get(AddFriendMessage.ADD).get(0).getFromUserInfo().getName());
        Log.d(TAG, event.getEventMap().get(AddFriendMessage.ADD).get(0).getMessage().toString());
    }

    public void executeMessage(final MessageEvent event){
        UserProxy.getInstance().updateUserInfo(event, new UpdateCacheListener() {
            @Override
            public void done(BmobException e) {
                BmobIMMessage msg = event.getMessage();
                if (BmobIMMessageType.getMessageTypeValue(msg.getMsgType()) == 0) {
                    //自定义消息类型：0
//                    processCustomMessage(msg, event.getFromUserInfo());
                } else {
                    //SDK内部内部支持的消息类型
                    processSDKMessage(msg, event);
                }
            }
        });
    }

    private void processSDKMessage(BmobIMMessage msg, MessageEvent event) {

        //直接发送消息事件
        EventBus.getDefault().post(event);

    }
}