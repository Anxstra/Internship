package com.anxstra.catalog.repositories;

import com.anxstra.catalog.dto.DescriptionDto;
import com.anxstra.jooq.db.tables.records.DescriptionsRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Records;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.anxstra.jooq.db.Tables.DESCRIPTIONS;

@Repository
@RequiredArgsConstructor
public class DescriptionRepository {

    private final DSLContext create;

    public void insert(DescriptionsRecord description) {

        create.insertInto(DESCRIPTIONS,
                      DESCRIPTIONS.TEXT,
                      DESCRIPTIONS.WEIGHT,
                      DESCRIPTIONS.LENGTH,
                      DESCRIPTIONS.WIDTH,
                      DESCRIPTIONS.DEPTH,
                      DESCRIPTIONS.VERSION
              )
              .values(
                      description.getText(),
                      description.getWeight(),
                      description.getLength(),
                      description.getWidth(),
                      description.getDepth(),
                      0L
              )
              .execute();
    }

    public Optional<DescriptionDto> getById(Long id) {

        Optional<DescriptionDto> result = create.select(
                                                        DESCRIPTIONS.TEXT,
                                                        DESCRIPTIONS.WEIGHT,
                                                        DESCRIPTIONS.LENGTH,
                                                        DESCRIPTIONS.WIDTH,
                                                        DESCRIPTIONS.DEPTH,
                                                        DESCRIPTIONS.VERSION
                                                )
                                                .from(DESCRIPTIONS)
                                                .where(DESCRIPTIONS.ID.eq(id))
                                                .fetchOptional().map(Records.mapping(DescriptionDto::new));

        return result;
    }

    public int update(Long id, DescriptionsRecord description) {

        int result = create.update(DESCRIPTIONS)
                           .set(DESCRIPTIONS.TEXT, description.getText())
                           .set(DESCRIPTIONS.WEIGHT, description.getWeight())
                           .set(DESCRIPTIONS.LENGTH, description.getLength())
                           .set(DESCRIPTIONS.WIDTH, description.getWidth())
                           .set(DESCRIPTIONS.DEPTH, description.getDepth())
                           .set(DESCRIPTIONS.VERSION, description.getVersion() + 1)
                           .where(DESCRIPTIONS.ID.eq(id))
                           .and(DESCRIPTIONS.VERSION.eq(description.getVersion()))
                           .execute();

        return result;
    }

    public void delete(Long id) {

        create.delete(DESCRIPTIONS)
              .where(DESCRIPTIONS.ID.eq(id))
              .execute();
    }
}
