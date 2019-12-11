package com.revolut.newrevolut.network;

import android.annotation.SuppressLint;
import android.util.Log;

import com.revolut.newrevolut.entities.IOpenCurrency;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
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

    private static final String CURRENCY_URL = "http://data.fixer.io/api/";

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
        final URL uri = new URL(CURRENCY_URL + "836b3a424c5c4c96bd9eed677303e19b"); // BuildConfig.CURRENCY_API_KEY);
        Retrofit adapter = new Retrofit.Builder()
                .baseUrl("https://revolut.duckdns.org/")
             //   .baseUrl(uri)
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
