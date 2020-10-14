package com.ahmadhamwi.keepclone.presentation.ui.navigation.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.ahmadhamwi.keepclone.R;
import com.ahmadhamwi.keepclone.databinding.ActivityHomeBinding;
import com.ahmadhamwi.keepclone.presentation.ui.navigation.note.NoteActivity;
import com.ahmadhamwi.keepclone.presentation.ui.util.callbacks.OnNoteClick;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeActivity extends AppCompatActivity implements OnNoteClick {

    private static final String TAG = "HomeActivity";
    public static final int NEW_NOTE_INIT_ID = -1;

    private ActivityHomeBinding binding;
    private HomeViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        binding.setLifecycleOwner(this);
        binding.rvListNotes.setAdapter(new HomeNoteItemAdapter(this, this));
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding.setViewModel(viewModel);
        viewModel.getAllNotes();

        addUiListeners();
        addObserversOnViewModel();
    }

    private void addObserversOnViewModel() {
        viewModel.activeNotesLiveData.observe(this, notes -> {
            if(notes != null) {
                if(notes.size() == 0) {
                    binding.noNotesYetLayout.setVisibility(View.VISIBLE);
                } else {
                    binding.noNotesYetLayout.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    private void addUiListeners() {
        binding.addNoteFab.setOnClickListener(v -> onAddNote(NEW_NOTE_INIT_ID));
    }

    public void onAddNote(int noteId) {
        Intent intent = new Intent(this, NoteActivity.class);
        intent.putExtra(NoteActivity.NOTE_ID, noteId);
        startActivityForResult(intent, NoteActivity.REQUEST_CODE_ADD_NOTE);
    }

    private void initStatusBar() {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == NoteActivity.REQUEST_CODE_ADD_NOTE || requestCode == NoteActivity.REQUEST_CODE_EDIT_NOTE) {
            viewModel.getAllNotes();
        }
    }

    @Override
    public void onNoteClick(int noteId) {
        onAddNote(noteId);
    }
}