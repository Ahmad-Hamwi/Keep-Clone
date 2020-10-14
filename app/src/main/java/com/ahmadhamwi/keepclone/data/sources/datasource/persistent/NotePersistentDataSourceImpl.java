package com.ahmadhamwi.keepclone.data.sources.datasource.persistent;

import com.ahmadhamwi.keepclone.data.local.dao.NoteDao;
import com.ahmadhamwi.keepclone.data.model.NoteModel;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

public class NotePersistentDataSourceImpl implements NotePersistentDataSource{
    private NoteDao noteDao;

    @Inject
    public NotePersistentDataSourceImpl(
            NoteDao noteDao
    ) {
        this.noteDao = noteDao;
    }

    @Override
    public Flowable<List<NoteModel>> getCachedNotesInDataBaseWithTags(List<String> tags) {
        return noteDao.getAllNotes();
    }

    @Override
    public Observable<NoteModel> addNote(NoteModel dataNote) {
        dataNote.setDateCreated(new Date());
        dataNote.setDateModified(new Date());
        long newlyAddedNoteId = noteDao.insertNote(dataNote);
        return noteDao.getNoteById((int)newlyAddedNoteId);
    }

    @Override
    public Observable<NoteModel> getNoteById(int noteId) {
        return noteDao.getNoteById(noteId);
    }
}
