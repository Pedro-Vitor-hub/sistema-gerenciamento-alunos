# Sistema de Gerenciamento de Alunos

Projeto desenvolvido em Java para praticar desenvolvimento Backend e integração com banco de dados.

O sistema permite cadastrar, consultar, atualizar e remover alunos, além de realizar algumas operações como cálculo da média e geração de relatório.

## Funcionalidades

- Cadastrar aluno
- Buscar aluno por nome
- Listar alunos
- Atualizar aluno
- Remover aluno
- Calcular média da turma
- Listar aprovados e reprovados
- Gerar relatório

## Tecnologias

- Java
- JDBC
- PostgreSQL
- Maven
- Docker
- Git/GitHub

## Como executar o projeto

### 1. Pré-requisitos

É necessário ter instalado:

- Java
- IntelliJ IDEA
- Docker
- Git

### 2. Clonar o projeto

```bash
git clone https://github.com/Pedro-Vitor-hub/sistema-gerenciamento-alunos.git

### 3. Iniciar o PostgreSQL com Docker

O projeto possui um arquivo `docker-compose.yaml`.

Execute:

```bash
docker compose up -d
```

Para verificar se o container está rodando:

```bash
docker ps
```

O PostgreSQL será executado na porta `5432`.

### 4. Criar a tabela

Acesse o PostgreSQL:

```bash
docker exec -it postgres-alunos psql -U postgres -d gerenciamento_alunos
```

Crie a tabela:

```sql
CREATE TABLE alunos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    idade INTEGER NOT NULL,
    nota DECIMAL(4,2) NOT NULL
);
```

Para sair:

```sql
\q
```

### 5. Executar o projeto

Abra o projeto no IntelliJ IDEA.

Aguarde o Maven carregar as dependências.

Depois execute a classe:

```text
Main.java
```

O projeto irá se conectar ao PostgreSQL e executar as operações disponíveis.

## Banco de Dados

O projeto utiliza PostgreSQL através do Docker.

**Banco:** `gerenciamento_alunos`

**Usuário:** `postgres`

**Porta:** `5432`

## Objetivo

Projeto criado durante meus estudos de Java Backend para praticar:

- Java
- JDBC
- SQL
- PostgreSQL
- CRUD
- Maven
- Docker

O projeto será evoluído gradualmente para Spring Boot.

🚀 Projeto em desenvolvimento