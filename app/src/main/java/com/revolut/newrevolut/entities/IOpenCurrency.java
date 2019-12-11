package com.revolut.newrevolut.entities;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IOpenCurrency {
    @GET("latest")
    Observable<CurrencyRequestRestModel> loadCurrency(@Query("base") String currency);
}
