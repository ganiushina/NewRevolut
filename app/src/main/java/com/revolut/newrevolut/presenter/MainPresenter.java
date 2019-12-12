package com.revolut.newrevolut.presenter;

import android.annotation.SuppressLint;

import com.revolut.newrevolut.api.DataSource;
import com.revolut.newrevolut.entities.Currency;
import com.revolut.newrevolut.network.OpenCurrencyRepo;
import com.revolut.newrevolut.presenter.list.ICurrencyListPresenter;
import com.revolut.newrevolut.repo.CurrenciesRepo;
import com.revolut.newrevolut.view.MainView;
import com.revolut.newrevolut.view.list.CurrencyRowView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.subjects.PublishSubject;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import moxy.MvpView;
import timber.log.Timber;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> implements MvpView {

    class CurrencyListPresenter implements ICurrencyListPresenter {

        PublishSubject<CurrencyRowView> clickSubject = PublishSubject.create();

        List<Currency> currencies = new ArrayList<>();

         @Override
        public void bind(CurrencyRowView view) {
            Currency currency = currencies.get(view.getPos());
            view.setTitle(currency.getNameCurrency());
            view.setCode(String.valueOf(currency.getCurrencyValue()));
        }

        @Override
        public int getCount() {
            return currencies.size();
        }

        @Override
        public PublishSubject<CurrencyRowView> getClickSubject() {
            return clickSubject;
        }
    }

    private Scheduler mainThreadScheduler;
    private CurrenciesRepo repo;
    private CurrencyListPresenter currencyListPresenter;



    public MainPresenter(Scheduler scheduler) {
        this.mainThreadScheduler = scheduler;
        this.repo = new CurrenciesRepo();
        this.currencyListPresenter = new CurrencyListPresenter();
    }

    @SuppressLint("CheckResult")
    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().init();

        repo.getCountries()
                .observeOn(mainThreadScheduler)
                .subscribe(currencies -> {
                    currencyListPresenter.currencies.clear();
                    currencyListPresenter.currencies.addAll(currencies);
                    getViewState().updateList();

                }, t-> Timber.e(t));

        currencyListPresenter.getClickSubject().subscribe(currencyRowView -> {
            Currency currency = currencyListPresenter.currencies.get(currencyRowView.getPos());
            getViewState().showMessage(currency.getNameCurrency() + " " + String.valueOf(currency.getCurrencyValue()));
        });
    }

    public ICurrencyListPresenter getCurrencyListPresenter(){
        return currencyListPresenter;
    }
}
