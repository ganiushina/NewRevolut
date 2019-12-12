package com.revolut.newrevolut.api;

import com.revolut.newrevolut.entities.Currency;
import com.revolut.newrevolut.entities.CurrencyRequestRestModel;
import com.revolut.newrevolut.network.OpenCurrencyRepo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class DataSource {

    private List<Currency> currencies = new ArrayList<>();
    private OpenCurrencyRepo repo;

    public DataSource(OpenCurrencyRepo repo) {
        this.repo = repo;
        repo.getData();
    }

    public List<Currency> loadCountries(){
        //Emulate long net call
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return currencies;
    }

//    public Single<List<Currency>> getCountries() {
//        return Single.fromCallable(() -> dataSource.loadCountries()).subscribeOn(Schedulers.io());
//    }

}
