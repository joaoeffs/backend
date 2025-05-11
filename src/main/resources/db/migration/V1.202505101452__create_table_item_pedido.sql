CREATE TABLE IF NOT EXISTS item_pedido (

    id                     UUID                 NOT NULL,

    quantidade             numeric(10)          NOT NULL,
    valor_unitario         NUMERIC(10, 2)       NOT NULL,

    pedido_id              UUID                 NOT NULL,
    produto_servico_id     UUID                 NOT NULL,
    deleted                BOOLEAN              NOT NULL DEFAULT FALSE,

    CONSTRAINT item_pedido_pk PRIMARY KEY (id)
);

ALTER TABLE item_pedido ADD CONSTRAINT item_pedido_pedido_fk FOREIGN KEY (pedido_id) REFERENCES pedido(id);
ALTER TABLE item_pedido ADD CONSTRAINT item_pedido_produto_servico_fk FOREIGN KEY (produto_servico_id) REFERENCES produto_servico(id);
