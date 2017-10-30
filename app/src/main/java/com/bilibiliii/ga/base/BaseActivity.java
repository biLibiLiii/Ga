package com.bilibiliii.ga.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity{
    protected final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        initView();
        initPresenter();
        initData();
    }

    protected abstract void setContentView();

    protected abstract void initView();

    protected abstract void initPresenter();

    protected abstract void initData();
}
