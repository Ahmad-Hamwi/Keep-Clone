package com.ahmadhamwi.keepclone.presentation.model;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;

import java.util.Date;

public class NoteUI {
    private int noteId;
    public MutableLiveData<String> title;
    public MutableLiveData<String> body;
    public ObservableBoolean isArchived;
    public ObservableBoolean isTrashed;
    public MutableLiveData<Date> dateCreated;
    public MutableLiveData<Date> dateModified;

    public NoteUI() {
        title = new MutableLiveData<>("");
        body = new MutableLiveData<>("");
        isArchived = new ObservableBoolean();
        isTrashed = new ObservableBoolean();
    }

    public NoteUI(int noteId, String title, String body, boolean isArchived, boolean isTrashed, Date dateCreated, Date dateModified) {
        this.noteId = noteId;
        this.title = new MutableLiveData<>(title);
        this.body = new MutableLiveData<>(body);
        this.isArchived = new ObservableBoolean(isArchived);
        this.isTrashed = new ObservableBoolean(isTrashed);
        this.dateCreated = new MutableLiveData<>(dateCreated);
        this.dateModified = new MutableLiveData<>(dateModified);
    }

    public int getNoteId() {
        return noteId;
    }
}
