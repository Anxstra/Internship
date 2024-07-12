package com.anxstra.catalog.controllers;

import com.anxstra.catalog.dto.DescriptionDto;
import com.anxstra.catalog.services.DescriptionService;
import com.anxstra.jwtfilterspringbootstarter.dto.BaseResponseEntity;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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

import static com.anxstra.catalog.constants.PathConstants.DESCRIPTIONS_PATH;
import static com.anxstra.catalog.constants.PathConstants.ID_PATH;

@RestController
@AllArgsConstructor
@RequestMapping(DESCRIPTIONS_PATH)
public class DescriptionController {

    private final DescriptionService descriptionService;

    @GetMapping(ID_PATH)
    public ResponseEntity<BaseResponseEntity<DescriptionDto>> getDescriptionById(@PathVariable Long id) {

        DescriptionDto result = descriptionService.findById(id);

        return ResponseEntity.status(HttpStatus.OK)
                             .body(BaseResponseEntity.ok(result));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BaseResponseEntity<Void>> addDescription(@RequestBody @Valid DescriptionDto descriptionDto) {

        descriptionService.insert(descriptionDto);

        return ResponseEntity.status(HttpStatus.OK)
                             .body(BaseResponseEntity.ok());
    }

    @PutMapping(ID_PATH)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BaseResponseEntity<Void>> updateDescription(@PathVariable Long id, @RequestBody @Valid DescriptionDto descriptionDto) {

        descriptionService.update(id, descriptionDto);

        return ResponseEntity.status(HttpStatus.OK)
                             .body(BaseResponseEntity.ok());
    }

    @DeleteMapping(ID_PATH)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BaseResponseEntity<Void>> deleteDescription(@PathVariable Long id) {

        descriptionService.delete(id);

        return ResponseEntity.status(HttpStatus.OK)
                             .body(BaseResponseEntity.ok());
    }
}
