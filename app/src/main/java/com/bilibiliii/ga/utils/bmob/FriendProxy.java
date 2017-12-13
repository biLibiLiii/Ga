package com.bilibiliii.ga.utils.bmob;

import com.bilibiliii.ga.bean.Friend;
import com.bilibiliii.ga.bean.NewFriend;
import com.bilibiliii.ga.bean.User;
import com.bilibiliii.ga.utils.bmob.I.IFriendProxy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.bean.BmobIMMessage;
import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.newim.core.BmobIMClient;
import cn.bmob.newim.listener.MessageSendListener;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * @author No.47 create at 2017/11/17.
 */
public class FriendProxy implements IFriendProxy {

    public void queryFriends(final CallBack<List<Friend>> callback) {
        BmobQuery<Friend> query = new BmobQuery<>();
        User user = UserProxy.getInstance().getCurrentUser();
        query.addWhereEqualTo("user", user);
        query.include("friendUser");
        query.order("-updatedAt");
        query.findObjects(new FindListener<Friend>() {
            @Override
            public void done(List<Friend> list, BmobException e) {
                if (null == e) {
                    if (list != null && list.size() > 0) {
                        callback.onSuccess(list);
                    } else {
                        callback.onFail("暂无联系人");
                    }
                } else {
                    callback.onFail(ErrorCodeHelper.getErrorInfo(e.getErrorCode()));
                }
            }
        });
    }

    public void deleteFriend(String friendID, final CallBack<String> callBack) {
        Friend friend = new Friend();
        friend.delete(friendID, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (null == e) {
                    callBack.onSuccess("success!");
                } else {
                    callBack.onFail(ErrorCodeHelper.getErrorInfo(e.getErrorCode()));
                }
            }
        });
    }

    public void sendAddFriendMessage(User user, final CallBack<BmobIMMessage> callBack) {
        BmobIMUserInfo userInfo = new BmobIMUserInfo();
        userInfo.setUserId(user.getObjectId());
        userInfo.setName(user.getUsername());
        BmobIMConversation conversationEntrance = BmobIM.getInstance().startPrivateConversation(userInfo, true, null);
        BmobIMConversation messageManager = BmobIMConversation.obtain(BmobIMClient.getInstance(), conversationEntrance);
        AddFriendMessage msg = new AddFriendMessage();
        User currentUser = UserProxy.getInstance().getCurrentUser();
        msg.setContent("很高兴认识你，可以加个好友吗?");
        Map<String, Object> map = new HashMap<>();
        map.put("name", currentUser.getUsername());
        map.put("uid", currentUser.getObjectId());
        msg.setExtraMap(map);
        messageManager.sendMessage(msg, new MessageSendListener() {
            @Override
            public void done(BmobIMMessage msg, BmobException e) {
                if (e == null) {
                    callBack.onSuccess(msg);
                } else {
                    callBack.onFail(ErrorCodeHelper.getErrorInfo(e.getErrorCode()));
                }
            }
        });

    }

    public void sendAgreeAddFriendMessage(NewFriend add, final CallBack<BmobIMMessage> callBack) {
        BmobIMUserInfo info = new BmobIMUserInfo();
        info.setUserId(add.getUid());
        info.setName(add.getName());
        BmobIMConversation conversationEntrance = BmobIM.getInstance().startPrivateConversation(info, true, null);
        BmobIMConversation messageManager = BmobIMConversation.obtain(BmobIMClient.getInstance(), conversationEntrance);
        AgreeAddFriendMessage msg = new AgreeAddFriendMessage();
        final User currentUser = BmobUser.getCurrentUser(User.class);
        msg.setContent("我通过了你的好友验证请求，我们可以开始 聊天了!");
        Map<String, Object> map = new HashMap<>();
        map.put("msg", currentUser.getUsername() + "同意添加你为好友");
        map.put("uid", add.getUid());
        map.put("time", add.getTime());
        msg.setExtraMap(map);
        messageManager.sendMessage(msg, new MessageSendListener() {
            @Override
            public void done(BmobIMMessage msg, BmobException e) {
                if (e == null) {
                    callBack.onSuccess(msg);
                } else {
                    callBack.onFail(ErrorCodeHelper.getErrorInfo(e.getErrorCode()));
                }
            }
        });
    }

    private void processCustomMessage(BmobIMMessage msg, BmobIMUserInfo info) {
        String type = msg.getMsgType();
        if (type.equals(AddFriendMessage.ADD)) {
            NewFriend friend = AddFriendMessage.convert(msg);
        } else if (type.equals(AgreeAddFriendMessage.AGREE)) {
            AgreeAddFriendMessage agree = AgreeAddFriendMessage.convert(msg);
        } else {

        }
    }

    public void agreeAddFriend(User friend, final CallBack<String> callBack) {
        Friend f = new Friend();
        User user = UserProxy.getInstance().getCurrentUser();
        f.setUser(user);
        f.setFriendUser(friend);
        f.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (null == e) {
                    callBack.onSuccess(s);
                } else {
                    callBack.onFail(ErrorCodeHelper.getErrorInfo(e.getErrorCode()));
                }
            }
        });
    }
}
