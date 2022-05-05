CREATE TABLE address
(
    id     BIGINT NOT NULL AUTO_INCREMENT,
    city   VARCHAR(255) NULL,
    street VARCHAR(255) NULL,
    number INT NULL,
    CONSTRAINT pk_address PRIMARY KEY (id)
);

CREATE TABLE app_user
(
    id   BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NULL,
    age  INT NULL,
    CONSTRAINT pk_app_user PRIMARY KEY (id)
);

CREATE TABLE event
(
    id         BIGINT       NOT NULL AUTO_INCREMENT,
    name       VARCHAR(255) NOT NULL,
    address_id BIGINT NULL,
    start_date TIMESTAMP NULL,
    end_date   TIMESTAMP NULL,
    CONSTRAINT pk_event PRIMARY KEY (id)
);

CREATE TABLE event_app_users
(
    app_users_id BIGINT NOT NULL,
    event_id     BIGINT NOT NULL,
    CONSTRAINT pk_event_app_users PRIMARY KEY (app_users_id, event_id)
);

ALTER TABLE event
    ADD CONSTRAINT FK_EVENT_ON_ADDRESS FOREIGN KEY (address_id) REFERENCES address (id);

ALTER TABLE event_app_users
    ADD CONSTRAINT fk_eveappuse_on_app_user FOREIGN KEY (app_users_id) REFERENCES app_user (id);

ALTER TABLE event_app_users
    ADD CONSTRAINT fk_eveappuse_on_event FOREIGN KEY (event_id) REFERENCES event (id);