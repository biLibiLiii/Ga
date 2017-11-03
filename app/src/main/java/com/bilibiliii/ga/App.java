package com.bilibiliii.ga;

import android.app.Application;
import android.graphics.Typeface;

import java.lang.reflect.Field;

/**
 * @author No.47 create at 2017/11/3.
 */
public class App extends Application {
    public Typeface typeFace;

    @Override
    public void onCreate() {
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
