package com.ahmadhamwi.keepclone.data.model.mappers;

import com.ahmadhamwi.keepclone.data.model.NoteModel;
import com.ahmadhamwi.keepclone.domain.entity.NoteEntity;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class NoteDomainDataMapper extends ModelDomainDataMapper<NoteEntity, NoteModel> {

    @Inject
    public NoteDomainDataMapper() {

    }

    @Override
    public NoteModel mapFromDomainToData(NoteEntity entity) {
        return new NoteModel(
                entity.getNoteId(),
                entity.getTitle(),
                entity.getNoteBody(),
                entity.isArchived(),
                entity.isTrashed(),
                entity.getCreatedAt(),
                entity.getModifiedAt()
        );
    }

    @Override
    public NoteEntity mapFromDataToDomain(NoteModel dataModel) {
        return new NoteEntity(
                dataModel.getNoteId(),
                dataModel.getTitle(),
                dataModel.getBody(),
                dataModel.isArchived(),
                dataModel.isTrashed(),
                dataModel.getDateCreated(),
                dataModel.getDateModified()
        );
    }
}
