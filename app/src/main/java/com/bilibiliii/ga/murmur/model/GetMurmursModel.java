package com.bilibiliii.ga.murmur.model;


import com.bilibiliii.ga.bean.Murmur;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * @author No.47 create at 2017/11/20.
 */
public class GetMurmursModel {
    private OnGetMurmurResult onGetMurmurResult;

    public static GetMurmursModel initGetMurmursModel(OnGetMurmurResult onGetMurmurResult) {
        GetMurmursModel getMurmursModel = new GetMurmursModel();
        getMurmursModel.onGetMurmurResult = onGetMurmurResult;
        return getMurmursModel;
    }

    public void getMurmur() {
        BmobQuery<Murmur> query = new BmobQuery<>();
        query.setLimit(20);
        query.order("-createdAt");
        query.findObjects(new FindListener<Murmur>() {
            @Override
            public void done(List<Murmur> list, BmobException e) {
                if (null == e) {
                    onGetMurmurResult.onSuccess(list);
                } else {
                    onGetMurmurResult.onFailed();
                }
            }
        });
    }

    public interface OnGetMurmurResult {
        void onSuccess(List<Murmur> list);

        void onFailed();
    }
}
