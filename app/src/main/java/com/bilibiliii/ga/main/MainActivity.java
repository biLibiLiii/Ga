package com.bilibiliii.ga.main;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.bilibiliii.ga.R;
import com.bilibiliii.ga.base.BaseActivity;
import com.bilibiliii.ga.bean.User;
import com.bilibiliii.ga.utils.bmob.CallBack;
import com.bilibiliii.ga.utils.bmob.UserProxy;

public class MainActivity extends BaseActivity {
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
        toolbar.setSubtitle("Bupt");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(toolbar, "Click setNavigationIcon", Snackbar.LENGTH_SHORT).show();
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_share:
                        Snackbar.make(toolbar, "Click Share", Snackbar.LENGTH_SHORT).show();
                        break;
                    case R.id.action_more:
                        Snackbar.make(toolbar, "Click More", Snackbar.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
        UserProxy.getInstance().login("testUse11r", "123456", new CallBack() {
            @Override
            public void onSuccess(User user) {
                Log.d(TAG,user.getUsername());
            }

            @Override
            public void onFail() {
                Log.d(TAG,"fail");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
