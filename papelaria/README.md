# SGC - Sistema de Gestão Comercial de Papelaria

API REST para gerenciamento de clientes, produtos e vendas de uma papelaria, desenvolvida com **Spring Boot 3**, **Spring Security**, **JWT** e **MySQL**.

---

# 👥 Equipe



| Luca Romariz | https://github.com/lucaRomariz |
| Miguel Rocha  | https://github.com/MiguelSilvaRocha|

---

# 🚀 Tecnologias Utilizadas

- Java 21
- Spring Boot 3.4.5
- Spring Data JPA / Hibernate
- Spring Security
- JWT (jjwt 0.11.5)
- MySQL 8
- SpringDoc OpenAPI (Swagger UI)
- Maven

---

# 🏗️ Arquitetura do Projeto

O sistema segue uma arquitetura em camadas:

```text
Presentation (Controller)
        ↓
Service
        ↓
Repository
        ↓
Database
```

## Camadas

### Controller
Responsável por expor os endpoints REST da aplicação.

### Service
Contém toda a lógica de negócio do sistema.

### Repository
Responsável pela comunicação com o banco de dados utilizando Spring Data JPA.

### Database
Camada de persistência utilizando MySQL.

---

# 📐 Padrão de Projeto

## Repository Pattern

O sistema utiliza o padrão **Repository Pattern** na camada de persistência.

Cada entidade possui sua própria interface de repositório estendendo `JpaRepository`, separando completamente:

- regras de negócio
- acesso aos dados
- manipulação de entidades

Exemplo:

```java
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
```

---

# 📦 Estrutura de Pacotes

```text
br.sgc
├── config/          → Configuração do Swagger (OpenApiConfig)
├── controller/      → Endpoints REST
├── dto/             → Objetos de transferência de dados
├── model/           → Entidades JPA
├── repository/      → Interfaces de acesso ao banco
├── security/        → JWT, filtros e configuração de segurança
└── service/         → Regras de negócio
```

---

# ⚙️ Pré-requisitos

Antes de executar o projeto, é necessário possuir instalado:

- Java 21+
- Maven 3.8+
- MySQL 8+

---

# ▶️ Como Executar o Projeto

## 1. Clone o repositório

```bash
git clone https://github.com/seu-usuario/sgc-papelaria.git
cd sgc-papelaria/papelaria
```

---

## 2. Crie o banco de dados

Execute no MySQL:

```sql
CREATE DATABASE sgc_papelaria;
```

---

## 3. Configure o `application.properties`

Abra:

```text
src/main/resources/application.properties
```

Configure:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/sgc_papelaria
spring.datasource.username=root
spring.datasource.password=sua_senha
```

---

## 4. Execute a aplicação

### Build do projeto

```bash
./mvnw clean package -DskipTests
```

### Executar aplicação

```bash
java -jar target/papelaria-0.0.1-SNAPSHOT.jar
```

---

## 5. Acesse o Swagger

```text
http://localhost:8080/swagger-ui/index.html
```

---

# 🔐 Autenticação

A API utiliza autenticação via **JWT (JSON Web Token)**.

Após realizar login, o token retornado deve ser enviado no header das requisições protegidas:

```http
Authorization: Bearer {token}
```

---

# 🧪 Como Testar a API

---

## Passo 1 — Criar usuário

### Endpoint

```http
POST /api/usuarios
```

### Body

```json
{
  "nome": "Admin",
  "email": "admin@papelaria.com",
  "password": "123456",
  "perfil": "ADMIN"
}
```

---

## Passo 2 — Fazer login

### Endpoint

```http
POST /api/auth/login
```

### Body

```json
{
  "email": "admin@papelaria.com",
  "password": "123456"
}
```

Copie o token retornado e utilize nas próximas requisições.

---

## Passo 3 — Cadastrar cliente

### Endpoint

```http
POST /api/clientes
```

### Body

```json
{
  "nome": "João Silva",
  "cpf": "12345678901",
  "email": "joao@email.com",
  "telefone": "61999999999",
  "endereco": "Rua A, 123"
}
```

---

## Passo 4 — Cadastrar produto

### Endpoint

```http
POST /api/produtos
```

### Body

```json
{
  "nome": "Caderno Universitário",
  "descricao": "96 folhas",
  "preco": 25.90,
  "estoque": 50
}
```

---

## Passo 5 — Registrar venda

### Endpoint

```http
POST /api/vendas
```

### Body

```json
{
  "cliente": { "id": 1 },
  "usuario": { "id": 1 },
  "itens": [
    {
      "produto": { "id": 1 },
      "quantidade": 2
    }
  ]
}
```

---

# 📚 Endpoints Disponíveis

| Método | Endpoint | Descrição | Auth |
|--------|----------|-----------|------|
| POST | `/api/auth/login` | Login e geração de token | ❌ |
| POST | `/api/usuarios` | Criar usuário | ❌ |
| GET | `/api/usuarios` | Listar usuários | ✅ |
| GET | `/api/clientes` | Listar clientes | ✅ |
| POST | `/api/clientes` | Criar cliente | ✅ |
| GET | `/api/clientes/{id}` | Buscar cliente | ✅ |
| PUT | `/api/clientes/{id}` | Atualizar cliente | ✅ |
| DELETE | `/api/clientes/{id}` | Remover cliente | ✅ |
| GET | `/api/produtos` | Listar produtos | ✅ |
| POST | `/api/produtos` | Criar produto | ✅ |
| GET | `/api/produtos/{id}` | Buscar produto | ✅ |
| PUT | `/api/produtos/{id}` | Atualizar produto | ✅ |
| DELETE | `/api/produtos/{id}` | Remover produto | ✅ |
| POST | `/api/vendas` | Registrar venda | ✅ |
| GET | `/api/vendas` | Listar vendas | ✅ |
| GET | `/api/vendas/{id}` | Buscar venda | ✅ |
| GET | `/api/vendas/cliente/{id}` | Vendas por cliente | ✅ |

---

# 📋 Regras de Negócio Implementadas

- CPF de cliente não pode ser duplicado
- Cliente não pode ser removido se possuir vendas registradas
- Produto não pode possuir preço negativo
- Venda deve conter pelo menos um item
- Estoque é validado antes de registrar a venda
- Estoque é decrementado automaticamente após cada venda
- Senhas armazenadas utilizando BCrypt
- Token JWT com expiração de 24 horas

---

# 🔒 Segurança

O sistema utiliza:

- Spring Security
- JWT Authentication
- BCrypt Password Encoder
- Filtros de autenticação via token
- Rotas públicas e privadas configuradas na SecurityConfig

---

# 📖 Documentação da API

A documentação interativa da API pode ser acessada via Swagger UI:

```text
http://localhost:8080/swagger-ui/index.html
```

---

# 🛠️ Build do Projeto

Para gerar o `.jar` da aplicação:

```bash
./mvnw clean package
```

O arquivo será gerado em:

```text
target/papelaria-0.0.1-SNAPSHOT.jar
```

---

# 📌 Observações

- O sistema utiliza JPA/Hibernate para persistência
- Todas as respostas da API são em JSON
- O projeto segue separação de responsabilidades em camadas
- O Swagger facilita os testes dos endpoints sem necessidade de ferramentas externas

---

# 📄 Licença

Projeto acadêmico desenvolvido para fins educacionais.