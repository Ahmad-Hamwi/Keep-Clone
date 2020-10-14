package com.ahmadhamwi.keepclone.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "Note")
public class NoteModel {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "NoteID")
    private int noteId;

    @ColumnInfo(name = "Title")
    private String title;

    @ColumnInfo(name = "Body")
    private String body;

    @ColumnInfo(name = "IsArchived")
    private boolean isArchived;

    @ColumnInfo(name = "IsTrashed")
    private boolean isTrashed;

    @ColumnInfo(name = "DateCreated")
    private Date dateCreated;

    @ColumnInfo(name = "DateModified")
    private Date dateModified;

    public NoteModel(int noteId, String title, String body, boolean isArchived, boolean isTrashed, Date dateCreated, Date dateModified) {
        this.noteId = noteId;
        this.title = title;
        this.body = body;
        this.isArchived = isArchived;
        this.isTrashed = isTrashed;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

}
