package com.anxstra.catalog.services;

import com.anxstra.catalog.dto.ProductDetailsDto;
import com.anxstra.catalog.dto.ProductDto;
import com.anxstra.catalog.dto.ProductListDto;
import com.anxstra.catalog.mappers.ProductMapper;
import com.anxstra.catalog.repositories.ProductRepository;
import com.anxstra.catalog.util.Page;
import com.anxstra.jooq.db.tables.records.ProductsRecord;
import com.anxstra.jwtfilterspringbootstarter.exception.ApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.anxstra.catalog.constants.MessageConstants.ENTITY_PERSISTED;
import static com.anxstra.catalog.constants.MessageConstants.ENTITY_TRANSIENT;
import static com.anxstra.catalog.constants.MessageConstants.PRODUCT_INSERT;
import static com.anxstra.catalog.constants.MessageConstants.PRODUCT_NOT_FOUND;
import static com.anxstra.catalog.constants.MessageConstants.PRODUCT_UPDATE;
import static com.anxstra.jwtfilterspringbootstarter.exception.AppErrors.badRequestOf;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    public List<ProductListDto> findAll(Long category, Integer pageNumber, Integer pageSize) {

        if (Objects.nonNull(pageNumber) && Objects.nonNull(pageSize)) {
            Page page = new Page(pageNumber, pageSize);
            return productRepository.getAllByCategoryIdAndPage(category, page);
        } else {
            return productRepository.getAllByCategoryId(category);
        }
    }

    public ProductDetailsDto findById(Long id) {

        return productRepository.getById(id)
                                .orElseThrow(() -> new ApplicationException(badRequestOf(PRODUCT_NOT_FOUND)));
    }

    public void delete(Long id) {

        productRepository.delete(id);
    }

    public void insert(ProductDto productDto) {

        ProductsRecord product = productMapper.productDtoToProductsRecord(productDto);

        if (Objects.isNull(product.getId()) && Objects.isNull(product.getVersion())) {
            int result = productRepository.insert(product);
            if (result == 0) {
                throw new ApplicationException(badRequestOf(PRODUCT_INSERT));
            }
        } else {
            throw new ApplicationException(badRequestOf(ENTITY_PERSISTED));
        }
    }

    public void update(Long id, ProductDto productDto) {

        ProductsRecord product = productMapper.productDtoToProductsRecord(productDto);

        if (Objects.nonNull(product.getVersion())) {
            int result = productRepository.update(id, product);
            if (result == 0) {
                throw new ApplicationException(badRequestOf(PRODUCT_UPDATE));
            }
        } else {
            throw new ApplicationException(badRequestOf(ENTITY_TRANSIENT));
        }
    }
}
