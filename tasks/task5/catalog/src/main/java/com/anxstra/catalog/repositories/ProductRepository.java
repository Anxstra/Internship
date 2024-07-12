package com.anxstra.catalog.repositories;

import com.anxstra.catalog.dto.DescriptionDetailsDto;
import com.anxstra.catalog.dto.ProductDetailsDto;
import com.anxstra.catalog.dto.ProductListDto;
import com.anxstra.catalog.util.Page;
import com.anxstra.jooq.db.enums.CategoryType;
import com.anxstra.jooq.db.tables.records.ProductsRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Records;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.anxstra.jooq.db.Tables.CATEGORIES;
import static com.anxstra.jooq.db.Tables.PRODUCTS;
import static org.jooq.impl.DSL.row;
import static org.jooq.impl.DSL.select;
import static org.jooq.impl.DSL.val;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final DSLContext create;

    public int insert(ProductsRecord product) {

        int result = create.insertInto(PRODUCTS)
                           .columns(
                                   PRODUCTS.NAME,
                                   PRODUCTS.PRICE,
                                   PRODUCTS.QUANTITY,
                                   PRODUCTS.DELIVERY_PERIOD,
                                   PRODUCTS.IMAGE_URL,
                                   PRODUCTS.CATEGORY_ID,
                                   PRODUCTS.DESCRIPTION_ID,
                                   PRODUCTS.VERSION
                           )
                           .select(
                                   select(
                                           val(product.getName()),
                                           val(product.getPrice()),
                                           val(product.getQuantity()),
                                           val(product.getDeliveryPeriod()),
                                           val(product.getImageUrl()),
                                           val(product.getCategoryId()),
                                           val(product.getDescriptionId()),
                                           val(0L)
                                   )
                                   .whereExists(
                                           select(val(0))
                                           .from(CATEGORIES)
                                           .where(CATEGORIES.ID.eq(product.getCategoryId()))
                                           .and(CATEGORIES.TYPE.eq(CategoryType.SUBCATEGORY))
                                   )
                           )
                           .execute();

        return result;
    }

    public int update(Long id, ProductsRecord product) {

        int result = create.update(PRODUCTS)
                           .set(PRODUCTS.NAME, product.getName())
                           .set(PRODUCTS.PRICE, product.getPrice())
                           .set(PRODUCTS.QUANTITY, product.getQuantity())
                           .set(PRODUCTS.DELIVERY_PERIOD, product.getDeliveryPeriod())
                           .set(PRODUCTS.IMAGE_URL, product.getImageUrl())
                           .set(PRODUCTS.CATEGORY_ID, product.getCategoryId())
                           .set(PRODUCTS.DESCRIPTION_ID, product.getDescriptionId())
                           .set(PRODUCTS.VERSION, product.getVersion() + 1)
                           .where(PRODUCTS.ID.eq(id))
                           .and(PRODUCTS.VERSION.eq(product.getVersion()))
                           .andExists(
                                   select(val(0))
                                   .from(CATEGORIES)
                                   .where(CATEGORIES.ID.eq(product.getCategoryId()))
                                   .and(CATEGORIES.TYPE.eq(CategoryType.SUBCATEGORY))
                           )
                           .execute();

        return result;
    }

    public void delete(Long id) {

        create.deleteFrom(PRODUCTS)
              .where(PRODUCTS.ID.eq(id))
              .execute();
    }

    public List<ProductListDto> getAllByCategoryId(Long categoryId) {

        List<ProductListDto> result = create.select(
                                                    PRODUCTS.ID,
                                                    PRODUCTS.NAME,
                                                    PRODUCTS.PRICE,
                                                    PRODUCTS.QUANTITY,
                                                    PRODUCTS.DELIVERY_PERIOD,
                                                    PRODUCTS.IMAGE_URL,
                                                    PRODUCTS.DESCRIPTION_ID
                                            )
                                            .from(PRODUCTS)
                                            .where(PRODUCTS.CATEGORY_ID.eq(categoryId))
                                            .fetch()
                                            .map(Records.mapping(ProductListDto::new));

        return result;
    }

    public List<ProductListDto> getAllByCategoryIdAndPage(Long categoryId, Page page) {

        List<ProductListDto> result = create.select(
                                                    PRODUCTS.ID,
                                                    PRODUCTS.NAME,
                                                    PRODUCTS.PRICE,
                                                    PRODUCTS.QUANTITY,
                                                    PRODUCTS.DELIVERY_PERIOD,
                                                    PRODUCTS.IMAGE_URL,
                                                    PRODUCTS.DESCRIPTION_ID
                                            )
                                            .from(PRODUCTS)
                                            .where(PRODUCTS.CATEGORY_ID.eq(categoryId))
                                            .offset(page.getOffset())
                                            .limit(page.pageSize())
                                            .fetch()
                                            .map(Records.mapping(ProductListDto::new));

        return result;
    }

    public Optional<ProductDetailsDto> getById(Long id) {

        Optional<ProductDetailsDto> result = create.select(
                                                           PRODUCTS.ID,
                                                           PRODUCTS.NAME,
                                                           PRODUCTS.PRICE,
                                                           PRODUCTS.QUANTITY,
                                                           PRODUCTS.DELIVERY_PERIOD,
                                                           PRODUCTS.IMAGE_URL,
                                                           row(
                                                                   PRODUCTS.descriptions().TEXT,
                                                                   PRODUCTS.descriptions().WEIGHT,
                                                                   PRODUCTS.descriptions().LENGTH,
                                                                   PRODUCTS.descriptions().WIDTH,
                                                                   PRODUCTS.descriptions().DEPTH
                                                           ).mapping(DescriptionDetailsDto::new),
                                                           PRODUCTS.VERSION
                                                   )
                                                   .from(PRODUCTS)
                                                   .where(PRODUCTS.ID.eq(id))
                                                   .fetchOptional()
                                                   .map(Records.mapping(ProductDetailsDto::new));

        return result;
    }
}
