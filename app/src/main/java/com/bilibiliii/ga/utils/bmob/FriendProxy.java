package com.bilibiliii.ga.utils.bmob;

import android.content.Context;

import com.bilibiliii.ga.bean.Friend;
import com.bilibiliii.ga.bean.User;
import com.bilibiliii.ga.utils.bmob.I.IFriendProxy;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * @author No.47 create at 2017/11/17.
 */
public class FriendProxy implements IFriendProxy {
    private Context context;

    public FriendProxy(Context context) {
        this.context = context;
    }

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
}
