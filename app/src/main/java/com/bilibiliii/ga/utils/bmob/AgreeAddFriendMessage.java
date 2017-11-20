package com.bilibiliii.ga.utils.bmob;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONObject;

import cn.bmob.newim.bean.BmobIMExtraMessage;
import cn.bmob.newim.bean.BmobIMMessage;

/**
 * @author No.47 create at 2017/11/20.
 */
public class AgreeAddFriendMessage extends BmobIMExtraMessage {
    public static final String AGREE = "agree";
    private String uid;
    private Long time;
    private String msg;

    public AgreeAddFriendMessage(){

    }

    public AgreeAddFriendMessage(BmobIMMessage msg) {
        super.parse(msg);
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMsgType() {
        return AGREE;
    }

    @Override
    public boolean isTransient() {
        return false;
    }

    public static AgreeAddFriendMessage convert(BmobIMMessage msg) {
        AgreeAddFriendMessage agree = new AgreeAddFriendMessage(msg);
        try {
            String extra = msg.getExtra();
            if (!TextUtils.isEmpty(extra)) {
                JSONObject json = new JSONObject(extra);
                Long time = json.getLong("time");
                String uid = json.getString("uid");
                String m = json.getString("msg");
                agree.setMsg(m);
                agree.setUid(uid);
                agree.setTime(time);
            } else {
                Log.d("add", "AgreeAddFriendMessage的extra为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return agree;
    }
}


