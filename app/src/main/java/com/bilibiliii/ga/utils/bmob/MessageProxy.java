package com.bilibiliii.ga.utils.bmob;

import android.text.TextUtils;

import com.bilibiliii.ga.bean.User;
import com.bilibiliii.ga.utils.bmob.I.IMessageProxy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.bean.BmobIMMessage;
import cn.bmob.newim.bean.BmobIMTextMessage;
import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.newim.core.BmobIMClient;
import cn.bmob.newim.event.MessageEvent;
import cn.bmob.newim.listener.MessageSendListener;
import cn.bmob.newim.listener.MessagesQueryListener;
import cn.bmob.v3.exception.BmobException;
import retrofit2.Call;

/**
 * @author No.47 create at 2017/11/10.
 */
public class MessageProxy implements IMessageProxy {
    private static IMessageProxy instance;
    private BmobIMConversation messageManager;

    public static IMessageProxy getInstance() {
        if (null == instance) {
            instance = new MessageProxy();
        }
        return instance;
    }

    public void setMessageManager(BmobIMConversation messageManager) {
        this.messageManager = messageManager;
    }

    @Override
    public void createNewConversation(BmobIMUserInfo userInfo) {
        messageManager = BmobIMConversation.obtain(BmobIMClient.getInstance(),
                BmobIM.getInstance().startPrivateConversation(userInfo, null));
    }

    @Override
    public void createTmpConversation(BmobIMUserInfo userInfo) {
        messageManager = BmobIMConversation.obtain(BmobIMClient.getInstance(),
                BmobIM.getInstance().startPrivateConversation(userInfo, true, null));
    }

    @Override
    public List<BmobIMConversation> queryAllConversation() {
        return BmobIM.getInstance().loadAllConversation();
    }

    @Override
    public long getUnReadCount(String conversationId) {
        return BmobIM.getInstance().getUnReadCount(conversationId);
    }

    @Override
    public long getAllUnReadCount() {
        return BmobIM.getInstance().getAllUnReadCount();
    }

    @Override
    public void deleteConversation(BmobIMConversation c) {
        BmobIM.getInstance().deleteConversation(c);
    }

    @Override
    public void deleteConversation(String conversationId) {
        BmobIM.getInstance().deleteConversation(conversationId);
    }

    @Override
    public void clearAllConversation() {
        BmobIM.getInstance().clearAllConversation();
    }

    @Override
    public void updateConversation(BmobIMConversation c) {
        BmobIM.getInstance().updateConversation(c);
    }

    @Override
    public void queryMessages(final CallBack<List<BmobIMMessage>> callback) {
        messageManager.queryMessages(null, 20, new MessagesQueryListener() {
            @Override
            public void done(List<BmobIMMessage> list, BmobException e) {
                if (null == e) {
                    callback.onSuccess(list);
                } else {
                    callback.onFail(ErrorCodeHelper.getErrorInfo(e.getErrorCode()));
                }
            }
        });
    }

    @Override
    public void sendMessage(String msg, final CallBack<BmobIMMessage> callback) {
        if (TextUtils.isEmpty(msg.trim())) {
            return;
        }
        BmobIMTextMessage textMessage = new BmobIMTextMessage();
        textMessage.setContent(msg);
        Map<String, Object> map = new HashMap<>();
        map.put("level", "1");
        textMessage.setExtraMap(map);
        messageManager.sendMessage(textMessage, new MessageSendListener() {
            @Override
            public void done(BmobIMMessage bmobIMMessage, BmobException e) {
                if (null == e) {
                    callback.onSuccess(bmobIMMessage);
                } else {
                    callback.onFail(ErrorCodeHelper.getErrorInfo(e.getErrorCode()));
                }
            }
        });
    }

    public void updateUserInfo(MessageEvent event) {
        final BmobIMConversation conversation = event.getConversation();
        final BmobIMUserInfo info = event.getFromUserInfo();
        final BmobIMMessage msg = event.getMessage();
        String username = info.getName();
        String title = conversation.getConversationTitle();
        if (!username.equals(title)) {
            UserProxy.getInstance().queryUserById(info.getUserId(), new CallBack<User>() {
                @Override
                public void onSuccess(User result) {
                    String name = result.getUsername();
                    conversation.setConversationTitle(name);
                    info.setName(name);
                    BmobIM.getInstance().updateUserInfo(info);
                    if (!msg.isTransient()) {
                        BmobIM.getInstance().updateConversation(conversation);
                    }
                }

                @Override
                public void onFail(String errorInfo) {

                }
            });
        }
    }


}
