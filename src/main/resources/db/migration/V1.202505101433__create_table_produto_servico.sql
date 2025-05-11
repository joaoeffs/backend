CREATE TABLE IF NOT EXISTS produto_servico (

    id                  UUID                NOT NULL,

    descricao           VARCHAR(64)         NOT NULL,
    tipo                VARCHAR(32)         NOT NULL,
    preco               NUMERIC(10, 2)      NOT NULL,
    situacao            VARCHAR(16)         NOT NULL,

    row_version         SMALLINT            NOT NULL DEFAULT 0,
    row_created_at      TIMESTAMP           NOT NULL DEFAULT NOW(),
    row_updated_at      TIMESTAMP           NOT NULL DEFAULT NOW(),

    CONSTRAINT produto_servico_pk PRIMARY KEY (id)
);
