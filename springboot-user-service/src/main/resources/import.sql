INSERT INTO USER (USERNAME, PASSWORD, ENABLED, NAME, LASTNAME, EMAIL) values ('mario', '$2a$10$PXUf0XPiKv2Ln58sayAmGeC6El0vOyA9iRm4bszVdgxy2jQcLbCTK', true, 'Mario', 'M', 'mario@email.com');
INSERT INTO USER (USERNAME, PASSWORD, ENABLED, NAME, LASTNAME, EMAIL) values ('walden', '$2a$10$.ELGDSN21NsiUiHgRE/qiedzK8f8F4UgkRbB0wEoZvtwgW4L7urL2', true, 'Walden', 'M', 'walden@email.com');

INSERT INTO ROLE (NAME) VALUES ('ROLE_USER');
INSERT INTO ROLE (NAME) VALUES ('ROLE_ADMIN');

INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES (1, 1);
INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES (2, 2);
INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES (2, 1);