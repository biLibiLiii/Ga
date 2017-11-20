package com.bilibiliii.ga.murmur.contract;

import android.net.Uri;

/**
 * Created by Cherie_No.47 on 2016/7/10 08:07.
 * Email jascal@163.com
 */
public class EditMurmurContract {
    public interface View {
        void onUpLoadFail();
        void onUpLoadSuccess();
        void setPresenter(EditMurmurContract.Presenter presenter);
    }

    public interface Presenter  {
        void upLoad(Uri imageFile, String content);
    }
}
