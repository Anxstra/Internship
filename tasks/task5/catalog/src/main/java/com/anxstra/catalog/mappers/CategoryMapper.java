package com.anxstra.catalog.mappers;

import com.anxstra.catalog.dto.CategoryDto;
import com.anxstra.jooq.db.tables.records.CategoriesRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "id", ignore = true)
    CategoriesRecord categoryDtoToCategoriesRecord(CategoryDto categoryDto);
}
