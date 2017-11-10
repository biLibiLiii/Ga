package com.bilibiliii.ga.utils.bmob.I;

import com.bilibiliii.ga.bean.User;
import com.bilibiliii.ga.utils.bmob.CallBack;

import java.util.List;

import cn.bmob.v3.BmobUser;

/**
 * @author No.47 create at 2017/11/8.
 */
public interface IUserProxy {
    int ERROR_CODE_APPKEY_NULL = 9001;
    int ERROR_CODE_PARSE_DATA = 9002;
    int ERROR_CODE_UPLOAD_FILE_ERROR = 9003;
    int ERROR_CODE_UPLOAD_FILE_FAIL = 9004;
    int ERROR_CODE_BATCH_OPERATION = 9005;
    int ERROR_CODE_OBJECTID_NULL = 9006;
    int ERROR_CODE_TOO_LARGE_FILE_SIZE = 9007;
    int ERROR_CODE_BMOBFILE_NOT_EXIST = 9008;
    int ERROR_CODE_NO_CACHE_DATA = 9009;
    int ERROR_CODE_TIME_OUT = 9010;
    int ERROR_CODE_NO_BATCH_SUPPORT = 9011;
    int ERROR_CODE_CONTEXT_NULL = 9012;
    int ERROR_CODE_DATABASE_NAME_WRONG = 9013;
    int ERROR_CODE_THIRD_PART_TAKEN_ERROR = 9014;
    int ERROR_CODE_OTHERS = 9015;
    int ERROR_CODE_NO_NETWORK = 9016;
    int ERROR_CODE_ERROR_ABOUT_THIRD_PART = 9017;
    int ERROR_CODE_PARAMS_NULL = 9018;
    int ERROR_CODE_FORMAT_ERROR = 9019;

    void login(String username, String password, CallBack<User> callBack);

    void register(User user, CallBack<User> callBack);

    void register(String username, String password, CallBack<User> callBack);

    void updateUser(String email, final CallBack<User> callBack);

    void changePSW(String oldPsw, String newPsw, CallBack<User> callBack);

    void queryUser(String username, CallBack<List<User>> callBack);

    void logOut();

    BmobUser getCurrentUser();
}
