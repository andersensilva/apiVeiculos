# Projeto de Cadastro de Carros

Este projeto contém a estrutura de um banco de dados simples para cadastro de carros, modelos e marcas, com exemplos de inserção de dados. Além disso, a aplicação possui uma API documentada via Swagger para facilitar testes.

## Estrutura do Banco de Dados

O banco é composto por três tabelas principais:

### Tabela `marca`
- **Descrição:** Armazena as marcas dos veículos.
- **Campos:**
    - `id` (PK, auto increment) – identificador único da marca.
    - `nome_marca` – nome da marca do veículo.

### Tabela `modelo`
- **Descrição:** Contém os modelos de cada marca.
- **Campos:**
    - `id` (PK, auto increment) – identificador único do modelo.
    - `nome` – nome do modelo.
    - `valor_fipe` – valor médio de mercado (tabela FIPE).
    - `marca_id` (FK → `marca.id`) – marca à qual o modelo pertence.

### Tabela `carro`
- **Descrição:** Registra os carros cadastrados.
- **Campos:**
    - `id` (PK, auto increment) – identificador único do carro.
    - `timestamp_cadastro` – data e hora de cadastro (formato UNIX timestamp).
    - `ano` – ano do veículo.
    - `combustivel` – tipo de combustível.
    - `num_portas` – quantidade de portas.
    - `cor` – cor do veículo.
    - `modelo_id` (FK → `modelo.id`) – modelo do carro.

## Organização e Inserção de Dados

1. **Ordem de inserção:**  
   Para manter a integridade referencial, a sequência é:
    1. Inserir marcas.
    2. Inserir modelos vinculados às marcas.
    3. Inserir carros vinculados aos modelos.

2. **Exemplos de inserção:**
```sql
INSERT INTO marca (nome_marca) VALUES ('CHEVROLET');
INSERT INTO marca (nome_marca) VALUES ('VOLKSWAGEN');

INSERT INTO modelo (nome, valor_fipe, marca_id) VALUES ('ONIX PLUS', 50000, 1);
INSERT INTO modelo (nome, valor_fipe, marca_id) VALUES ('JETTA', 49000, 2);
INSERT INTO modelo (nome, valor_fipe, marca_id) VALUES ('HILLUX SW4', 47500, 2);

INSERT INTO carro (timestamp_cadastro, ano, combustivel, num_portas, cor, modelo_id)
VALUES (1696539488, 2015, 'FLEX', 4, 'BEGE', 1);
INSERT INTO carro (timestamp_cadastro, ano, combustivel, num_portas, cor, modelo_id)
VALUES (1696531234, 2014, 'FLEX', 4, 'AZUL', 2);
INSERT INTO carro (timestamp_cadastro, ano, combustivel, num_portas, cor, modelo_id)
VALUES (1696535432, 1993, 'DIESEL', 4, 'AZUL', 3);
```

## Decisões do Projeto

- **Chaves primárias auto incrementáveis:** simplificam inserções.
- **Relacionamentos entre tabelas:** `modelo.marca_id → marca.id` e `carro.modelo_id → modelo.id`. Mantém integridade referencial e facilita consultas com `JOIN`.
- **Uso de timestamp:** simplifica o registro da data/hora de cadastro.
- **Valores FIPE e exemplos reais:** facilita testes e validação de dados.
- **Organização do SQL:** primeiro marcas, depois modelos e por último carros, evitando erros de chave estrangeira.

## Como Executar e Testar a API

1. Acesse a documentação da API via Swagger: [/swagger-ui.html](/swagger-ui.html)
2. Para testes, utilize o usuário de acesso:
    - **Usuário:** `admin`
    - **Senha:** `123456`
3. A partir do Swagger, é possível testar todos os endpoints de `marca`, `modelo` e `carro`, incluindo consultas, criação, atualização e exclusão.
