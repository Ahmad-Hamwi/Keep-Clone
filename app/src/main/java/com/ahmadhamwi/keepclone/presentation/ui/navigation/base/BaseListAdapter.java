package com.ahmadhamwi.keepclone.presentation.ui.navigation.base;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseListAdapter<VH extends BaseViewHolder<?, ?>> extends RecyclerView.Adapter<BaseViewHolder<?, ?>> {

    protected Context context;
    protected List<?> data;

    public BaseListAdapter(Context context) {
        this.context = context;
        this.data = new ArrayList<>();
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }
}
