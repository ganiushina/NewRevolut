package com.revolut.newrevolut.repo;

import com.revolut.newrevolut.network.OpenCurrencyRepo;

public class ResponseRepository {

  //  List<Currency> currencies = new ArrayList<>();
    OpenCurrencyRepo currencyRepo;

    public ResponseRepository(OpenCurrencyRepo currencyRepo) {
        this.currencyRepo = currencyRepo;
    }

    //    private List<String> getDataFromServer() {
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

//    public Single<List<Currency>> getCurrencies() {
//    //    return Single.fromCallable(() -> currencyRepo.getData()).subscribeOn(Schedulers.io());
//    }

//    public Observable<List<String>> getData(){
//        return Observable.fromCallable(new Callable<List<String>>() {
//            @Override
//            public List<String> call() throws Exception {
//                return getDataFromServer();
//            }
//        }).subscribeOn(Schedulers.io());
//    }
}
