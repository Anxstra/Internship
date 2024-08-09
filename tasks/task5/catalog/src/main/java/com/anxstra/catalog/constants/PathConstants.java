package com.anxstra.catalog.constants;

public class PathConstants {

    public static final String SWAGGER_UI_PATH = "/swagger-ui/**";

    public static final String SWAGGER_DOCS_PATH = "/v3/api-docs/**";

    public static final String PRODUCTS_PATH = "/products";

    public static final String ID_PATH = "/{id}";

    public static final String PRODUCT_PATH = PRODUCTS_PATH + ID_PATH;

    public static final String CATEGORIES_PATH = "/categories";

    public static final String CATEGORY_PATH = CATEGORIES_PATH + ID_PATH;

    public static final String DESCRIPTIONS_PATH = "/descriptions";

    public static final String DESCRIPTION_PATH = DESCRIPTIONS_PATH + ID_PATH;

    private PathConstants() {
    }
}
