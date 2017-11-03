package com.bilibiliii.ga.main;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.bilibiliii.ga.R;
import com.bilibiliii.ga.base.BaseActivity;
import com.bilibiliii.ga.bean.Event;
import com.bilibiliii.ga.bean.Result;
import com.bilibiliii.ga.utils.http.CallBack;
import com.bilibiliii.ga.utils.http.CallBackException;
import com.bilibiliii.ga.utils.http.RetrofitUtils;

public class MainActivity extends BaseActivity implements CallBack<Event> {
    private final String TAG = getClass().getSimpleName();
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initView() {
        toolbar = ((Toolbar) findViewById(R.id.main_toolbar));
        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setTitle("Hello");
        toolbar.setSubtitle("bupt");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(toolbar, "Click setNavigationIcon", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onNext(Result<Event> value) {
        Log.d(TAG, value.getResult().get(0).getDes());
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
