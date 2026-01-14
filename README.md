# 🎫 Plataforma Aprimora - Backend

Sistema de gerenciamento de eventos com autenticação JWT.

## 🚀 Tecnologias

- Java 17
- Spring Boot 3.2.1
- Spring Data JPA
- H2 Database
- JWT (JSON Web Token)
- Swagger/OpenAPI
- BCrypt para criptografia

## 📋 Funcionalidades

- ✅ Cadastro e autenticação de administradores
- ✅ CRUD completo de eventos
- ✅ Autenticação com JWT
- ✅ Criptografia de senhas com BCrypt
- ✅ Documentação automática com Swagger
- ✅ Isolamento de dados por administrador

## 🔧 Como Rodar

1. Clone o repositório
2. Abra o projeto no Eclipse/IntelliJ
3. Execute como Spring Boot Application
4. Acesse: `http://localhost:8080/swagger-ui.html`

## 📚 Documentação da API

Após rodar o projeto, acesse:
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **API Docs**: http://localhost:8080/v3/api-docs

## 🗄️ Banco de Dados

Usando H2 em memória (para desenvolvimento).

Para acessar o console H2:
- URL: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:eventosdb`
- Username: `sa`
- Password: (deixe em branco)

## 📦 Endpoints Principais

### Autenticação
- POST `/api/auth/cadastrar` - Cadastrar administrador
- POST `/api/auth/login` - Fazer login (retorna JWT)

### Eventos
- POST `/api/eventos` - Criar evento
- GET `/api/eventos` - Listar eventos
- GET `/api/eventos/{id}` - Buscar evento
- PUT `/api/eventos/{id}` - Atualizar evento
- DELETE `/api/eventos/{id}` - Deletar evento

## 👨‍💻 Autor

Desenvolvido como parte do processo seletivo Residência NEKI 2025.