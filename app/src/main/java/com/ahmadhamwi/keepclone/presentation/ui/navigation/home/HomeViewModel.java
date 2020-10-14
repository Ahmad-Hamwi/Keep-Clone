package com.ahmadhamwi.keepclone.presentation.ui.navigation.home;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ahmadhamwi.keepclone.domain.entity.NoteEntity;
import com.ahmadhamwi.keepclone.domain.interactors.UseCaseDisposableObserver;
import com.ahmadhamwi.keepclone.domain.interactors.usecase.GetNotesUseCase;
import com.ahmadhamwi.keepclone.domain.interactors.usecase.base.UseCase;
import com.ahmadhamwi.keepclone.presentation.model.NoteUI;
import com.ahmadhamwi.keepclone.presentation.model.mappers.NoteUiDomainMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private static final String TAG = "HomeViewModel";

    public MutableLiveData<List<NoteUI>> activeNotesLiveData = new MutableLiveData<>();

    private List<UseCase<?, ?>> homeUseCases = new ArrayList<>();
    private GetNotesUseCase getNotesUseCase;

    private NoteUiDomainMapper noteUiDomainMapper;

    @ViewModelInject
    public HomeViewModel(GetNotesUseCase getNotesUseCase ,NoteUiDomainMapper mapper) {
        this.noteUiDomainMapper = mapper;
        this.getNotesUseCase = getNotesUseCase;

        registerUseCases(getNotesUseCase);
    }


    private void registerUseCases(UseCase<?, ?>... useCases) {
        homeUseCases.addAll(Arrays.asList(useCases));
    }

    public void getAllNotes() {
        getNotesUseCase.execute(new GetNotesUseCase.GetNotesParams(null),
                new UseCaseDisposableObserver<>(
                        noteEntities -> {
                            List<NoteUI> uiNotes = noteUiDomainMapper.mapListFromDomainToUi(noteEntities);
                            activeNotesLiveData.setValue(uiNotes);
                            Log.d(TAG, "getAllNotes: success home notes response delivered to homeviewmodel");
                            Log.d(TAG, "getAllNotes: notes count: " + uiNotes.size());
                            for(NoteEntity noteEntity : noteEntities) {
                                Log.d(TAG, "getAllNotes: note id: " + noteEntity.getNoteId());
                            }
                        },
                        error -> {
                            Log.e(TAG, "getAllNotes: something went wrong, error home notes response delivered to homeviewmodel", error);
                        }
                ));
    }

    @Override
    protected void onCleared() {
        for(UseCase<?, ?> useCase : homeUseCases) {
            useCase.dispose();
        }
        super.onCleared();
    }
}
