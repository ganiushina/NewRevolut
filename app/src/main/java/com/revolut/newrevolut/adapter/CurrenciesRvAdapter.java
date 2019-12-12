package com.revolut.newrevolut.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.rxbinding2.view.RxView;
import com.revolut.newrevolut.R;
import com.revolut.newrevolut.presenter.list.ICurrencyListPresenter;
import com.revolut.newrevolut.view.list.CurrencyRowView;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CurrenciesRvAdapter extends RecyclerView.Adapter<CurrenciesRvAdapter.ViewHolder> {
    private ICurrencyListPresenter presenter;

    public CurrenciesRvAdapter(ICurrencyListPresenter presenter) {
        this.presenter = presenter;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.pos = position;
        presenter.bind(holder);
        RxView.clicks(holder.itemView).map(o -> holder).subscribe(presenter.getClickSubject());
    }

    @Override
    public int getItemCount() {
        return presenter.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements CurrencyRowView {
        int pos = 0;

        @BindView(R.id.textViewCurrency)
        TextView textViewCurrency;
        @BindView(R.id.editTextCount)
        TextView countTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public int getPos() {
            return pos;
        }

        @Override
        public void setTitle(String name) {
            textViewCurrency.setText(name);
        }

        @Override
        public void setCode(String value) {
            countTextView.setText(value);
        }
    }
}
