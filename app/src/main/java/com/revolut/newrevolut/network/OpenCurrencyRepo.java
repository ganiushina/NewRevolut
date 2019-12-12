package com.revolut.newrevolut.network;

import android.annotation.SuppressLint;

import com.revolut.newrevolut.entities.IOpenCurrency;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class OpenCurrencyRepo {
    private static OpenCurrencyRepo singleton = null;
    private IOpenCurrency API;

    public static final String BASE_CURRENCY = "EUR";

    private Disposable networkObservable;

    private OpenCurrencyRepo() {
        try {
            API = createAdapter();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static OpenCurrencyRepo getSingleton() {
        if(singleton == null) {
            singleton = new OpenCurrencyRepo();
        }
        return singleton;
    }

    public IOpenCurrency getAPI() {
        return API;
    }

    private IOpenCurrency createAdapter() throws MalformedURLException {
        Retrofit adapter = new Retrofit.Builder()
                .baseUrl("https://revolut.duckdns.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return adapter.create(IOpenCurrency.class);
    }

    @SuppressLint("TimberArgCount")
    public void getData() {
        networkObservable = Observable.interval(1, TimeUnit.SECONDS)
                .flatMap(n -> {
                            return API.loadCurrency(BASE_CURRENCY)
                                    .flatMap(Observable::just)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .onErrorResumeNext(observer -> {
                                    })
                                    .doOnNext(currencies -> {
                                        Timber.d("server", currencies.getBaseCurrency() + "   " + currencies.getRates());
                                    })
                                    .doOnError(throwable -> {
                                        Timber.e("server", "Error: " + throwable.getMessage());

                                    });
                        }
                ).subscribe();
    }

}
