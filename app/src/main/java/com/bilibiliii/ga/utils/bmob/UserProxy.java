package com.bilibiliii.ga.utils.bmob;

import com.bilibiliii.ga.bean.User;
import com.bilibiliii.ga.utils.bmob.I.IUserProxy;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * @author No.47 create at 2017/11/8.
 */
public class UserProxy implements IUserProxy {
    private String TAG = getClass().getSimpleName();
    private static UserProxy instance;

    public static UserProxy getInstance() {
        if (instance == null) {
            instance = new UserProxy();
        }
        return instance;
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
                } else {
                    callBack.onFail(getErrorInfo(e.getErrorCode()));
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
                if (e == null) {
                    callBack.onSuccess(s);
                } else {
                    callBack.onFail(getErrorInfo(e.getErrorCode()));
                }
            }
        });
    }

    @Override
    public void register(String username, String password, String email, final CallBack<User> callBack) {
        if (null == callBack) {
            throw new NullPointerException("User register Callback can not be Null.");
        }

        User user = new User.UserBuilder()
                .setUsername(username)
                .setPassword(password)
                .setEmail(email)
                .build();
        register(user, callBack);
    }

    @Override
    public void updateUser(String email, final CallBack<User> callBack) {
        if (null == callBack) {
            throw new NullPointerException("User updateUser Callback can not be Null.");
        }

        User user = new User.UserBuilder()
                .setEmail(email)
                .build();
        BmobUser currentUser = getCurrentUser();
        user.update(currentUser.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    callBack.onSuccess(null);
                } else {
                    callBack.onFail(getErrorInfo(e.getErrorCode()));
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
                    callBack.onFail(getErrorInfo(e.getErrorCode()));
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
                    callBack.onFail(getErrorInfo(e.getErrorCode()));
                }
            }
        });
    }

    @Override
    public void logOut() {
        User.logOut();
    }

    @Override
    public BmobUser getCurrentUser() {
        return User.getCurrentUser();
    }

    private String getErrorInfo(int errorCode) {
        String msg = "";
        switch (errorCode) {
            case ERROR_CODE_APPKEY_NULL:
                msg = "Application Id为空，请初始化.";
                break;
            case ERROR_CODE_PARSE_DATA:
                msg = "解析返回数据出错";
                break;
            case ERROR_CODE_UPLOAD_FILE_ERROR:
                msg = "上传文件出错";
                break;
            case ERROR_CODE_UPLOAD_FILE_FAIL:
                msg = "文件上传失败";
                break;
            case ERROR_CODE_BATCH_OPERATION:
                msg = "批量操作只支持最多50条";
                break;
            case ERROR_CODE_OBJECTID_NULL:
                msg = "objectId为空";
                break;
            case ERROR_CODE_TOO_LARGE_FILE_SIZE:
                msg = "文件大小超过10M";
                break;
            case ERROR_CODE_BMOBFILE_NOT_EXIST:
                msg = "上传文件不存在";
                break;
            case ERROR_CODE_NO_CACHE_DATA:
                msg = "没有缓存数据";
                break;
            case ERROR_CODE_TIME_OUT:
                msg = "网络超时";
                break;
            case ERROR_CODE_NO_BATCH_SUPPORT:
                msg = "BmobUser类不支持批量操作";
                break;
            case ERROR_CODE_CONTEXT_NULL:
                msg = "上下文为空";
                break;
            case ERROR_CODE_DATABASE_NAME_WRONG:
                msg = "BmobObject（数据表名称）格式不正确";
                break;
            case ERROR_CODE_THIRD_PART_TAKEN_ERROR:
                msg = "第三方账号授权失败";
                break;
            case ERROR_CODE_OTHERS:
                msg = "其他错误";
                break;
            case ERROR_CODE_NO_NETWORK:
                msg = "无网络连接，请检查您的手机网络";
                break;
            case ERROR_CODE_ERROR_ABOUT_THIRD_PART:
                msg = "与第三方登录有关的错误，具体请看对应的错误描述";
                break;
            case ERROR_CODE_PARAMS_NULL:
                msg = "参数不能为空";
                break;
            case ERROR_CODE_FORMAT_ERROR:
                msg = "格式不正确：手机号码、邮箱地址、验证码";
                break;
            default:
                msg = "未知错误";
                break;
        }
        return msg;
    }

}
