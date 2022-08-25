CREATE TABLE item
(
    item_id     BIGINT       NOT NULL PRIMARY KEY auto_increment,
    name        VARCHAR(200) NOT NULL,
    description VARCHAR(200) NOT NULL
);

CREATE TABLE roles
(
    role_id bigint  NOT NULL PRIMARY KEY,
    name    VARCHAR NOT NULL
);

INSERT INTO roles (role_id, name)
values (1, 'ADMIN'),
       (2, 'USER');

CREATE TABLE users
(
    user_id    BIGINT       NOT NULL PRIMARY KEY auto_increment,
    first_name VARCHAR(200) NOT NULL,
    last_name  VARCHAR(200) NOT NULL,
    login      VARCHAR(200) NOT NULL,
    password   VARCHAR(200) NOT NULL,
    role_id    BIGINT       NOT NULL,
    FOREIGN KEY (role_id) REFERENCES roles (role_id)
);

SELECT user_id, first_name, last_name, login, password, role_id, r.name FROM users
    LEFT JOIN roles r on r.role_id = users.role_id;