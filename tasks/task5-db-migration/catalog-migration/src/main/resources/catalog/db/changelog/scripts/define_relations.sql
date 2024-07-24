ALTER TABLE categories
    ADD CONSTRAINT fk_categories_categories FOREIGN KEY (parent_id) REFERENCES categories(id);

ALTER TABLE products
    ADD CONSTRAINT fk_products_categories FOREIGN KEY (category_id) REFERENCES categories(id);

ALTER TABLE products
    ADD CONSTRAINT fk_products_descriptions FOREIGN KEY (description_id) REFERENCES descriptions(id);
