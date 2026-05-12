# Documentação Técnica — SGC Papelaria

Documentação técnica do sistema **SGC Papelaria**, uma API REST desenvolvida com Spring Boot para gerenciamento de clientes, produtos, usuários e vendas.

---

# 📚 Sumário

1. Descrição da Arquitetura
2. Padrão de Projeto Utilizado
3. Justificativa das Decisões Técnicas
4. Requisitos Funcionais Implementados
5. Segurança
6. Banco de Dados
7. Estrutura de Pacotes
8. Tecnologias Utilizadas
9. Instruções para Execução
10. Link do Repositório

---

# 🏗️ 1. Descrição da Arquitetura

O sistema utiliza **Arquitetura em Camadas**, promovendo separação de responsabilidades, organização do código e facilidade de manutenção.

---

## Camada de Apresentação (Controller)

Responsável por receber as requisições HTTP, delegar o processamento para a camada de serviço e retornar respostas em JSON.

### Controllers implementados

- `AuthController` — autenticação e login
- `ClienteController` — CRUD de clientes
- `ProdutoController` — CRUD de produtos
- `UsuarioController` — cadastro e listagem de usuários
- `VendaController` — registro e consulta de vendas

### Responsabilidades

- Receber requisições HTTP
- Validar dados de entrada
- Chamar a camada Service
- Retornar respostas HTTP adequadas

---

## Camada de Serviço (Service)

Contém toda a lógica de negócio da aplicação.

### Services implementados

- `ClienteService`
- `ProdutoService`
- `UsuarioService`
- `VendaService`

### Responsabilidades

- Aplicar regras de negócio
- Validar dados antes da persistência
- Controlar fluxo da aplicação
- Lançar exceções customizadas
- Coordenar operações entre entidades

---

## Camada de Domínio (Model)

Representa as entidades do sistema mapeadas para o banco de dados através do JPA/Hibernate.

### Entidades

- `Cliente`
- `Produto`
- `Usuario`
- `Venda`
- `ItemVenda`

### Responsabilidades

- Representar tabelas do banco
- Definir relacionamentos
- Mapear atributos persistidos

---

## Camada de Persistência (Repository)

Responsável pelo acesso ao banco de dados utilizando Spring Data JPA.

### Repositories implementados

- `ClienteRepository`
- `ProdutoRepository`
- `UsuarioRepository`
- `VendaRepository`
- `ItemVendaRepository`

### Responsabilidades

- Persistência de dados
- Consultas ao banco
- Operações CRUD
- Queries automáticas do Spring Data

---

## Camada de Segurança (Security)

Responsável pela autenticação e autorização utilizando JWT.

### Componentes implementados

- `JwtService`
- `CustomUserDetailsService`
- `JwtAuthenticationFilter`
- `SecurityConfig`

### Responsabilidades

- Gerar tokens JWT
- Validar autenticação
- Proteger endpoints
- Configurar permissões de acesso

---

# 📐 2. Padrão de Projeto Utilizado

## Repository Pattern

### Onde foi aplicado

O padrão foi aplicado na camada de persistência através das interfaces Repository.

Cada entidade possui sua própria interface de repositório.

### Exemplo

```java
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findByCpf(String cpf);

    Cliente findByEmailIgnoreCase(String email);

}
```

---

## Por que foi escolhido

O padrão Repository permite desacoplar completamente:

- lógica de negócio
- persistência de dados
- consultas ao banco

Isso facilita manutenção, testes e evolução futura do sistema.

---

## Benefícios arquiteturais

- Separação clara de responsabilidades
- Código mais limpo e organizado
- Fácil manutenção
- Facilidade para testes
- Menor acoplamento
- Queries automáticas com Spring Data JPA
- Redução de SQL manual

---

# ⚙️ 3. Justificativa das Decisões Técnicas

| Decisão | Justificativa |
|---------|---------------|
| Spring Boot 3.4.5 | Framework moderno com configuração automática |
| Spring Data JPA | Simplifica persistência e reduz SQL manual |
| MySQL | Banco relacional robusto e amplamente utilizado |
| JWT (jjwt 0.11.5) | Autenticação stateless sem sessões no servidor |
| BCrypt | Algoritmo seguro para criptografia de senhas |
| Spring Security | Controle completo de autenticação e autorização |
| Swagger / SpringDoc | Documentação interativa da API |
| Maven | Gerenciamento de dependências e build |

---

# ✅ 4. Requisitos Funcionais Implementados

| Código | Descrição | Status |
|--------|-----------|--------|
| RF01 | Cadastrar cliente | ✅ |
| RF02 | Listar clientes | ✅ |
| RF03 | Atualizar cliente | ✅ |
| RF04 | Remover cliente | ✅ |
| RF05 | Cadastrar produto | ✅ |
| RF06 | Listar produtos | ✅ |
| RF07 | Atualizar produto | ✅ |
| RF08 | Controlar estoque | ✅ |
| RF09 | Registrar venda | ✅ |
| RF10 | Não permitir venda sem itens | ✅ |
| RF11 | Controle de acesso por perfil | ✅ |

---

# 🔒 5. Segurança

O sistema utiliza autenticação baseada em **JWT (JSON Web Token)**.

---

## Fluxo de autenticação

1. Cliente envia `POST /api/auth/login`
2. Spring Security valida e-mail e senha
3. BCrypt verifica senha criptografada
4. `JwtService` gera token JWT
5. Token é retornado ao cliente
6. Cliente envia o token no header:
   ```http
   Authorization: Bearer {token}
   ```
7. `JwtAuthenticationFilter` intercepta a requisição
8. Token é validado
9. Se válido, acesso é liberado

---

## Rotas públicas

```text
POST /api/auth/login
POST /api/usuarios
GET /swagger-ui/**
GET /v3/api-docs/**
```

---

## Rotas protegidas

Todas as rotas:

```text
/api/**
```

exigem token JWT válido.

---

## Configuração do token

| Configuração | Valor |
|--------------|------|
| Algoritmo | HS256 |
| Expiração | 24 horas |
| Criptografia de senha | BCrypt |

---

# 🗄️ 6. Banco de Dados

As tabelas são criadas automaticamente pelo Hibernate através das entidades JPA.

---

## Estrutura SQL

```sql
-- Usuários do sistema
CREATE TABLE usuarios (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    perfil VARCHAR(255) NOT NULL
);

-- Clientes da papelaria
CREATE TABLE clientes (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL,
    telefone VARCHAR(255),
    endereco VARCHAR(255)
);

-- Produtos disponíveis
CREATE TABLE produtos (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(255),
    preco FLOAT NOT NULL,
    estoque INTEGER NOT NULL
);

-- Vendas realizadas
CREATE TABLE vendas (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    data DATE NOT NULL,
    valor_total FLOAT NOT NULL,
    cliente_id INTEGER NOT NULL,
    usuario_id INTEGER NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES clientes(id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

-- Itens de cada venda
CREATE TABLE itens_venda (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    quantidade INTEGER NOT NULL,
    preco_unitario FLOAT NOT NULL,
    venda_id INTEGER NOT NULL,
    produto_id INTEGER NOT NULL,
    FOREIGN KEY (venda_id) REFERENCES vendas(id),
    FOREIGN KEY (produto_id) REFERENCES produtos(id)
);
```

---

# 📦 7. Estrutura de Pacotes

```text
br.sgc
├── config/          → Configurações gerais
├── controller/      → Endpoints REST
├── dto/             → Objetos de transferência de dados
├── model/           → Entidades JPA
├── repository/      → Persistência de dados
├── security/        → JWT e segurança
└── service/         → Regras de negócio
```

---

# 🚀 8. Tecnologias Utilizadas

- Java 21
- Spring Boot 3.4.5
- Spring Security
- Spring Data JPA
- Hibernate
- JWT (jjwt 0.11.5)
- MySQL 8
- Swagger / SpringDoc
- Maven

---

# ▶️ 9. Instruções para Execução

## 1. Clone o repositório

```bash
git clone https://github.com/seu-usuario/sgc-papelaria.git
cd sgc-papelaria/papelaria
```

---

## 2. Crie o banco de dados

```sql
CREATE DATABASE sgc_papelaria;
```

---

## 3. Configure o application.properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/sgc_papelaria
spring.datasource.username=root
spring.datasource.password=sua_senha
```

---

## 4. Execute a aplicação

```bash
java -jar target/papelaria-0.0.1-SNAPSHOT.jar
```

---

## 5. Acesse o Swagger

```text
http://localhost:8080/swagger-ui/index.html
```

---

# 🔗 10. Link do Repositório

```text
https://github.com/lucaRomariz/SGC-projeto
```

---

# 📌 Considerações Finais

O sistema foi desenvolvido seguindo boas práticas de arquitetura e segurança, utilizando separação em camadas, autenticação JWT e persistência com Spring Data JPA.

A estrutura facilita:

- manutenção
- escalabilidade
- organização do código
- testes
- evolução futura do projeto

---

# 📄 Licença

Projeto acadêmico desenvolvido para fins educacionais.