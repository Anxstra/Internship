package com.anxstra.catalog.mappers;

import com.anxstra.catalog.dto.DescriptionDto;
import com.anxstra.jooq.db.tables.records.DescriptionsRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DescriptionMapper {

    @Mapping(target = "id", ignore = true)
    DescriptionsRecord descriptionDtoToDescriptionsRecord(DescriptionDto descriptionDto);
}
