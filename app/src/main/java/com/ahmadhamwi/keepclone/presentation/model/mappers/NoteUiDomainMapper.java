package com.ahmadhamwi.keepclone.presentation.model.mappers;

import com.ahmadhamwi.keepclone.domain.entity.NoteEntity;
import com.ahmadhamwi.keepclone.presentation.model.NoteUI;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class NoteUiDomainMapper extends ModelUiDomainMapper<NoteUI, NoteEntity> {

    @Inject
    public NoteUiDomainMapper() {
    }

    @Override
    public NoteEntity mapFromUiToDomain(NoteUI uiModel) {
        String title = null, body = null;
        boolean isArchived = false, isTrashed = false;
        Date dateCreated = null, dateModified = null;
        if(uiModel.title != null) title = uiModel.title.getValue();
        if(uiModel.body != null) body = uiModel.body.getValue();
        if(uiModel.isArchived != null) isArchived = uiModel.isArchived.get();
        if(uiModel.isTrashed != null) isTrashed = uiModel.isTrashed.get();
        if(uiModel.dateCreated != null) dateCreated = uiModel.dateCreated.getValue();
        if(uiModel.dateModified != null) dateModified = uiModel.dateModified.getValue();
        return new NoteEntity(
                uiModel.getNoteId(),
                title,
                body,
                isArchived,
                isTrashed,
                dateCreated,
                dateModified
        );
    }

    @Override
    public NoteUI mapFromDomainToUi(NoteEntity entity) {
        return new NoteUI(
                entity.getNoteId(),
                entity.getTitle(),
                entity.getNoteBody(),
                entity.isArchived(),
                entity.isTrashed(),
                entity.getCreatedAt(),
                entity.getModifiedAt()
        );
    }
}
