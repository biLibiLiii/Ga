package com.bilibiliii.ga.murmur.model;

import com.bilibiliii.ga.bean.Murmur;
import com.bilibiliii.ga.utils.bmob.UserProxy;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * @author No.47 create at 2017/11/20.
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
        murmur.setCreater(UserProxy.getInstance().getCurrentUser());
        murmur.setImageUri(imageUri);
        murmur.setCreaterName(UserProxy.getInstance().getCurrentUser().getUsername());
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
        murmur.setCreater(UserProxy.getInstance().getCurrentUser());
        murmur.setImageUri("");
        murmur.setCreaterName(UserProxy.getInstance().getCurrentUser().getUsername());
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
