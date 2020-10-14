package com.ahmadhamwi.keepclone.domain.interactors.usecase;

import com.ahmadhamwi.keepclone.domain.entity.NoteEntity;
import com.ahmadhamwi.keepclone.domain.interactors.usecase.base.UseCase;
import com.ahmadhamwi.keepclone.domain.repository.NoteRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class GetNotesUseCase extends UseCase<List<NoteEntity>, GetNotesUseCase.GetNotesParams> {

    private NoteRepository repository;

    @Inject
    public GetNotesUseCase(NoteRepository repository) {
        this.repository = repository;
    }

    @Override
    protected Observable<List<NoteEntity>> buildObservable() {
        return null;
    }

    @Override
    protected Observable<List<NoteEntity>> buildParamsObservable(GetNotesParams getNotesParams) {
        return repository.getNotes(getNotesParams.tags);
    }

    public static class GetNotesParams {
        private List<String> tags;

        public GetNotesParams(List<String> tags) {
            this.tags = tags;
        }
    }
}
