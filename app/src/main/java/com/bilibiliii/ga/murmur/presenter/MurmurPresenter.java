package com.bilibiliii.ga.murmur.presenter;


import com.bilibiliii.ga.bean.Murmur;
import com.bilibiliii.ga.murmur.contract.MurmurContract;
import com.bilibiliii.ga.murmur.model.GetMurmursModel;

import java.util.List;

/**
 * Created by Cherie_No.47 on 2016/7/11 14:11.
 * Email jascal@163.com
 */
public class MurmurPresenter implements MurmurContract.Presenter ,GetMurmursModel.OnGetMurmurResult{
    private MurmurContract.View view;
    public MurmurPresenter(MurmurContract.View view){
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void getMurmurData() {
        GetMurmursModel.initGetMurmursModel(this).getMurmur();
    }

    @Override
    public void onSuccess(List<Murmur> list) {
        view.onGetMurmurSuccess(list);
    }

    @Override
    public void onFailed() {
        view.onGetMurmurFail();
    }
}
