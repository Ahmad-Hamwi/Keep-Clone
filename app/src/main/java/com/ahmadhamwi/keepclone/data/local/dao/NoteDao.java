package com.ahmadhamwi.keepclone.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.ahmadhamwi.keepclone.data.model.NoteModel;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

@Dao
public interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertNote(NoteModel note);

    @Query("SELECT * FROM Note ORDER BY NoteId DESC LIMIT 1")
    NoteModel getLastModifiedNote();

    @Query("SELECT * FROM Note ORDER BY DateModified DESC")
    Flowable<List<NoteModel>> getAllNotes();

    @Update
    void updateNote(NoteModel dataNote);

    @Query("SELECT * FROM Note WHERE NoteID = :noteId")
    Observable<NoteModel> getNoteById(int noteId);
}
