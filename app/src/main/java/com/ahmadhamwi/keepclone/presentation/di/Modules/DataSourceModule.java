package com.ahmadhamwi.keepclone.presentation.di.Modules;

import com.ahmadhamwi.keepclone.data.sources.datasource.persistent.NotePersistentDataSource;
import com.ahmadhamwi.keepclone.data.sources.datasource.persistent.NotePersistentDataSourceImpl;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public abstract class DataSourceModule {

    @Binds
    public abstract NotePersistentDataSource bindNotePersistentDataSource(NotePersistentDataSourceImpl notePersistentDataSource);

}
