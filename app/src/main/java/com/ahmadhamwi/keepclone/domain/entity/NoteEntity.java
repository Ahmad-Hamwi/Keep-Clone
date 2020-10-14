package com.ahmadhamwi.keepclone.domain.entity;

import java.util.Date;

public class NoteEntity {

    private int noteId;
    private String title;
    private String noteBody;
    private boolean isArchived;
    private boolean isTrashed;
    private Date createdAt;
    private Date ModifiedAt;

    public NoteEntity(int noteId, String title, String noteBody, boolean isArchived, boolean isTrashed, Date createdAt, Date modifiedAt) {
        this.noteId = noteId;
        this.title = title;
        this.noteBody = noteBody;
        this.isArchived = isArchived;
        this.isTrashed = isTrashed;
        this.createdAt = createdAt;
        ModifiedAt = modifiedAt;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNoteBody() {
        return noteBody;
    }

    public void setNoteBody(String noteBody) {
        this.noteBody = noteBody;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
    }

    public boolean isTrashed() {
        return isTrashed;
    }

    public void setTrashed(boolean trashed) {
        isTrashed = trashed;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModifiedAt() {
        return ModifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        ModifiedAt = modifiedAt;
    }

}
