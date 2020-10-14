package com.ahmadhamwi.keepclone.data.model.mappers;

import java.util.ArrayList;
import java.util.List;

public abstract class ModelDomainDataMapper<DOMAIN, DATA> {
    public abstract DATA mapFromDomainToData(DOMAIN entity);

    public abstract DOMAIN mapFromDataToDomain(DATA dataModel);

    public List<DATA> mapListFromDomainToData(List<DOMAIN> entities) {
        List<DATA> dataModels = new ArrayList<>();
        for(DOMAIN entity: entities) {
            dataModels.add(mapFromDomainToData(entity));
        }
        return dataModels;
    }

    public List<DOMAIN> mapListFromDataToDomain(List<DATA> dataModels) {
        List<DOMAIN> entities = new ArrayList<>();
        for(DATA dataModel : dataModels) {
            entities.add(mapFromDataToDomain(dataModel));
        }
        return entities;
    }
}
