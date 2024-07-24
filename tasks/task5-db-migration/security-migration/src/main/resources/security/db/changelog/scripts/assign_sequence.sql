ALTER TABLE users ALTER COLUMN id SET DEFAULT nextval('users_id_seq');

ALTER TABLE roles ALTER COLUMN id SET DEFAULT nextval('roles_id_seq');

ALTER TABLE tokens ALTER COLUMN id SET DEFAULT nextval('tokens_id_seq');