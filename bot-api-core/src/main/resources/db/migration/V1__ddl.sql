CREATE TABLE IF NOT EXISTS bots
(
    id           BIGINT       NOT NULL PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    company_id           BIGINT       NOT NULL,
    api_key      VARCHAR(40)  NOT NULL,
    webhook_path VARCHAR(255) NOT NULL,
    active       BOOLEAN      NOT NULL,
    CONSTRAINT bot_unique UNIQUE (id, api_key)
);

CREATE TABLE IF NOT EXISTS roles
(
    id     BIGINT       NOT NULL PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    bot_id BIGINT       NOT NULL REFERENCES bots (id) ON DELETE CASCADE,
    name   VARCHAR(100) NOT NULL,
    CONSTRAINT role_unique UNIQUE (bot_id, name)
);

CREATE TABLE IF NOT EXISTS users
(
    id                    BIGINT       NOT NULL PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    bot_id                BIGINT       NOT NULL REFERENCES bots (id) ON DELETE CASCADE,
    platform_id           VARCHAR(50)  NOT NULL,
    username              VARCHAR(255),
    first_name            VARCHAR(255),
    last_name             VARCHAR(255),
    blocked               BOOLEAN      NOT NULL,
    state                 VARCHAR(100) NOT NULL,
    registered_at         TIMESTAMP    NOT NULL,
    last_activity_at      TIMESTAMP,
    additional_attributes JSONB,
    CONSTRAINT user_unique UNIQUE (bot_id, platform_id)
);

CREATE TABLE IF NOT EXISTS users_roles
(
    id      BIGINT NOT NULL PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    user_id BIGINT NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    role_id BIGINT NOT NULL REFERENCES roles (id) ON DELETE CASCADE,
    CONSTRAINT user_role_unique UNIQUE (user_id, role_id)
);

CREATE TABLE IF NOT EXISTS services
(
    id     BIGINT       NOT NULL PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    bot_id BIGINT       NOT NULL REFERENCES bots (id) ON DELETE CASCADE,
    type   INT          NOT NULL, -- (1-date, 2-counter)
    name   VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS tariffs
(
    id         BIGINT NOT NULL PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    service_id BIGINT NOT NULL REFERENCES services (id) ON DELETE CASCADE,
    time_unit  INT    NOT NULL, -- (1 - seconds, 2- minutes, 3- hour, 4- day, 5- month, 6- year)
    value      BIGINT NOT NULL,
    price      BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS subscriptions
(
    id                        BIGINT  NOT NULL PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    service_id                BIGINT  NOT NULL REFERENCES services (id) ON DELETE CASCADE,
    user_id                   BIGINT  NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    active                    BOOLEAN NOT NULL,
    need_notify     BOOLEAN,
    trial_period_start_date   TIMESTAMP,
    trial_period_end_date     TIMESTAMP,
    subscribe_after_trial     BOOLEAN,
    trial_count               BIGINT,
    available_count           BIGINT,
    end_date   TIMESTAMP,
    notified     BOOLEAN
);


CREATE TABLE IF NOT EXISTS pays
(
    id         BIGINT    NOT NULL PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    user_id    BIGINT    NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    service_id BIGINT    NOT NULL REFERENCES services (id) ON DELETE CASCADE,
    tariff_id  BIGINT    NOT NULL REFERENCES tariffs (id) ON DELETE CASCADE,
    pay_date   TIMESTAMP NOT NULL,
    payload    VARCHAR(150),
    amount     BIGINT
);