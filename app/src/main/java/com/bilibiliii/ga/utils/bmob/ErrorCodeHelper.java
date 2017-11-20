package com.bilibiliii.ga.utils.bmob;

/**
 * @author No.47 create at 2017/11/17.
 */
public class ErrorCodeHelper {
    private static final int ERROR_CODE_APPKEY_NULL = 9001;
    private static final int ERROR_CODE_PARSE_DATA = 9002;
    private static final int ERROR_CODE_UPLOAD_FILE_ERROR = 9003;
    private static final int ERROR_CODE_UPLOAD_FILE_FAIL = 9004;
    private static final int ERROR_CODE_BATCH_OPERATION = 9005;
    private static final int ERROR_CODE_OBJECTID_NULL = 9006;
    private static final int ERROR_CODE_TOO_LARGE_FILE_SIZE = 9007;
    private static final int ERROR_CODE_BMOBFILE_NOT_EXIST = 9008;
    private static final int ERROR_CODE_NO_CACHE_DATA = 9009;
    private static final int ERROR_CODE_TIME_OUT = 9010;
    private static final int ERROR_CODE_NO_BATCH_SUPPORT = 9011;
    private static final int ERROR_CODE_CONTEXT_NULL = 9012;
    private static final int ERROR_CODE_DATABASE_NAME_WRONG = 9013;
    private static final int ERROR_CODE_THIRD_PART_TAKEN_ERROR = 9014;
    private static final int ERROR_CODE_OTHERS = 9015;
    private static final int ERROR_CODE_NO_NETWORK = 9016;
    private static final int ERROR_CODE_ERROR_ABOUT_THIRD_PART = 9017;
    private static final int ERROR_CODE_PARAMS_NULL = 9018;
    private static final int ERROR_CODE_FORMAT_ERROR = 9019;

    public static String getErrorInfo(int errorCode) {
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
