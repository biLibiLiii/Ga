package com.bilibiliii.ga.murmur.contract;

import android.net.Uri;

/**
 * @author No.47 create at 2017/11/20.
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
