# Desafio Técnico Pacto Mais

API REST para gerenciamento de correntistas, contas bancárias e transações,
desenvolvida como parte do desafio técnico para estágio na Pacto Mais.

O projeto foi desenvolvido com foco nos requisitos obrigatórios, utilizando
Java 8, Spring Boot, Spring Data JPA, Hibernate e MySQL.

## Tecnologias utilizadas

- Java 8
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- MySQL 8
- Maven

## Sobre o projeto

A aplicação permite cadastrar correntistas, criar contas correntes e
poupanças, realizar depósitos e saques e consultar as transações realizadas.

Cada correntista pode possuir mais de uma conta.

A aplicação utiliza uma separação entre `Controller`, `Service`, `Repository`
e `Entity`, mantendo as responsabilidades organizadas.

## Regras de negócio

### Conta Corrente

Possui um limite que pode ser utilizado durante um saque. O saque é permitido
quando o valor não ultrapassa o saldo disponível somado ao limite.

### Conta Poupança

O saque só pode ser realizado quando houver saldo suficiente.

### Depósitos e saques

O valor das operações deve ser maior que zero.

Ao realizar um depósito ou saque, o saldo é atualizado e uma transação
correspondente é registrada.

## Organização do projeto

```text
src/main/java/com/pactomais/desafio
├── controller
├── entity
├── exception
├── repository
├── service
├── transaction
└── DesafioApplication
```

Principais classes:

- `CorrentistaController`
- `ContaController`
- `TransacaoController`
- `Correntista`
- `Conta`
- `ContaCorrente`
- `ContaPoupanca`
- `Transacao`
- `CorrentistaRepository`
- `ContaRepository`
- `TransacaoRepository`
- `CorrentistaService`
- `ContaService`
- `TransacaoService`

## Como executar

### Pré-requisitos

- Java 8
- MySQL 8
- Maven

### 1. Criar o banco

```sql
CREATE DATABASE desafio;
```

### 2. Configurar o banco

Configure o arquivo `src/main/resources/application.properties`:

```properties
spring.application.name=desafio
spring.datasource.url=jdbc:mysql://localhost:3306/desafio?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=SUA_SENHA
spring.jpa.hibernate.ddl-auto=update
```

O `application.properties` não é versionado por conter configurações locais.

### 3. Executar

Dentro da pasta do projeto:

```bash
mvn spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

## Endpoints disponíveis

### Correntistas

**Cadastrar correntista**

```http
POST /correntista
```

Exemplo de requisição:

```json
{
  "nome": "Ana Clara",
  "documento": "12345678900",
  "email": "ana@email.com",
  "telefone": "83999999999"
}
```

Retorno: `201 Created`

**Listar correntistas**

```http
GET /correntista
```

Retorno: `200 OK`

**Buscar correntista por ID**

```http
GET /correntista/{id}
```

Exemplo: `GET /correntista/1`

Retornos: `200 OK` ou `404 Not Found`

### Contas

**Criar conta corrente**

```http
POST /conta/corrente/{correntistaId}
```

Exemplo: `POST /conta/corrente/1`

```json
{
  "numero": "0001",
  "saldo": 1000.0,
  "limite": 500.0
}
```

Retorno: `201 Created`

**Criar conta poupança**

```http
POST /conta/poupanca/{correntistaId}
```

Exemplo: `POST /conta/poupanca/1`

```json
{
  "numero": "0002",
  "saldo": 800.0
}
```

Retorno: `201 Created`

**Listar contas**

```http
GET /conta
```

Retorno: `200 OK`

**Buscar conta por ID**

```http
GET /conta/{id}
```

Exemplo: `GET /conta/1`

Retornos: `200 OK` ou `404 Not Found`

**Listar contas de um correntista**

```http
GET /conta/correntista/{correntistaId}
```

Exemplo: `GET /conta/correntista/1`

Retorno: `200 OK`

**Realizar depósito**

```http
POST /conta/{contaId}/deposito?valor={valor}
```

Exemplo: `POST /conta/1/deposito?valor=100`

Não é necessário enviar JSON.

Retornos: `200 OK` ou `400 Bad Request`

**Realizar saque**

```http
POST /conta/{contaId}/saque?valor={valor}
```

Exemplo: `POST /conta/1/saque?valor=100`

Não é necessário enviar JSON.

Retornos: `200 OK` ou `400 Bad Request`

### Transações

**Listar todas as transações**

```http
GET /transacao
```

Exemplo de resposta:

```json
[{
  "id": 1,
  "tipo": "DEPOSITO",
  "valor": 100.0,
  "data": "2026-09-18T15:30:00"
}]
```

Retorno: `200 OK`

**Listar transações de uma conta**

```http
GET /transacao/conta/{contaId}
```

Exemplo: `GET /transacao/conta/1`

Retorno: `200 OK`

## Tratamento de erros

A aplicação possui tratamento global para erros relacionados às regras
de negócio.

São utilizados os seguintes códigos:

- `200 OK` — operação realizada com sucesso
- `201 Created` — recurso criado
- `400 Bad Request` — regra de negócio violada
- `404 Not Found` — recurso não encontrado

Exemplos de erros:

```text
Saldo insuficiente
Saldo e limite insuficientes
O valor do deposito deve ser maior que zero
```

## Banco de dados

O projeto utiliza MySQL para persistência dos dados.

O Hibernate cria e atualiza as tabelas através de:

```properties
spring.jpa.hibernate.ddl-auto=update
```

O script SQL está disponível em:

```text
src/main/resources/schema.sql
```

## Diferenciais implementados

### Testes unitários

O projeto possui testes unitários utilizando JUnit 5 e Mockito.

Os testes implementados cobrem:

- Depósito em conta
- Saque em conta
- Regra de negócio que impede saque acima do saldo permitido

Atualmente, são três testes unitários implementados e aprovados.

Para executar os testes pelo IntelliJ IDEA, basta executar a classe `ContaServiceTest`.

## Diferenciais não implementados

Os seguintes diferenciais não foram implementados:

- Rendimento mensal da conta poupança
- Juros sobre saldo negativo
- Swagger/OpenAPI
- Padronização avançada das respostas de erro

A decisão foi priorizar os requisitos obrigatórios dentro do prazo do desafio, garantindo o funcionamento das principais funcionalidades.

Como evolução, o rendimento e os juros poderiam ser implementados através de rotinas específicas que atualizariam o saldo e registrariam as operações como transações. O Swagger poderia ser utilizado para documentar visualmente os endpoints.

## Considerações finais

O projeto contempla cadastro de correntistas, criação de contas, depósitos,
saques, aplicação das regras de negócio e registro de transações.

Os diferenciais não implementados foram documentados juntamente com uma
proposta de implementação futura.

---

**Desafio técnico — Pacto Mais**

Desenvolvimento em Java com Spring Boot