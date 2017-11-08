package com.bilibiliii.ga;

import android.app.Application;
import android.graphics.Typeface;

import com.bilibiliii.ga.utils.Common;

import java.lang.reflect.Field;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;

/**
 * @author No.47 create at 2017/11/3.
 */
public class App extends Application {
    public Typeface typeFace;

    @Override
    public void onCreate() {
        BmobConfig config = new BmobConfig.Builder(this)
                .setApplicationId(Common.BMOB_APPLICATION_KEY)
                .setConnectTimeout(10)
                .setUploadBlockSize(1024 * 1024)
                .setFileExpiration(2500)
                .build();
        Bmob.initialize(config);

        typeFace = Typeface.createFromAsset(getAssets(), "stfangso.ttf");
        try {
            Field field = Typeface.class.getDeclaredField("SERIF");
            field.setAccessible(true);
            field.set(null, typeFace);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        super.onCreate();
    }
}
