package com.revolut.newrevolut.repo;

import com.revolut.newrevolut.api.DataSource;
import com.revolut.newrevolut.entities.Currency;
import com.revolut.newrevolut.entities.CurrencyRequestRestModel;
import com.revolut.newrevolut.network.OpenCurrencyRepo;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class CurrenciesRepo {

    private DataSource dataSource;
    private CurrencyRequestRestModel model;
    private OpenCurrencyRepo repo;

    public CurrenciesRepo() {
        this.repo =  OpenCurrencyRepo.getSingleton();
        this.dataSource = new DataSource(repo);
    }

    public Single<List<Currency>> getCountries() {
        return Single.fromCallable(() -> dataSource.loadCountries()).subscribeOn(Schedulers.io());
    }
}
