package com.ahmadhamwi.keepclone.presentation.ui.navigation.home;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.ahmadhamwi.keepclone.R;
import com.ahmadhamwi.keepclone.databinding.ItemNoteHomeBinding;
import com.ahmadhamwi.keepclone.presentation.model.NoteUI;
import com.ahmadhamwi.keepclone.presentation.ui.navigation.base.BaseListAdapter;
import com.ahmadhamwi.keepclone.presentation.ui.navigation.base.BaseViewHolder;
import com.ahmadhamwi.keepclone.presentation.ui.util.callbacks.OnNoteClick;

public class HomeNoteItemAdapter extends BaseListAdapter<BaseViewHolder<?, ?>> {

    private OnNoteClick callback;

    public HomeNoteItemAdapter(Context context, OnNoteClick callback) {
        super(context);
        this.callback = callback;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNoteHomeBinding noteBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_note_home, parent, false);
        return new NoteViewHolder(noteBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.bindDataWithViewHolder(data.get(position));
    }

    public class NoteViewHolder extends BaseViewHolder<NoteUI, ItemNoteHomeBinding> {

        private ItemNoteHomeBinding binding;

        public NoteViewHolder(ItemNoteHomeBinding binding) {
            super(binding);
            this.binding = binding;
        }

        @Override
        public void bindDataWithViewHolder(NoteUI noteUI) {
            binding.setNote(noteUI);
            binding.noteLayout.setOnClickListener(v -> {
                callback.onNoteClick(noteUI.getNoteId());
            });
        }


    }
}
