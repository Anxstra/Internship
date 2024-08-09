package com.anxstra.catalog.constants;

public class MessageConstants {

    public static final String ENTITY_PERSISTED = "Entity persisted: id and version must be null for new entity";

    public static final String ENTITY_TRANSIENT = "Entity transient: version must not be null for existing entity";

    public static final String PRODUCT_NOT_FOUND = "Product not found";

    public static final String PRODUCT_UPDATE = "Product update failed: optimistic lock violation or " +
                                                "category must exist and it's type be SUBCATEGORY";

    public static final String PRODUCT_INSERT = "Product insert failed: " +
                                                "category must exist and it's type be SUBCATEGORY";

    public static final String DESCRIPTION_NOT_FOUND = "Description not found";

    public static final String DESCRIPTION_UPDATE = "Description update failed: optimistic lock violation";

    public static final String CATEGORY_INSERT = "Category insert failed: child/parent type constraint violation";

    public static final String CATEGORY_UPDATE = "category update failed: optimistic lock violation or " +
                                                 "child/parent type constraint violation";

    private MessageConstants() {

    }
}
