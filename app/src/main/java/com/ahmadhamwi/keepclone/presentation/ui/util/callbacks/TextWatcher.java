package com.ahmadhamwi.keepclone.presentation.ui.util.callbacks;

import android.text.Editable;

public class TextWatcher implements android.text.TextWatcher {

    private OnTextChanged onTextChanged;

    public TextWatcher(OnTextChanged onTextChanged) {
        this.onTextChanged = onTextChanged;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(onTextChanged != null) {
            String text = String.valueOf(s);
            onTextChanged.onTextChanged(text);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
