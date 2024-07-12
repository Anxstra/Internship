package com.anxstra.catalog.services;

import com.anxstra.catalog.dto.CategoryDto;
import com.anxstra.catalog.dto.CategoryTreeDto;
import com.anxstra.catalog.mappers.CategoryMapper;
import com.anxstra.catalog.repositories.CategoryRepository;
import com.anxstra.jooq.db.tables.records.CategoriesRecord;
import com.anxstra.jwtfilterspringbootstarter.exception.ApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.anxstra.catalog.constants.MessageConstants.CATEGORY_INSERT;
import static com.anxstra.catalog.constants.MessageConstants.CATEGORY_UPDATE;
import static com.anxstra.catalog.constants.MessageConstants.ENTITY_PERSISTED;
import static com.anxstra.catalog.constants.MessageConstants.ENTITY_TRANSIENT;
import static com.anxstra.jwtfilterspringbootstarter.exception.AppErrors.badRequestOf;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"categories"})
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    @CacheEvict(key = "'categories'")
    public void insert(CategoryDto categoryDto) {

        CategoriesRecord category = categoryMapper.categoryDtoToCategoriesRecord(categoryDto);

        if (Objects.isNull(category.getId()) && Objects.isNull(category.getVersion())) {
            int result = categoryRepository.insert(category);
            if (result == 0) {
                throw new ApplicationException(badRequestOf(CATEGORY_INSERT));
            }
        } else {
            throw new ApplicationException(badRequestOf(ENTITY_PERSISTED));
        }
    }

    @CacheEvict(key = "'categories'")
    public void update(Long id, CategoryDto categoryDto) {

        CategoriesRecord category = categoryMapper.categoryDtoToCategoriesRecord(categoryDto);

        if (Objects.nonNull(category.getVersion())) {
            int result = categoryRepository.update(id, category);
            if (result == 0) {
                throw new ApplicationException(badRequestOf(CATEGORY_UPDATE));
            }
        } else {
            throw new ApplicationException(badRequestOf(ENTITY_TRANSIENT));
        }
    }

    @CacheEvict(key = "'categories'")
    public void delete(Long id) {

        categoryRepository.delete(id);
    }

    @Cacheable(key = "'categories'")
    public List<CategoryTreeDto> getAll() {

        return categoryRepository.getAll();
    }
}
