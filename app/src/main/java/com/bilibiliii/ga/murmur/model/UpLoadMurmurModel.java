package com.bilibiliii.ga.murmur.model;

import com.bilibiliii.ga.bean.Murmur;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Cherie_No.47 on 2016/7/10 13:41.
 * Email jascal@163.com
 */
public class UpLoadMurmurModel {
    private OnUpLoadMurResult onUpLoadMurResult;

    public static UpLoadMurmurModel initUpLoadMurmurModel( OnUpLoadMurResult onUpLoadMurResult) {
        UpLoadMurmurModel upLoadMurmurModel = new UpLoadMurmurModel();
        upLoadMurmurModel.onUpLoadMurResult = onUpLoadMurResult;
        return upLoadMurmurModel;
    }

    public void upLoad(String content, String imageUri) {
        Murmur murmur = new Murmur();
        murmur.setContent(content);
        murmur.setFavor(0);
        murmur.setCreater(BmobUser.getCurrentUser().getObjectId());
        murmur.setImageUri(imageUri);
        murmur.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    onUpLoadMurResult.onSuccessForMur();
                } else {
                    onUpLoadMurResult.onFailForMur(e.toString());
                }
            }
        });
    }

    public void upLoad(String content) {
        Murmur murmur = new Murmur();
        murmur.setContent(content);
        murmur.setFavor(0);
        murmur.setCreater(BmobUser.getCurrentUser().getObjectId());
        murmur.setImageUri("");
        murmur.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    onUpLoadMurResult.onSuccessForMur();
                } else {
                    onUpLoadMurResult.onFailForMur(e.toString());
                }
            }
        });
    }

    public interface OnUpLoadMurResult {
        void onSuccessForMur();
        void onFailForMur(String errorCode);
    }
}
