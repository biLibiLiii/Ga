package com.bilibiliii.ga.utils.http;

import android.util.Log;

import com.bilibiliii.ga.bean.Event;
import com.bilibiliii.ga.bean.Result;
import com.bilibiliii.ga.utils.Common;
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
public class RetrofitUtils {
    private final String TAG = getClass().getSimpleName();

    public void getHistoryEvent(final CallBack callBack, String month, String day) throws CallBackException {
        if (callBack == null) {
            throw new CallBackException(Common.RETROFIT_CALLBACK_EXCEPTION);
        }
        Gson gson = new GsonBuilder()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Common.HISTORY_EVENT_BASE)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        HoTService service = retrofit.create(HoTService.class);
        service.getEvent(Common.HISTORY_EVENT_KEY_VALUE, Common.HISTORY_EVENT_VERSION_VALUE, month, day)
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<Event>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(Result<Event> value) {
                        callBack.onNext(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError(e);
                    }

                    @Override
                    public void onComplete() {
                        callBack.onComplete();
                    }
                });
    }

    interface HoTService {
        @GET("toh")
        Observable<Result<Event>> getEvent(@Query(Common.HISTORY_EVENT_KEY) String key,
                                           @Query(Common.HISTORY_EVENT_VERSION) String version,
                                           @Query(Common.HISTORY_EVENT_MONTH) String month,
                                           @Query(Common.HISTORY_EVENT_DAY) String day);
    }
}
