package com.anxstra.catalog.services;

import com.anxstra.catalog.dto.DescriptionDto;
import com.anxstra.catalog.mappers.DescriptionMapper;
import com.anxstra.catalog.repositories.DescriptionRepository;
import com.anxstra.jooq.db.tables.records.DescriptionsRecord;
import com.anxstra.jwtfilterspringbootstarter.exception.ApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.anxstra.catalog.constants.MessageConstants.DESCRIPTION_NOT_FOUND;
import static com.anxstra.catalog.constants.MessageConstants.DESCRIPTION_UPDATE;
import static com.anxstra.catalog.constants.MessageConstants.ENTITY_PERSISTED;
import static com.anxstra.catalog.constants.MessageConstants.ENTITY_TRANSIENT;
import static com.anxstra.jwtfilterspringbootstarter.exception.AppErrors.badRequestOf;

@Service
@RequiredArgsConstructor
public class DescriptionService {

    private final DescriptionRepository descriptionRepository;

    private final DescriptionMapper descriptionMapper;

    public void insert(DescriptionDto descriptionDto) {

        DescriptionsRecord description = descriptionMapper.descriptionDtoToDescriptionsRecord(descriptionDto);

        if (Objects.isNull(description.getId()) && Objects.isNull(description.getVersion())) {
            descriptionRepository.insert(description);
        } else {
            throw new ApplicationException(badRequestOf(ENTITY_PERSISTED));
        }
    }

    public DescriptionDto findById(Long id) {

        return descriptionRepository.getById(id)
                                    .orElseThrow(() -> new ApplicationException(badRequestOf(DESCRIPTION_NOT_FOUND)));
    }

    public void update(Long id, DescriptionDto descriptionDto) {

        DescriptionsRecord description = descriptionMapper.descriptionDtoToDescriptionsRecord(descriptionDto);

        if (Objects.nonNull(description.getVersion())) {
            int result = descriptionRepository.update(id, description);
            if (result == 0) {
                throw new ApplicationException(badRequestOf(DESCRIPTION_UPDATE));
            }
        } else {
            throw new ApplicationException(badRequestOf(ENTITY_TRANSIENT));
        }
    }

    public void delete(Long id) {

        descriptionRepository.delete(id);
    }
}
