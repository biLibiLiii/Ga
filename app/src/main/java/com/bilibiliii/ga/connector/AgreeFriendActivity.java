package com.bilibiliii.ga.connector;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bilibiliii.ga.R;
import com.bilibiliii.ga.base.BaseActivity;
import com.bilibiliii.ga.bean.NewFriend;
import com.bilibiliii.ga.utils.db.NewFriendManager;

import java.util.List;

public class AgreeFriendActivity extends BaseActivity {
    private RecyclerView mAgreeFriendRecyclerview;
    private List<NewFriend> mNewFriendList;
    private NewFriendManager mNewFriendManager=NewFriendManager.getInstance(this);
    private AgreeFriendAdapter mAgreeFriendAdapter;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_agree_friend);
    }

    @Override
    protected void initView() {
        mAgreeFriendRecyclerview=(RecyclerView)findViewById(R.id.agree_friend_recyclerview);
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
        mNewFriendList=mNewFriendManager.getAllNewFriend();
        mAgreeFriendAdapter=new AgreeFriendAdapter(mNewFriendList);
        mAgreeFriendRecyclerview.setAdapter(mAgreeFriendAdapter);
        mAgreeFriendRecyclerview.setLayoutManager(new LinearLayoutManager(this));
    }
}
