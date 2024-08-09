package com.anxstra.catalog.controllers;

import com.anxstra.catalog.dto.CategoryDto;
import com.anxstra.catalog.dto.CategoryTreeDto;
import com.anxstra.catalog.services.CategoryService;
import com.anxstra.jwtfilterspringbootstarter.dto.BaseResponseEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.anxstra.catalog.constants.PathConstants.CATEGORIES_PATH;
import static com.anxstra.catalog.constants.PathConstants.ID_PATH;

@RestController
@RequiredArgsConstructor
@RequestMapping(CATEGORIES_PATH)
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BaseResponseEntity<Void>> addCategory(@RequestBody @Valid CategoryDto categoryDto) {

        categoryService.insert(categoryDto);

        return ResponseEntity.status(HttpStatus.OK)
                             .body(BaseResponseEntity.ok());
    }

    @PutMapping(ID_PATH)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BaseResponseEntity<Void>> updateCategory(@PathVariable Long id, @RequestBody @Valid CategoryDto categoryDto) {

        categoryService.update(id, categoryDto);

        return ResponseEntity.status(HttpStatus.OK)
                             .body(BaseResponseEntity.ok());
    }

    @DeleteMapping(ID_PATH)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BaseResponseEntity<Void>> deleteProduct(@PathVariable Long id) {

        categoryService.delete(id);

        return ResponseEntity.status(HttpStatus.OK)
                             .body(BaseResponseEntity.ok());
    }

    @GetMapping
    public ResponseEntity<BaseResponseEntity<List<CategoryTreeDto>>> getAll() {

        List<CategoryTreeDto> result = categoryService.getAll();

        return ResponseEntity.status(HttpStatus.OK)
                             .body(BaseResponseEntity.ok(result));
    }
}
