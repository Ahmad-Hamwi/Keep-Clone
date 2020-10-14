package com.ahmadhamwi.keepclone.presentation.ui.navigation.note;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ahmadhamwi.keepclone.domain.interactors.UseCaseDisposableObserver;
import com.ahmadhamwi.keepclone.domain.interactors.usecase.AddNoteUseCase;
import com.ahmadhamwi.keepclone.domain.interactors.usecase.GetNoteUseCase;
import com.ahmadhamwi.keepclone.domain.interactors.usecase.base.UseCase;
import com.ahmadhamwi.keepclone.presentation.model.NoteUI;
import com.ahmadhamwi.keepclone.presentation.model.mappers.NoteUiDomainMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NoteViewModel extends ViewModel {

    private static final String TAG = "NoteViewModel";

    public NoteUI displayedNote;

    List<UseCase<?, ?>> noteUseCases = new ArrayList<>();
    private AddNoteUseCase addNoteUseCase;
    private GetNoteUseCase getNoteUseCase;

    private NoteUiDomainMapper mapper;

    public MutableLiveData<String> toastMessageLiveData = new MutableLiveData<>(null);

    @ViewModelInject
    public NoteViewModel(
            GetNoteUseCase getNoteUseCase,
            AddNoteUseCase addNoteUseCase,
            NoteUiDomainMapper mapper
    ) {
        this.getNoteUseCase = getNoteUseCase;
        this.addNoteUseCase = addNoteUseCase;
        this.mapper = mapper;

        registerUseCases(getNoteUseCase, addNoteUseCase);

    }

    void registerUseCases(UseCase<?, ?>... useCases) {
        noteUseCases.addAll(Arrays.asList(useCases));
    }

    public void saveNote() {
        addNoteUseCase.execute(
                new AddNoteUseCase.AddNoteParams(mapper.mapFromUiToDomain(displayedNote)),
                new UseCaseDisposableObserver<>(
                        addedNoteInEntityForm -> {
                            toastMessageLiveData.setValue("Note Saved.");
                            Log.d(TAG, "saveNote: note has been added successfully, note id is "  + addedNoteInEntityForm.getNoteId());
                            //todo make an event toast of success
                        },
                        error -> {
                            toastMessageLiveData.setValue("Error saving note.");
                            Log.e(TAG, "saveNote: note is not added, something wrong happended. " + error.getMessage(), error);
                            //todo make an event toast of an error
                        }
                ));
    }

    public boolean noteSavable() {
        return !displayedNote.title.getValue().equals("") || !displayedNote.body.getValue().equals("");
    }

    @Override
    protected void onCleared() {
        for(UseCase<?, ?> useCase : noteUseCases) {
            useCase.dispose();
        }
        super.onCleared();
    }

    /**
     * initializes viewModel with the correct note data.
     * @param noteId the note that is being displayed in NoteActivity.
     * @param callback should be called after data has been delivered successfully.
     */

    public void init(int noteId, OnReadyToObserve callback) {
        if(noteId == -1) {
            displayedNote = new NoteUI();
            callback.onReadyToObserve();
        } else {
            getNoteUseCase.execute(new GetNoteUseCase.GetNoteParams(noteId), new UseCaseDisposableObserver<>(
                    noteEntity -> {
                        Log.d(TAG, "init: note received for editing");
                        displayedNote = mapper.mapFromDomainToUi(noteEntity);
                        callback.onReadyToObserve();
                    },
                    error -> {
                        Log.e(TAG, "init: something is wrong", error);
                    }
            ));
        }
    }

    public interface OnReadyToObserve {
        void onReadyToObserve();
    }
}
