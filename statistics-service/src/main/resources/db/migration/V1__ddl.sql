CREATE TABLE IF NOT EXISTS users
(
    id                            VARCHAR(36) NOT NULL PRIMARY KEY,
    date_time                     TIMESTAMP   NOT NULL,
    bot_id                        BIGINT      NOT NULL,
    count_all                     BIGINT      NOT NULL,
    count_blocked                 BIGINT      NOT NULL,
    count_today_online            BIGINT      NOT NULL,
    count_today_registered        BIGINT      NOT NULL,
    count_with_no_payments        BIGINT      NOT NULL,
    count_with_more_one_payments  BIGINT      NOT NULL,
    count_with_more_five_payments BIGINT      NOT NULL,
    count_with_more_ten_payments  BIGINT      NOT NULL
);

CREATE TABLE IF NOT EXISTS pays
(
    id               VARCHAR(36) NOT NULL PRIMARY KEY,
    date_time        TIMESTAMP   NOT NULL,
    bot_id           BIGINT      NOT NULL,
    service_id       BIGINT      NOT NULL,
    tariff_id        BIGINT      NOT NULL,
    count_all        BIGINT      NOT NULL,
    all_income   BIGINT      NOT NULL,
    count_today      BIGINT      NOT NULL,
    today_income BIGINT      NOT NULL
);

CREATE TABLE IF NOT EXISTS service_subscriptions
(
    id           VARCHAR(36) NOT NULL PRIMARY KEY,
    date_time    TIMESTAMP   NOT NULL,
    bot_id       BIGINT      NOT NULL,
    service_id   BIGINT      NOT NULL,
    count_all    BIGINT      NOT NULL,
    count_active BIGINT      NOT NULL
);