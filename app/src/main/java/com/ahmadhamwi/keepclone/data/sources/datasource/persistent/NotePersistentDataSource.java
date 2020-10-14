package com.ahmadhamwi.keepclone.data.sources.datasource.persistent;

import com.ahmadhamwi.keepclone.data.model.NoteModel;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

public interface NotePersistentDataSource {
    Flowable<List<NoteModel>> getCachedNotesInDataBaseWithTags(List<String> tags);

    Observable<NoteModel> addNote(NoteModel dataNote);

    Observable<NoteModel> getNoteById(int noteId);
}
