package com.revolut.newrevolut.presenter.list;

import com.revolut.newrevolut.view.list.CurrencyRowView;

import io.reactivex.subjects.PublishSubject;

public interface ICurrencyListPresenter {
    void bind(CurrencyRowView view);
    int getCount();
    PublishSubject<CurrencyRowView> getClickSubject();
}
