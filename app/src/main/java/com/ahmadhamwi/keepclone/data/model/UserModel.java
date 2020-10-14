package com.ahmadhamwi.keepclone.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserModel {

    @PrimaryKey
    private int id;

    private String name;

    private boolean isGuest;

    public UserModel(int id, boolean isGuest) {
        this.id = id;
        this.isGuest = isGuest;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGuest() {
        return isGuest;
    }

    public void setGuest(boolean guest) {
        isGuest = guest;
    }
}
