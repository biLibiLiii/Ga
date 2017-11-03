package com.bilibiliii.ga.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.bilibiliii.ga.R;
import com.bilibiliii.ga.bean.Event;
import com.bilibiliii.ga.bean.Result;
import com.bilibiliii.ga.utils.http.CallBack;
import com.bilibiliii.ga.utils.http.CallBackException;
import com.bilibiliii.ga.utils.http.RetrofitUtils;

public class MainActivity extends AppCompatActivity implements CallBack<Event> {
    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            new RetrofitUtils().getHistoryEvent(this, "11", "3");
        } catch (CallBackException e) {
            e.printStackTrace();
        }
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
