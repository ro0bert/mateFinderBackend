ALTER TABLE app_user
    ADD email VARCHAR(255) NULL;

ALTER TABLE app_user
    ADD first_name VARCHAR(255) NULL;

ALTER TABLE app_user
    ADD last_name VARCHAR(255) NULL;

ALTER TABLE app_user
    ADD phone_number VARCHAR(255) NULL;

ALTER TABLE app_user
    ADD user_name VARCHAR(255) NULL;

ALTER TABLE app_user
    DROP COLUMN name;