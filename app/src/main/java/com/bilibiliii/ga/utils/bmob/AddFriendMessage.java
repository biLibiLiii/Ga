package com.bilibiliii.ga.utils.bmob;

import android.text.TextUtils;
import android.util.Log;

import com.bilibiliii.ga.bean.NewFriendRe;
import com.bilibiliii.ga.utils.Common;

import org.json.JSONObject;

import cn.bmob.newim.bean.BmobIMExtraMessage;
import cn.bmob.newim.bean.BmobIMMessage;

/**
 * @author No.47 create at 2017/11/20.
 */
public class AddFriendMessage extends BmobIMExtraMessage {
    public static final String ADD = "add";

    public AddFriendMessage() {
    }

    @Override
    public String getMsgType() {
        return ADD;
    }

    @Override
    public boolean isTransient() {
        return true;
    }

    public static NewFriendRe convert(BmobIMMessage msg) {
        NewFriendRe add = new NewFriendRe();
        String content = msg.getContent();
        add.setMsg(content);
        add.setTime(msg.getCreateTime());
        add.setStatus(Common.STATUS_VERIFY_NONE);
        try {
            String extra = msg.getExtra();
            if (!TextUtils.isEmpty(extra)) {
                JSONObject json = new JSONObject(extra);
                String name = json.getString("name");
                add.setName(name);
                add.setUid(json.getString("uid"));
            } else {
                Log.d("Add", "AddFriendMessage的extra为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return add;
    }
}
