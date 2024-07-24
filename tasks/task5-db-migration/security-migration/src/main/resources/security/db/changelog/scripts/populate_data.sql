INSERT INTO users(id, email, password, status, version)
VALUES (101, 'kozichev2020@gmail.com', '$2a$10$bQGMrqmYNP2gLdV.SJDq8OQh4HmRdg7ydBAXquLNgrRUSf/lE7mwK', 'ACTIVE', 0),
       (102, 'kozichev2004@gmail.com', '$2a$10$jZQVBUWSanOLM4vaWL/jMOYmHiLPy5ly2VrFGmT/ZyLUm/sgNCe22', 'ACTIVE', 0);

ALTER SEQUENCE users_id_seq RESTART WITH 127;

INSERT INTO roles(id, name, version)
VALUES (101, 'ADMIN', 0),
       (102, 'USER', 0);

ALTER SEQUENCE roles_id_seq RESTART WITH 103;

INSERT INTO users_roles(user_id, role_id)
VALUES (101, 101),
       (102, 102);
