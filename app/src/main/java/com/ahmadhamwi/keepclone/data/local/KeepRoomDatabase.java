package com.ahmadhamwi.keepclone.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.ahmadhamwi.keepclone.data.local.dao.NoteDao;
import com.ahmadhamwi.keepclone.data.model.NoteModel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {
        NoteModel.class
}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class KeepRoomDatabase extends RoomDatabase {

    public abstract NoteDao getNoteDao();

    private static volatile KeepRoomDatabase instance;

    private static final int NUMBER_OF_THREADS = 4;
    private ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static KeepRoomDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (KeepRoomDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            KeepRoomDatabase.class, "keep_database")
                            .build();
                }
            }
        }
        return instance;
    }
}
