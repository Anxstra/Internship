ALTER TABLE users_roles
    ADD CONSTRAINT fk_users_roles_users FOREIGN KEY (user_id) REFERENCES users(id);

ALTER TABLE users_roles
    ADD CONSTRAINT fk_users_roles_roles FOREIGN KEY (role_id) REFERENCES roles(id);

ALTER TABLE tokens
    ADD CONSTRAINT fk_tokens_users FOREIGN KEY (user_id) REFERENCES users(id);
