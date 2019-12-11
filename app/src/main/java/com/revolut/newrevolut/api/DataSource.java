package com.revolut.newrevolut.api;

import com.revolut.newrevolut.entities.Currency;
import com.revolut.newrevolut.entities.CurrencyRequestRestModel;

import java.util.ArrayList;
import java.util.List;

public class DataSource {

    private List<Currency> currencies = new ArrayList<>();

    public DataSource(CurrencyRequestRestModel model) {
        model.getRates().forEach((k, v) -> currencies.add(new Currency(k , v)));

//        currencies.add(new Currency("AUD", model.rates.AUD));
//        currencies.add(new Currency("BGN", model.rates.BGN));
//        currencies.add(new Currency("BRL", model.rates.BRL));
//        currencies.add(new Currency("CAD", model.rates.CAD));
//        currencies.add(new Currency("CHF", model.rates.CHF));
//        currencies.add(new Currency("CNY", model.rates.CNY));
//        currencies.add(new Currency("CZK", model.rates.CZK));
//        currencies.add(new Currency("DKK", model.rates.DKK));
//        currencies.add(new Currency("GBP", model.rates.GBP));
//        currencies.add(new Currency("HKD", model.rates.HKD));
//        currencies.add(new Currency("HRK", model.rates.HRK));
//        currencies.add(new Currency("HUF", model.rates.HUF));
//        currencies.add(new Currency("IDR", model.rates.IDR));
//        currencies.add(new Currency("ILS", model.rates.ILS));
//        currencies.add(new Currency("INR", model.rates.INR));
//        currencies.add(new Currency("ISK", model.rates.ISK));
//        currencies.add(new Currency("JPY", model.rates.JPY));
//        currencies.add(new Currency("KRW", model.rates.KRW));
//        currencies.add(new Currency("MXN", model.rates.MXN));
//        currencies.add(new Currency("MYR", model.rates.MYR));
//        currencies.add(new Currency("NOK", model.rates.NOK));
//        currencies.add(new Currency("NZD", model.rates.NZD));
//        currencies.add(new Currency("PHP", model.rates.PHP));
//        currencies.add(new Currency("PLN", model.rates.PLN));
//        currencies.add(new Currency("RON", model.rates.RON));
//        currencies.add(new Currency("RUB", model.rates.RUB));
//        currencies.add(new Currency("SEK", model.rates.SEK));
//        currencies.add(new Currency("SGD", model.rates.SGD));
//        currencies.add(new Currency("THB", model.rates.THB));
//        currencies.add(new Currency("TRY", model.rates.TRY));
//        currencies.add(new Currency("USD", model.rates.USD));
//        currencies.add(new Currency("ZAR", model.rates.ZAR));
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

}
