package com.bilibiliii.ga.utils;

/**
 * @author No.47 create at 2017/11/2.
 */
public class Common {
    public static final String BMOB_APPLICATION_KEY = "0a58691792addebbeefae9f474e3f0d7";

    public static final String RETROFIT_CALLBACK_EXCEPTION = "retrofit callback is null. Set callback before using get";

    /**
     * history event api
     */
    public static final String HISTORY_EVENT_BASE = "http://api.juheapi.com/japi/";
    public static final String HISTORY_EVENT_KEY = "key";
    public static final String HISTORY_EVENT_KEY_VALUE = "eff36bdaeeb868a6b8057a34f32d1326";
    public static final String HISTORY_EVENT_VERSION = "v";
    public static final String HISTORY_EVENT_VERSION_VALUE = "1.0";
    public static final String HISTORY_EVENT_MONTH = "month";
    public static final String HISTORY_EVENT_DAY = "day";

    public static final int STATUS_VERIFY_NONE = 0;
    //好友请求：已读-未添加->点击查看了新朋友，则都变成已读状态
    public static final int STATUS_VERIFY_READED = 2;
    //好友请求：已添加
    public static final int STATUS_VERIFIED = 1;
    //好友请求：拒绝
    public static final int STATUS_VERIFY_REFUSE = 3;
    //好友请求：我发出的好友请求-暂未存储到本地数据库中
    public static final int STATUS_VERIFY_ME_SEND = 4;

    public static int RESULT_ALBUM = 1000;
    public static String RESULT_ALBUM_INTENT_TYPE = "image/*";
    public static String RESULT_ALBUM_INTENT_PARM_0 = "crop";
    public static String RESULT_ALBUM_INTENT_PARM_1 = "return-data";

    public static int IMAGE_SIZE_ON_EDIT_WIDTH = 100;
    public static int IMAGE_SIZE_ON_EDIT_HEIGHT = 100;

}
