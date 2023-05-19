CREATE TABLE IF NOT EXISTS orders
(
    id         VARCHAR(36) NOT NULL PRIMARY KEY,
    bot_id     BIGINT      NOT NULL,
    service_id BIGINT,
    tariff_id  BIGINT,
    days       INT         NOT NULL,
    type       VARCHAR(36) NOT NULL,
    value      VARCHAR(36) NOT NULL,
    created_at TIMESTAMP   NOT NULL,
    start_at   TIMESTAMP,
    end_at     TIMESTAMP,
    status     INT         NOT NULL,
    result     JSONB
);