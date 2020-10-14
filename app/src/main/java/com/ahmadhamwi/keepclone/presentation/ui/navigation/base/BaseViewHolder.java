package com.ahmadhamwi.keepclone.presentation.ui.navigation.base;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseViewHolder<MODEL, BINDING extends ViewDataBinding> extends RecyclerView.ViewHolder {

    protected BINDING binding;

    public BaseViewHolder(BINDING binding) {
        super(binding.getRoot());
    }

    public abstract void bindDataWithViewHolder(MODEL model);
}
