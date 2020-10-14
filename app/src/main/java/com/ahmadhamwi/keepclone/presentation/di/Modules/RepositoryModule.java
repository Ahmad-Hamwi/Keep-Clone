package com.ahmadhamwi.keepclone.presentation.di.Modules;

import com.ahmadhamwi.keepclone.data.sources.repository.NoteRepositoryImpl;
import com.ahmadhamwi.keepclone.domain.repository.NoteRepository;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public abstract class RepositoryModule {

    @Binds
    public abstract NoteRepository bindNoteRepository(NoteRepositoryImpl noteRepository);

}
