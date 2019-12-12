package com.revolut.newrevolut.view;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface MainView extends MvpView {
    void init();
    void updateList();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showMessage(String text);
}
