package com.bilibiliii.ga.murmur;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.bilibiliii.ga.R;
import com.bilibiliii.ga.bean.Murmur;
import com.bilibiliii.ga.murmur.adapter.MurmurListAdapter;
import com.bilibiliii.ga.murmur.contract.MurmurContract;
import com.bilibiliii.ga.murmur.presenter.MurmurPresenter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Cherie_No.47 on 2016/7/11 13:58.
 * Email jascal@163.com
 */
public class MurmurFragment extends Fragment implements MurmurContract.View {
    private MurmurContract.Presenter presenter;
    @Bind(R.id.murmur_list)
    RecyclerView recyclerView;

    @OnClick(R.id.murmur_to_edit)
    void editMurmur() {
        startActivity(EditMurmurActivity.getInstance(this.getActivity()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_murmur, container, false);
        ButterKnife.bind(this, view);
        new MurmurPresenter(this);
        presenter.getMurmurData();
        return view;
    }

    @Override
    public void setPresenter(MurmurContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onGetMurmurSuccess(List<Murmur> list) {
        MurmurListAdapter murmurListAdapter = new MurmurListAdapter(this.getActivity(), list);
        recyclerView.setAdapter(murmurListAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
    }

    @Override
    public void onGetMurmurFail() {
    }
}
