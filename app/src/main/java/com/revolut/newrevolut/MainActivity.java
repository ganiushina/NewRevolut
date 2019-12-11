package com.revolut.newrevolut;

import android.os.Bundle;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import com.revolut.newrevolut.adapter.CurrenciesRvAdapter;
import com.revolut.newrevolut.presenter.MainPresenter;
import com.revolut.newrevolut.view.MainView;

public class MainActivity extends MvpAppCompatActivity implements MainView  {

    @InjectPresenter
    MainPresenter presenter;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private CurrenciesRvAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @ProvidePresenter
    public MainPresenter createPresenter() {
        return new MainPresenter(AndroidSchedulers.mainThread());
    }

    @Override
    public void init() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new CurrenciesRvAdapter(presenter.getCurrencyListPresenter());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void updateList() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

}
