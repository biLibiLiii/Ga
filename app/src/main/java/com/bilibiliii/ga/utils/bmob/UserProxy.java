package com.bilibiliii.ga.utils.bmob;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.bilibiliii.ga.bean.User;
import com.bilibiliii.ga.utils.bmob.I.IUserProxy;
import com.bilibiliii.ga.utils.bmob.I.QueryUserListener;
import com.bilibiliii.ga.utils.bmob.I.UpdateCacheListener;

import java.util.List;

import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.bean.BmobIMMessage;
import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.newim.core.ConnectionStatus;
import cn.bmob.newim.event.MessageEvent;
import cn.bmob.newim.listener.ConnectListener;
import cn.bmob.newim.listener.ConnectStatusChangeListener;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * @author No.47 create at 2017/11/8.
 */
public class UserProxy implements IUserProxy {
    private String TAG = getClass().getSimpleName();
    private Handler handler;

    public static UserProxy getInstance() {
        return new UserProxy();
    }

    public void setHandlerCallback(Handler.Callback callback) {
        this.handler = new Handler(Looper.getMainLooper(), callback);
    }

    @Override
    public void login(String username, String password, final CallBack<User> callBack) {
        if (null == callBack) {
            throw new NullPointerException("User login Callback can not be Null.");
        }

        User user = new User.UserBuilder()
                .setUsername(username)
                .setPassword(password)
                .build();
        user.login(new SaveListener<User>() {
            @Override
            public void done(User s, BmobException e) {
                if (null == e) {
                    callBack.onSuccess(s);
                    initIMProfile();
                } else {
                    callBack.onFail(ErrorCodeHelper.getErrorInfo(e.getErrorCode()));
                }
            }
        });
    }

    @Override
    public void register(User user, final CallBack<User> callBack) {
        if (null == callBack) {
            throw new NullPointerException("User register Callback can not be Null.");
        }

        user.signUp(new SaveListener<User>() {
            @Override
            public void done(User s, BmobException e) {
                if (null == e) {
                    callBack.onSuccess(s);
                    initIMProfile();
                } else {
                    callBack.onFail(ErrorCodeHelper.getErrorInfo(e.getErrorCode()));
                }
            }
        });
    }

    @Override
    public void register(String username, String password, final CallBack<User> callBack) {
        if (null == callBack) {
            throw new NullPointerException("User register Callback can not be Null.");
        }

        User user = new User.UserBuilder()
                .setUsername(username)
                .setPassword(password)
                .build();
        register(user, callBack);
    }

    private void initIMProfile() {
        if (getCurrentUser().getObjectId().equals(BmobIM.getInstance().getCurrentUid())) {
            if (BmobIM.getInstance().getCurrentStatus().getCode() == ConnectionStatus.CONNECTED.getCode()) {
                Log.d(TAG, "getCurrentStatus CONNECTED");
                handler.sendEmptyMessage(STATE_CONNECTED);
                return;
            } else if (BmobIM.getInstance().getCurrentStatus().getCode() == ConnectionStatus.CONNECTING.getCode()) {
                Log.d(TAG, "getCurrentStatus CONNECTING");
                setIMStateCallback();
                return;
            }
        }
        BmobIM.connect(getCurrentUser().getObjectId(), new ConnectListener() {
            @Override
            public void done(String uid, BmobException e) {
                if (e == null) {
                    setIMStateCallback();
                    /**
                     * 此处有空指针异常
                     */
                    handler.sendEmptyMessage(STATE_CONNECTED);
                } else {
                    handler.sendEmptyMessage(STATE_DISCONNECT);
                }
            }
        });
    }

    private ConnectStatusChangeListener connectStatusChangeListener;

    private void setIMStateCallback() {
        if (null == connectStatusChangeListener) {
            connectStatusChangeListener = new ConnectStatusChangeListener() {
                @Override
                public void onChange(ConnectionStatus status) {
                    handler.sendEmptyMessage(status.getCode());
                }
            };
        }
        BmobIM.getInstance().setOnConnectStatusChangeListener(connectStatusChangeListener);
    }

    @Override
    public void updateUser(String email, final CallBack<User> callBack) {
        if (null == callBack) {
            throw new NullPointerException("User updateUser Callback can not be Null.");
        }

        User user = new User.UserBuilder()
                .setEmail(email)
                .build();
        User currentUser = getCurrentUser();
        user.update(currentUser.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    callBack.onSuccess(null);
                } else {
                    callBack.onFail(ErrorCodeHelper.getErrorInfo(e.getErrorCode()));
                }
            }
        });
    }

    @Override
    public void changePSW(String oldPsw, String newPsw, final CallBack<User> callBack) {
        if (null == callBack) {
            throw new NullPointerException("User changePSW Callback can not be Null.");
        }

        User.updateCurrentUserPassword(oldPsw, newPsw, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    callBack.onSuccess(null);
                } else {
                    callBack.onFail(ErrorCodeHelper.getErrorInfo(e.getErrorCode()));
                }
            }
        });
    }

    @Override
    public void queryUser(String username, final CallBack<List<User>> callBack) {
        if (null == callBack) {
            throw new NullPointerException("User queryUser Callback can not be Null.");
        }

        BmobQuery<User> query = new BmobQuery();
        query.addWhereEqualTo("username", username);
        query.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> list, BmobException e) {
                if (e == null) {
                    callBack.onSuccess(list);
                } else {
                    callBack.onFail(ErrorCodeHelper.getErrorInfo(e.getErrorCode()));
                }
            }
        });
    }

    @Override
    public void queryUserById(String userID, final CallBack<User> callBack) {
        if (null == callBack) {
            throw new NullPointerException("User queryUserById Callback can not be Null.");
        }

        BmobQuery<User> bmobQuery = new BmobQuery<User>();
        bmobQuery.getObject(userID, new QueryListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e == null) {
                    callBack.onSuccess(user);
                } else {
                    callBack.onFail(ErrorCodeHelper.getErrorInfo(e.getErrorCode()));
                }
            }
        });
    }

    @Override
    public void reLoginIM() {
        initIMProfile();
    }

    @Override
    public void logOutIM() {
        BmobIM.getInstance().disConnect();
    }

    @Override
    public void logOut() {
        User.logOut();
        BmobIM.getInstance().disConnect();
    }

    @Override
    public User getCurrentUser() {
        return User.getCurrentUser(User.class);
    }

    public void updateUserInfo(MessageEvent event, final UpdateCacheListener listener) {
        final BmobIMConversation conversation = event.getConversation();
        final BmobIMUserInfo info = event.getFromUserInfo();
        final BmobIMMessage msg = event.getMessage();
        String username = info.getName();
//        String avatar = info.getAvatar();
        String title = conversation.getConversationTitle();
//        String icon = conversation.getConversationIcon();
        //SDK内部将新会话的会话标题用objectId表示，因此需要比对用户名和私聊会话标题，后续会根据会话类型进行判断
        if (!username.equals(title) ) {
            UserProxy.getInstance().queryUserById(info.getUserId(), new CallBack<User>() {
                @Override
                public void onSuccess(User result) {
                    String name = result.getUsername();
//                        String avatar = s.getAvatar();
//                        conversation.setConversationIcon(avatar);
                    conversation.setConversationTitle(name);
                    info.setName(name);
//                        info.setAvatar(avatar);
                    //TODO 用户管理：2.7、更新用户资料，用于在会话页面、聊天页面以及个人信息页面显示
                    BmobIM.getInstance().updateUserInfo(info);
                    //TODO 会话：4.7、更新会话资料-如果消息是暂态消息，则不更新会话资料
                    if (!msg.isTransient()) {
                        BmobIM.getInstance().updateConversation(conversation);
                    }
                    listener.done(null);
                }
                @Override
                public void onFail(String errorInfo) {
                    listener.done(null);
                }
            });
        } else {
            listener.done(null);
        }
    }
}
