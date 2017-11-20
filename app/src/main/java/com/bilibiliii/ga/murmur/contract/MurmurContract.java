package com.bilibiliii.ga.murmur.contract;


import com.bilibiliii.ga.bean.Murmur;

import java.util.List;

/**
 * Created by Cherie_No.47 on 2016/7/11 14:10.
 * Email jascal@163.com
 */
public class MurmurContract {
    public interface View   {
        void onGetMurmurSuccess(List<Murmur> list);
        void onGetMurmurFail();
        void setPresenter(MurmurContract.Presenter presenter);
    }

    public interface Presenter  {
        void getMurmurData();
    }
}
