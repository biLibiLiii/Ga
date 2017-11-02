package com.bilibiliii.ga.utils.http;

import android.util.Log;

import com.bilibiliii.ga.bean.Event;
import com.bilibiliii.ga.bean.Result;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author No.47 create at 2017/11/2.
 */
public class RetroUtils {
    private final String TAG = getClass().getSimpleName();
    private Retrofit retrofit;

    public void get(String month, String day) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.juheapi.com/japi/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        HoTService service = retrofit.create(HoTService.class);
        service.getEvent("eff36bdaeeb868a6b8057a34f32d1326", "1.0", month, day)
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<Event>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(Result<Event> value) {
                        Log.d(TAG, "onNext");
                        Log.d(TAG, value.getResult().get(0).getDes());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError");
                        Log.d(TAG, e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete");
                    }
                });
    }

    public interface HoTService {
        @GET("toh")
        Observable<Result<Event>> getEvent(@Query("key") String key,
                                           @Query("version") String version,
                                           @Query("month") String month,
                                           @Query("day") String day);
    }
}
