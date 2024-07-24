ALTER TABLE categories ALTER COLUMN id SET DEFAULT nextval('categories_id_seq');

ALTER TABLE descriptions ALTER COLUMN id SET DEFAULT nextval('descriptions_id_seq');

ALTER TABLE products ALTER COLUMN id SET DEFAULT nextval('products_id_seq');