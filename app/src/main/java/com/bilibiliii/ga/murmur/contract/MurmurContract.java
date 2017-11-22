package com.bilibiliii.ga.murmur.contract;


import com.bilibiliii.ga.bean.Murmur;

import java.util.List;

/**
 * @author No.47 create at 2017/11/20.
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
