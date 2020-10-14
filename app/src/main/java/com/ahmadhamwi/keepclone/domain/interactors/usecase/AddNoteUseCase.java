package com.ahmadhamwi.keepclone.domain.interactors.usecase;

import com.ahmadhamwi.keepclone.domain.entity.NoteEntity;
import com.ahmadhamwi.keepclone.domain.interactors.usecase.base.UseCase;
import com.ahmadhamwi.keepclone.domain.repository.NoteRepository;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class AddNoteUseCase extends UseCase<NoteEntity, AddNoteUseCase.AddNoteParams> {

    private NoteRepository noteRepository;

    @Inject
    public AddNoteUseCase(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    protected Observable<NoteEntity> buildObservable() {
        return null;
    }

    @Override
    protected Observable<NoteEntity> buildParamsObservable(AddNoteParams addNoteParams) {
        return noteRepository.addNote(addNoteParams.noteEntity);
    }

    public static class AddNoteParams {
        private NoteEntity noteEntity;

        public AddNoteParams(NoteEntity noteEntity) {
            this.noteEntity = noteEntity;
        }
    }
}
