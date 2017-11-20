package com.bilibiliii.ga.utils.bmob.I;

import com.bilibiliii.ga.utils.bmob.CallBack;

import java.util.List;

import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.bean.BmobIMMessage;
import cn.bmob.newim.bean.BmobIMUserInfo;

/**
 * @author No.47 create at 2017/11/10.
 */
public interface IMessageProxy {
    void createNewConversation(BmobIMUserInfo userInfo);

    void createTmpConversation(BmobIMUserInfo userInfo);

    List<BmobIMConversation> queryAllConversation();

    long getUnReadCount(String conversationId);

    long getAllUnReadCount();

    void deleteConversation(BmobIMConversation c);

    void deleteConversation(String conversationID);

    void clearAllConversation();

    void updateConversation(BmobIMConversation c);

    void queryMessages(CallBack<List<BmobIMMessage>> callback);

    void sendMessage(String msg, CallBack<BmobIMMessage> callback);
}
