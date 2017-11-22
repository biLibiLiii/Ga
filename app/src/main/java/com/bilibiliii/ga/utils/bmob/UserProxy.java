package com.bilibiliii.ga.utils.bmob;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.bilibiliii.ga.bean.User;
import com.bilibiliii.ga.utils.bmob.I.IUserProxy;

import java.util.List;

import cn.bmob.newim.BmobIM;
import cn.bmob.newim.core.ConnectionStatus;
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
}
