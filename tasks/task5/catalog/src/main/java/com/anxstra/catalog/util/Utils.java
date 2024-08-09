package com.anxstra.catalog.util;

import com.anxstra.jooq.db.enums.CategoryType;

public class Utils {

    private Utils() {

    }

    public static CategoryType getParentCategory(CategoryType childType) {

        if (childType == CategoryType.SUBCATEGORY) {
            return CategoryType.CATEGORY;
        } else if (childType == CategoryType.CATEGORY) {
            return CategoryType.GROUP;
        } else {
            return null;
        }
    }
}
