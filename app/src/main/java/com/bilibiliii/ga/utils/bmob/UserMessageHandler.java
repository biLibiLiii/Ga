package com.bilibiliii.ga.utils.bmob;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.bilibiliii.ga.bean.NewFriend;
import com.bilibiliii.ga.utils.bmob.I.UpdateCacheListener;
import com.bilibiliii.ga.utils.db.NewFriendManager;

import org.greenrobot.eventbus.EventBus;

import cn.bmob.newim.bean.BmobIMMessage;
import cn.bmob.newim.bean.BmobIMMessageType;
import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.newim.event.MessageEvent;
import cn.bmob.newim.event.OfflineMessageEvent;
import cn.bmob.newim.listener.BmobIMMessageHandler;
import cn.bmob.v3.exception.BmobException;

/**
 * @author No.47 create at 2017/11/10.
 */
public class UserMessageHandler extends BmobIMMessageHandler {
    Context mContext;
    private  final String TAG = getClass().getSimpleName();

    public UserMessageHandler(Context context) {
        mContext = context;
    }

    @Override
    public void onMessageReceive(final MessageEvent event) {
        executeMessage(event);
        Log.d("licl", "onMessageReceive");
    }

    @Override
    public void onOfflineReceive(final OfflineMessageEvent event) {
        Log.d(TAG, "onOfflineReceive");
//        Log.d(TAG, event.getTotalNumber()+"");
//        Log.d(TAG, event.getEventMap().get(AddFriendMessage.ADD).get(0).getFromUserInfo().getName());
//        Log.d(TAG, event.getEventMap().get(AddFriendMessage.ADD).get(0).getMessage().toString());
    }

    public void executeMessage(final MessageEvent event){
        BmobIMMessage msg = event.getMessage();
        Log.d("licl",msg.getMsgType()+BmobIMMessageType.getMessageTypeValue(msg.getMsgType())+1);
        UserProxy.getInstance().updateUserInfo(event, new UpdateCacheListener() {
            @Override
            public void done(BmobException e) {
                BmobIMMessage msg = event.getMessage();
                Log.d("licl",msg.getMsgType()+BmobIMMessageType.getMessageTypeValue(msg.getMsgType())+2);
                if (BmobIMMessageType.getMessageTypeValue(msg.getMsgType()) == 0) {
                    //自定义消息类型：0
                    EventBus.getDefault().post(event);//处理临时消息
                    processCustomMessage(msg, event.getFromUserInfo());
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
    private void processCustomMessage(BmobIMMessage msg, BmobIMUserInfo info) {

      Log.d("licl","收到自定义消息");
        //消息类型
        String type = msg.getMsgType();
        //发送页面刷新的广播
//        EventBus.getDefault().post(new RefreshEvent());
        //处理消息
        if (type.equals(AddFriendMessage.ADD)) {//接收到的添加好友的请求
            NewFriend friend = AddFriendMessage.convert(msg);
            long id = NewFriendManager.getInstance(mContext).insertOrUpdateNewFriend(friend);
            if (id > 0) {
                Toast.makeText(mContext,"收到好友请求",Toast.LENGTH_SHORT).show();
            }
            EventBus.getDefault().post(friend);
        } else if (type.equals(AgreeAddFriendMessage.AGREE)) {//接收到的对方同意添加自己为好友,此时需要做的事情：1、添加对方为好友，2、显示通知

        } else {

        }
    }
}