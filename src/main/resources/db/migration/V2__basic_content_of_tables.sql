INSERT INTO role (id, name)
VALUES ('a9731fb3-36bf-4ae2-9e70-9277fae845f1', 'ADMIN');

INSERT INTO "user" (id, email, password, first_name, last_name)
VALUES ('a206b559-2f75-4102-9b19-7b4710e12892', 'admin@mail.com', '{bcrypt}$2a$10$oz4scNUI1l7O.sqB0sBonu/ovNEhi2ijYjhaflG2u7RQFDOwVjA4q', 'San',   'Sanych');

INSERT INTO user_role (user_id, role_id)
VALUES ('a206b559-2f75-4102-9b19-7b4710e12892', 'a9731fb3-36bf-4ae2-9e70-9277fae845f1');