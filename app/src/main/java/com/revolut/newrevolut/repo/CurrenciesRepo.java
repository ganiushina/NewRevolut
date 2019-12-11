package com.revolut.newrevolut.repo;

import com.revolut.newrevolut.api.DataSource;
import com.revolut.newrevolut.entities.Currency;
import com.revolut.newrevolut.entities.CurrencyRequestRestModel;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class CurrenciesRepo {

    private DataSource dataSource;
    private CurrencyRequestRestModel model;

    public CurrenciesRepo() {
        this.dataSource = new DataSource(model);
    }

    public Single<List<Currency>> getCountries() {
        return Single.fromCallable(() -> dataSource.loadCountries()).subscribeOn(Schedulers.io());
    }
}
