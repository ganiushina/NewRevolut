package com.revolut.newrevolut.entities;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.Map;

public class CurrencyRequestRestModel {
    @SerializedName("base") public String baseCurrency;
    @SerializedName("date") public Date dateUpdate;
   // @SerializedName("rates") public RatesRestModel rates;
   @SerializedName("rates") Map<String, Float> rates;

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public Map<String, Float> getRates() {
        return rates;
    }

    public void setRates(Map<String, Float> rates) {
        this.rates = rates;
    }
}
