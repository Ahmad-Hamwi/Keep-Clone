package com.ahmadhamwi.keepclone.presentation.di.Modules;

import android.content.Context;

import androidx.room.Room;

import com.ahmadhamwi.keepclone.data.local.KeepRoomDatabase;
import com.ahmadhamwi.keepclone.data.local.dao.NoteDao;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;

@Module
@InstallIn(ApplicationComponent.class)
public class PersistentDataModule {

    @Provides
    public static KeepRoomDatabase provideKeepDataBase(
            @ApplicationContext Context appContext
    ) {
        return Room.databaseBuilder(appContext, KeepRoomDatabase.class, "KeepCloneDataBase").allowMainThreadQueries().build();
    }

    @Provides
    public static NoteDao provideNoteDao(
            KeepRoomDatabase database
    ) {
        return database.getNoteDao();
    }
}
