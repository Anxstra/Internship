package com.anxstra.catalog.mappers;

import com.anxstra.catalog.dto.ProductDto;
import com.anxstra.jooq.db.tables.records.ProductsRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    ProductsRecord productDtoToProductsRecord(ProductDto productDto);
}
