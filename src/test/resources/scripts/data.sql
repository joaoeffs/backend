DELETE FROM ITEM_PEDIDO;
DELETE FROM PEDIDO;
DELETE FROM PRODUTO_SERVICO;

INSERT INTO produto_servico
       (id,                                     descricao,   tipo,      situacao,  preco)
VALUES ('f1ba310b-1f35-4128-974c-bb8146b85f3d', 'Produto A', 'PRODUTO', 'ATIVO',   10.00),
       ('3409ab1d-d090-4fcc-9bf2-d3415dc09eeb', 'Produto B', 'PRODUTO', 'INATIVO', 20.00),
       ('54818486-b87b-4adb-99a7-55ceb558fab9', 'SERVICO A', 'SERVICO', 'ATIVO',   100.00),
       ('21badd45-e2d3-405b-8ba8-176b4a9dc1bb', 'SERVICO B', 'SERVICO', 'ATIVO',   250.00);

INSERT INTO pedido
       (id,                                     numero_pedido, situacao,    cliente,    valor_total, percentual_desconto, valor_desconto)
VALUES ('48337951-18f1-4cc3-9178-3c3bcd522e6b', 3,             'FECHADO',   'Edu',      0.00,        0.00,                0.00),
       ('446961a6-8980-41df-88e1-2c4d13f28293', 2,             'PENDENTE',  'João',     0.0,         0.00,                0.00),
       ('8c5390a2-315e-44be-80d8-d31aa2cc8f77', 1,             'CANCELADO', 'Joana',    0.0,         0.00,                0.00);

INSERT INTO item_pedido
        (id,                                    quantidade, valor_unitario, pedido_id,                              produto_servico_id,                     deleted)
VALUES ('5fb59977-d85c-4c24-940a-c6ea925ba798', 1,          50.00,          '446961a6-8980-41df-88e1-2c4d13f28293', 'f1ba310b-1f35-4128-974c-bb8146b85f3d', false),
       ('0cc3c811-26c7-4efa-bdb9-a505e7a2dfaa', 2,          250.00,         '446961a6-8980-41df-88e1-2c4d13f28293', '3409ab1d-d090-4fcc-9bf2-d3415dc09eeb', true),
       ('1f5d9e0b-df59-4a0e-8f91-e1a274eb3731', 1,          100.00,         '446961a6-8980-41df-88e1-2c4d13f28293', '54818486-b87b-4adb-99a7-55ceb558fab9', false),
       ('daf4b972-3a93-456a-89d4-7111219792d2', 2,          10.00,          '446961a6-8980-41df-88e1-2c4d13f28293', '21badd45-e2d3-405b-8ba8-176b4a9dc1bb', false),
       ('36ad1ae4-ae7e-4f54-8298-9983c3480726', 7,          12.00,          '8c5390a2-315e-44be-80d8-d31aa2cc8f77', '21badd45-e2d3-405b-8ba8-176b4a9dc1bb', false),
       ('49e8ac2e-d918-48e6-90bf-2f3ad3bfa4aa', 5,          15.00,          '8c5390a2-315e-44be-80d8-d31aa2cc8f77', 'f1ba310b-1f35-4128-974c-bb8146b85f3d', false);