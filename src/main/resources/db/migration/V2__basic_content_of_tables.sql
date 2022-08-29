INSERT INTO role (id, name)
VALUES ('a9731fb3-36bf-4ae2-9e70-9277fae845f1', 'ADMIN'),
       ('4aa6d56e-dc50-4f85-9ab7-da524a271276', 'USER');

INSERT INTO "user" (id, email, password, first_name, last_name)
VALUES ('a206b559-2f75-4102-9b19-7b4710e12892', 'admin@mail.com', 'admin', 'San',   'Sanych'),
       ('ea861d1f-300c-49a3-b73e-9c7627948c52', 'user@mail.com',  'user',  'Vasyl', 'Medusa');

INSERT INTO user_role (user_id, role_id)
VALUES ('a206b559-2f75-4102-9b19-7b4710e12892', 'a9731fb3-36bf-4ae2-9e70-9277fae845f1'),
       ('ea861d1f-300c-49a3-b73e-9c7627948c52', '4aa6d56e-dc50-4f85-9ab7-da524a271276');