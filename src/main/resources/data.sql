INSERT INTO marca (id, nome_marca) VALUES (1, 'CHEVROLET');
INSERT INTO marca (id, nome_marca) VALUES (2, 'VOLKSWAGEN');

INSERT INTO modelo (id, nome, valor_fipe, marca_id) VALUES (12, 'ONIX PLUS', 50000, 1);
INSERT INTO modelo (id, nome, valor_fipe, marca_id) VALUES (14, 'JETTA', 49000, 2);
INSERT INTO modelo (id, nome, valor_fipe, marca_id) VALUES (79, 'HILLUX SW4', 47500, 2);

INSERT INTO carro (id, timestamp_cadastro, ano, combustivel, num_portas, cor, modelo_id)
VALUES (1, 1696539488, 2015, 'FLEX', 4, 'BEGE', 12);
INSERT INTO carro (id, timestamp_cadastro, ano, combustivel, num_portas, cor, modelo_id)
VALUES (2, 1696531234, 2014, 'FLEX', 4, 'AZUL', 14);
INSERT INTO carro (id, timestamp_cadastro, ano, combustivel, num_portas, cor, modelo_id)
VALUES (3, 1696535432, 1993, 'DIESEL', 4, 'AZUL', 79);
