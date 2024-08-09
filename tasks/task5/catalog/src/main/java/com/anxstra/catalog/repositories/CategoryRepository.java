package com.anxstra.catalog.repositories;

import com.anxstra.catalog.dto.CategoryTreeDto;
import com.anxstra.jooq.db.enums.CategoryType;
import com.anxstra.jooq.db.tables.records.CategoriesRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.anxstra.catalog.util.Utils.getParentCategory;
import static com.anxstra.jooq.db.tables.Categories.CATEGORIES;
import static org.jooq.impl.DSL.exists;
import static org.jooq.impl.DSL.inline;
import static org.jooq.impl.DSL.select;
import static org.jooq.impl.DSL.val;

@Repository
@RequiredArgsConstructor
public class CategoryRepository {

    private final DSLContext create;

    private static final String ID_PROPERTY = "id";

    private static final String NAME_PROPERTY = "name";

    private static final String TYPE_PROPERTY = "type";

    private static final String PARENT_PROPERTY = "parent_id";

    public int insert(CategoriesRecord category) {

        int result = create.insertInto(CATEGORIES)
                           .columns(
                                   CATEGORIES.NAME,
                                   CATEGORIES.TYPE,
                                   CATEGORIES.PARENT_ID,
                                   CATEGORIES.VERSION
                           )
                           .select(
                                   select(
                                           val(category.getName()),
                                           val(category.getType()),
                                           val(category.getParentId()),
                                           val(0L)
                                   )
                                   .where(
                                           inline(category.getType()).eq(CategoryType.GROUP)
                                           .and(inline(category.getParentId()).isNull())
                                   )
                                   .orExists(
                                           select(val(0))
                                           .from(CATEGORIES)
                                           .where(CATEGORIES.ID.eq(category.getParentId()))
                                           .and(CATEGORIES.TYPE.eq(getParentCategory(category.getType())))
                                   )
                           )
                           .execute();

        return result;
    }

    public int update(Long id, CategoriesRecord category) {

        int result = create.update(CATEGORIES)
                           .set(CATEGORIES.NAME, category.getName())
                           .set(CATEGORIES.TYPE, category.getType())
                           .set(CATEGORIES.PARENT_ID, category.getParentId())
                           .set(CATEGORIES.VERSION, category.getVersion() + 1)
                           .where(CATEGORIES.ID.eq(id))
                           .and(CATEGORIES.VERSION.eq(category.getVersion()))
                           .and(
                                   exists(
                                           select(val(0))
                                           .from(CATEGORIES)
                                           .where(CATEGORIES.ID.eq(category.getParentId()))
                                           .and(CATEGORIES.TYPE.eq(getParentCategory(category.getType())))
                                   )
                                   .or(
                                           inline(category.getType()).eq(CategoryType.GROUP)
                                           .and(inline(category.getParentId()).isNull())
                                   )

                           )
                           .execute();

        return result;
    }

    public void delete(Long id) {

        create.deleteFrom(CATEGORIES)
              .where(CATEGORIES.ID.eq(id))
              .execute();
    }

    public List<CategoryTreeDto> getAll() {

        Result<Record> result = create.fetch("""
                        WITH RECURSIVE c AS (
                            SELECT id, name, type, parent_id
                            FROM categories
                            WHERE parent_id IS NULL
                            UNION ALL
                            SELECT categories.id, categories.name, categories.type, categories.parent_id
                            FROM categories
                            JOIN c ON categories.parent_id = c.id
                        )
                        SELECT * FROM c;
                """);

        return mapToCategoryTree(result);
    }

    private List<CategoryTreeDto> mapToCategoryTree(Result<Record> result) {

        Map<Long, CategoryTreeDto> categoryMap = new HashMap<>();

        for (Record row : result) {
            Long id = row.get(ID_PROPERTY, Long.class);
            String name = row.get(NAME_PROPERTY, String.class);
            String type = row.get(TYPE_PROPERTY, String.class);
            Long parentId = row.get(PARENT_PROPERTY, Long.class);

            categoryMap.put(id, new CategoryTreeDto(id, name, type, parentId, new ArrayList<>()));
        }

        List<CategoryTreeDto> rootCategories = new ArrayList<>();

        for (CategoryTreeDto category : categoryMap.values()) {
            if (category.parent() == null) {
                rootCategories.add(category);
            } else {
                CategoryTreeDto parentCategory = categoryMap.get(category.parent());
                if (parentCategory != null) {
                    parentCategory.children().add(category);
                }
            }
        }

        return rootCategories;
    }
}
