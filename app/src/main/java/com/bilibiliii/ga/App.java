package com.bilibiliii.ga;

import android.app.Application;
import android.graphics.Typeface;

import com.bilibiliii.ga.utils.Common;
import com.bilibiliii.ga.utils.bmob.UserMessageHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Field;

import cn.bmob.newim.BmobIM;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;

/**
 * @author No.47 create at 2017/11/3.
 */
public class App extends Application {
    public Typeface typeFace;

    @Override
    public void onCreate() {
        super.onCreate();
        // init bmob
        BmobConfig config = new BmobConfig.Builder(this)
                .setApplicationId(Common.BMOB_APPLICATION_KEY)
                .setConnectTimeout(10)
                .setUploadBlockSize(1024 * 1024)
                .setFileExpiration(2500)
                .build();
        Bmob.initialize(config);

        // init bmob im
        if(getApplicationInfo().packageName.equals(getMyProcessName())){
            BmobIM.init(this);
            BmobIM.registerDefaultMessageHandler(new UserMessageHandler(this));
        }

        // set global fonts
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


    }

    public static String getMyProcessName() {
        try {
            File file = new File("/proc/" + android.os.Process.myPid() + "/" + "cmdline");
            BufferedReader mBufferedReader = new BufferedReader(new FileReader(file));
            String processName = mBufferedReader.readLine().trim();
            mBufferedReader.close();
            return processName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
