package com.ahmadhamwi.keepclone.domain.repository;

import com.ahmadhamwi.keepclone.domain.entity.NoteEntity;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface NoteRepository {
    Observable<NoteEntity> addNote(NoteEntity domainNote);
    Observable<List<NoteEntity>> getNotes(List<String> tags);
    Observable<NoteEntity> getNoteById(int noteId);
}
