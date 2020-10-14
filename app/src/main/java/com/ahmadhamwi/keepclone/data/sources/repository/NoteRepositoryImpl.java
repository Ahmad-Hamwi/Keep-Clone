package com.ahmadhamwi.keepclone.data.sources.repository;

import com.ahmadhamwi.keepclone.data.model.NoteModel;
import com.ahmadhamwi.keepclone.data.model.mappers.NoteDomainDataMapper;
import com.ahmadhamwi.keepclone.data.sources.datasource.persistent.NotePersistentDataSource;
import com.ahmadhamwi.keepclone.domain.entity.NoteEntity;
import com.ahmadhamwi.keepclone.domain.repository.NoteRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class NoteRepositoryImpl implements NoteRepository {

    private NotePersistentDataSource notePersistentDataSource;
    private NoteDomainDataMapper mapper;

    @Inject
    public NoteRepositoryImpl(NotePersistentDataSource notePersistentDataSource, NoteDomainDataMapper mapper) {
        this.notePersistentDataSource = notePersistentDataSource;
        this.mapper = mapper;
    }

    @Override
    public Observable<NoteEntity> addNote(NoteEntity domainNote) {
        NoteModel paramDataNote = mapper.mapFromDomainToData(domainNote); //the note converted to the data layer model
        Observable<NoteModel> returnedDataNoteObservable = notePersistentDataSource.addNote(paramDataNote); //the the returned observable from the datasource having the added note.
        return returnedDataNoteObservable.map(mapper::mapFromDataToDomain); //returning the observable but mapping the data to domain layer.
    }

    @Override
    public Observable<List<NoteEntity>> getNotes(List<String> tags) {
        return notePersistentDataSource.getCachedNotesInDataBaseWithTags(tags).map(mapper::mapListFromDataToDomain).toObservable();
    }

    @Override
    public Observable<NoteEntity> getNoteById(int noteId) {
        return notePersistentDataSource.getNoteById(noteId).map(mapper::mapFromDataToDomain);
    }
}
