package com.ahmadhamwi.keepclone.domain.interactors.usecase;

import com.ahmadhamwi.keepclone.domain.entity.NoteEntity;
import com.ahmadhamwi.keepclone.domain.interactors.usecase.base.UseCase;
import com.ahmadhamwi.keepclone.domain.repository.NoteRepository;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class GetNoteUseCase extends UseCase<NoteEntity, GetNoteUseCase.GetNoteParams> {

    private NoteRepository repository;

    @Inject
    public GetNoteUseCase(NoteRepository noteRepository) {
        this.repository = noteRepository;
    }

    @Override
    protected Observable<NoteEntity> buildObservable() {
        return null;
    }

    @Override
    protected Observable<NoteEntity> buildParamsObservable(GetNoteUseCase.GetNoteParams getNoteParams) {
        return repository.getNoteById(getNoteParams.noteId);
    }

    public static class GetNoteParams {
        private int noteId;

        public GetNoteParams(int noteId) {
            this.noteId = noteId;
        }

        public int getNoteId() {
            return noteId;
        }
    }
}
