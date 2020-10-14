package com.ahmadhamwi.keepclone.presentation.model.mappers;

import java.util.ArrayList;
import java.util.List;

public abstract class ModelUiDomainMapper<UI, DOMAIN> {

    public abstract DOMAIN mapFromUiToDomain(UI uiModel);

    public abstract UI mapFromDomainToUi(DOMAIN entity);

    public List<DOMAIN> mapListFromUiToDomain(List<UI> uiModels) {
        List<DOMAIN> domainModels = new ArrayList<>();
        for(UI uiModel: uiModels) {
            domainModels.add(mapFromUiToDomain(uiModel));
        }
        return domainModels;
    }

    public List<UI> mapListFromDomainToUi(List<DOMAIN> entities) {
        List<UI> uiModels = new ArrayList<>();
        for(DOMAIN entity : entities) {
            uiModels.add(mapFromDomainToUi(entity));
        }
        return uiModels;
    }
}
