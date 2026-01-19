# 🎫 Plataforma Aprimora - Backend

Sistema de gerenciamento de eventos com autenticação JWT.

## 🚀 Tecnologias

- Java 17
- Spring Boot 3.2.1
- Spring Data JPA
- PostgreSQL
- JWT (JSON Web Token)
- Swagger / OpenAPI
- BCrypt para criptografia de senhas

## 📋 Funcionalidades

- ✅ Cadastro e autenticação de administradores
- ✅ CRUD completo de eventos
- ✅ Autenticação com JWT
- ✅ Criptografia de senhas com BCrypt
- ✅ Documentação automática com Swagger
- ✅ Isolamento de dados por administrador

## 🔧 Como Rodar

1. Clone o repositório
2. Abra o projeto no Eclipse ou IntelliJ
3. Configure o banco PostgreSQL no `application.properties` ou `application.yml`
4. Execute como Spring Boot Application
5. Acesse:
   - Swagger UI: http://localhost:8080/swagger-ui.html

## 📚 Documentação da API

Após rodar o projeto, acesse:

- Swagger UI: http://localhost:8080/swagger-ui.html
- API Docs: http://localhost:8080/v3/api-docs

## 🗄️ Banco de Dados

Utiliza PostgreSQL como banco de dados relacional.

Exemplo de configuração no `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/aprimora
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

Certifique-se de que o PostgreSQL esteja rodando e que o banco já tenha sido criado.

## 📦 Endpoints Principais

### 🔐 Autenticação

- `POST /api/auth/cadastrar` – Cadastrar administrador
- `POST /api/auth/login` – Fazer login (retorna JWT)

### 🎟️ Eventos

- `POST /api/eventos` – Criar evento
- `GET /api/eventos` – Listar eventos
- `GET /api/eventos/{id}` – Buscar evento
- `PUT /api/eventos/{id}` – Atualizar evento
- `DELETE /api/eventos/{id}` – Deletar evento

## 👨‍💻 Autor

**Alexandre Lício da Silva Morais**

Desenvolvido como parte do processo seletivo Residência NEKI 2025.
