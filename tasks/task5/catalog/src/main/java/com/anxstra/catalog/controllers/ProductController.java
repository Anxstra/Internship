package com.anxstra.catalog.controllers;

import com.anxstra.catalog.dto.ProductDetailsDto;
import com.anxstra.catalog.dto.ProductDto;
import com.anxstra.catalog.dto.ProductListDto;
import com.anxstra.catalog.services.ProductService;
import com.anxstra.jwtfilterspringbootstarter.dto.BaseResponseEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.anxstra.catalog.constants.PathConstants.ID_PATH;
import static com.anxstra.catalog.constants.PathConstants.PRODUCTS_PATH;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping(PRODUCTS_PATH)
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<BaseResponseEntity<List<ProductListDto>>> getAllProducts(
            @RequestParam Long category,
            @RequestParam(required = false) @PositiveOrZero Integer pageNumber,
            @RequestParam(required = false) @Positive Integer pageSize) {

        List<ProductListDto> result = productService.findAll(category, pageNumber, pageSize);

        return ResponseEntity.status(HttpStatus.OK)
                             .body(BaseResponseEntity.ok(result));
    }

    @GetMapping(ID_PATH)
    public ResponseEntity<BaseResponseEntity<ProductDetailsDto>> getProductById(@PathVariable Long id) {

        ProductDetailsDto result = productService.findById(id);

        return ResponseEntity.status(HttpStatus.OK)
                             .body(BaseResponseEntity.ok(result));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BaseResponseEntity<Void>> addProduct(@RequestBody @Valid ProductDto productDto) {

        productService.insert(productDto);

        return ResponseEntity.status(HttpStatus.OK)
                             .body(BaseResponseEntity.ok());
    }

    @PutMapping(ID_PATH)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BaseResponseEntity<Void>> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductDto productDto) {

        productService.update(id, productDto);

        return ResponseEntity.status(HttpStatus.OK)
                             .body(BaseResponseEntity.ok());
    }

    @DeleteMapping(ID_PATH)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BaseResponseEntity<Void>> deleteProduct(@PathVariable Long id) {

        productService.delete(id);

        return ResponseEntity.status(HttpStatus.OK)
                             .body(BaseResponseEntity.ok());
    }
}
