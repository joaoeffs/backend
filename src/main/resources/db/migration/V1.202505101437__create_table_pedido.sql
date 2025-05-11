CREATE TABLE IF NOT EXISTS pedido (
    id                      UUID                NOT NULL,

    numero_pedido           INTEGER             NOT NULL,
    situacao                VARCHAR(32)         NOT NULL,
    cliente                 VARCHAR(64)         NOT NULL,
    valor_total             NUMERIC(10, 2),
    valor_desconto          NUMERIC(10, 2),
    percentual_desconto     NUMERIC(5, 2),

    row_version             SMALLINT            NOT NULL DEFAULT 0,
    row_created_at          TIMESTAMP           NOT NULL DEFAULT NOW(),
    row_updated_at          TIMESTAMP           NOT NULL DEFAULT NOW(),

    CONSTRAINT pedido_pk PRIMARY KEY (id)
);
