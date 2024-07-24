CREATE TYPE category_type AS ENUM ('GROUP', 'CATEGORY', 'SUBCATEGORY');

CREATE TABLE IF NOT EXISTS categories (
    id BIGINT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    type category_type NOT NULL,
    parent_id BIGINT,
    version BIGINT NOT NULL
);

CREATE TABLE descriptions (
    id BIGINT PRIMARY KEY,
    text VARCHAR(255) NOT NULL,
    weight INT NOT NULL,
    length INT NOT NULL,
    width INT NOT NULL,
    depth INT NOT NULL,
    version BIGINT NOT NULL
);

CREATE TABLE products (
    id BIGINT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    price NUMERIC(15,2) NOT NULL,
    quantity INT NOT NULL,
    delivery_period INT NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    category_id BIGINT NOT NULL,
    description_id BIGINT NOT NULL,
    version BIGINT NOT NULL
);