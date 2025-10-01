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

## Tecnologias Utilizadas

O projeto foi desenvolvido com as seguintes tecnologias:

- **Java 17** → linguagem principal do projeto.
- **Spring Boot 3.5.6** → framework para inicialização rápida e configuração simplificada da aplicação.
- **Spring Web (spring-boot-starter-web)** → criação dos endpoints REST.
- **Spring Data JPA** → abstração para acesso e persistência de dados.
- **Spring Security** → gerenciamento de autenticação e autorização, integrado com JWT.
- **JWT (Java JSON Web Token - io.jsonwebtoken)** → usado para autenticação stateless da API.
- **H2 Database** → banco de dados em memória para testes locais, dispensando configuração externa.
- **Swagger / OpenAPI (springdoc-openapi)** → documentação interativa da API acessível em `/swagger-ui.html`.
- **JUnit & Spring Security Test** → framework de testes unitários e de integração.

Essas escolhas permitem que o sistema seja **modular, escalável, seguro e fácil de testar**.


## Decisões do Projeto

- **Chaves primárias auto incrementáveis:** simplificam inserções.
- **Relacionamentos entre tabelas:** `modelo.marca_id → marca.id` e `carro.modelo_id → modelo.id`. Mantém integridade referencial e facilita consultas com `JOIN`.
- **Uso de timestamp:** simplifica o registro da data/hora de cadastro.
- **Valores FIPE e exemplos reais:** facilita testes e validação de dados.
- **Organização do SQL:** primeiro marcas, depois modelos e por último carros, evitando erros de chave estrangeira.

## Segurança da API

A aplicação utiliza **Spring Security** com autenticação baseada em **JWT (JSON Web Token)**.
- O usuário faz login no endpoint `/auth/login`, recebendo um token JWT.
- Esse token deve ser enviado no **header** das requisições subsequentes:
   Authorization: Bearer <seu_token>
- Endpoints de autenticação (`/auth/**`), documentação (`/swagger-ui/**`, `/v3/api-docs/**`) e console H2 estão liberados sem token.
- Todos os demais endpoints exigem autenticação JWT.
- O gerenciamento de sessão é **stateless**, garantindo escalabilidade e segurança.

### Usuário de teste
- **Usuário:** `admin`
- **Senha:** `123456`

---

## Configuração de CORS

Foi configurado o **CORS** para permitir que um frontend (ex: React, Next.js) consuma a API sem problemas de origem cruzada.

- A origem permitida é definida na propriedade:

```properties
app.cors.allowed-origin=http://localhost:3000
```

## Como Executar e Testar a API

1. Acesse a documentação da API via Swagger: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
2. Para testes, utilize o usuário de acesso:
    - **Usuário:** `admin`
    - **Senha:** `123456`
3. A partir do Swagger, é possível testar todos os endpoints de `marca`, `modelo` e `carro`, incluindo consultas, criação, atualização e exclusão.
