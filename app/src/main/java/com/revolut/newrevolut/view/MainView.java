package com.revolut.newrevolut.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface MainView extends MvpView, moxy.MvpView {
    void init();
    void updateList();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showMessage(String text);

//    void showLoading();
//    void hideLoading();
}
