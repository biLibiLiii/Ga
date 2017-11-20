package com.bilibiliii.ga.murmur.model;

import android.content.Context;
import android.net.Uri;


import com.bilibiliii.ga.utils.file.FileUtils;

import java.io.File;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * Created by Cherie_No.47 on 2016/7/10 13:30.
 * Email jascal@163.com
 */
public class UploadPicModel {
    private OnUpLoadPicResult onUpLoadPicResult;
    private Context context;

    public static UploadPicModel initUploadPicModel(Context context, OnUpLoadPicResult onUpLoadPicResult) {
        UploadPicModel uploadPicModel = new UploadPicModel();
        uploadPicModel.onUpLoadPicResult = onUpLoadPicResult;
        uploadPicModel.context = context;
        return uploadPicModel;
    }

    public void upLoad(Uri imageFile){
        String picPath = FileUtils.getFilePathFromUri(context, imageFile);
        final BmobFile bmobFile = new BmobFile(new File(picPath));
        bmobFile.uploadblock(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    onUpLoadPicResult.onSuccessForPic(bmobFile.getFileUrl());
                }else {
                    onUpLoadPicResult.onFailForPic(e.toString());
                }
            }

            @Override
            public void onProgress(Integer value) {
                // 返回的上传进度（百分比）
            }
        });
    }

    public interface OnUpLoadPicResult {
        void onSuccessForPic(String fileUrl);
        void onFailForPic(String errorCode);
    }

}
