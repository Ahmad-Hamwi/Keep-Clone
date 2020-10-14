package com.ahmadhamwi.keepclone.presentation.ui.navigation.note;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.ahmadhamwi.keepclone.R;
import com.ahmadhamwi.keepclone.databinding.ActivityNoteBinding;
import com.ahmadhamwi.keepclone.presentation.ui.util.callbacks.TextWatcher;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class NoteActivity extends AppCompatActivity implements NoteViewModel.OnReadyToObserve{

    private static final String TAG = "NoteActivity";

    public static final String NOTE_ID = "NOTE_ID";
    public static final int REQUEST_CODE_ADD_NOTE = 0;
    public static final int REQUEST_CODE_EDIT_NOTE = 1;

    private ActivityNoteBinding binding;
    private NoteViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_note);
        viewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        initViewModel();
        setupToolbar();
    }

    private void initViewModel() {
        int noteId = getIntent().getExtras().getInt(NOTE_ID, -1);
        viewModel.init(noteId, this);
    }

    private void addObserversOnViewModel() {
        viewModel.displayedNote.title.observe(this, s ->  {
            if(!s.equals(binding.noteTitle.getText().toString())) {
                binding.noteTitle.setText(s);
            }
        });
        viewModel.displayedNote.body.observe(this, s -> {
            if(!s.equals(binding.noteBody.getText().toString())) {
                binding.noteBody.setText(s);
            }
        });
        viewModel.toastMessageLiveData.observe(this, toastMessage -> {
            if(toastMessage != null) {
                Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addUiListeners() {
        binding.noteTitle.addTextChangedListener(new TextWatcher(text ->    viewModel.displayedNote.title.setValue(text)));
        binding.noteBody.addTextChangedListener(new TextWatcher(text -> viewModel.displayedNote.body.setValue(text)));
    }

    private void setupToolbar() {
        setSupportActionBar(binding.toolbarLayout.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        if(viewModel.noteSavable()) {
            viewModel.saveNote();
        }
        super.onBackPressed();
    }

    /**
     * callback used to start observing the data that viewModel has successfully delivered asynchronously
     */

    @Override
    public void onReadyToObserve() {
        addObserversOnViewModel();
        addUiListeners();
    }

}