package com.bilibiliii.ga.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bilibiliii.ga.R;
import com.bilibiliii.ga.utils.http.RetroUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new RetroUtils().get("10","1");
    }
}
