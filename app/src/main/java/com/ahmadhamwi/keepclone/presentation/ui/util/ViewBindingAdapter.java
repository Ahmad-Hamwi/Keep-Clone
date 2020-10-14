package com.ahmadhamwi.keepclone.presentation.ui.util;

import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmadhamwi.keepclone.presentation.ui.navigation.base.BaseListAdapter;

import java.util.List;

public class ViewBindingAdapter {

    private static final String TAG = "ViewBindingAdapter";

    @BindingAdapter("loadText")
    public static void loadText(TextView textView, String text) {
        textView.setText(text);
    }

    @BindingAdapter("loadText")
    public static void loadText(EditText editText, String text) {
        editText.setText(text);
    }

    @BindingAdapter("loadData")
    public static void loadData(RecyclerView recyclerView, List<?> data) {

        Log.d(TAG, "loadData: called, and data is " + (data == null ? "null" : "filled"));

        if(recyclerView.getAdapter() instanceof BaseListAdapter) {
            ((BaseListAdapter) recyclerView.getAdapter()).setData(data);
            recyclerView.getAdapter().notifyDataSetChanged();
        } else {
            Log.e(TAG, "loadData: implement BaseListAdapter in your source code.", new Exception());
        }
    }
}
