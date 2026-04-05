# SGC — Sistema de Gestão Comercial

Sistema de gestão comercial para pequenos negócios, desenvolvido como projeto acadêmico da disciplina de Arquitetura de Software — CEUB · 2026.

---

## Sumário

- [Tecnologias](#tecnologias)
- [Arquitetura](#arquitetura)
- [Design Patterns](#design-patterns)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Banco de Dados](#banco-de-dados)
- [Documentação](#documentação)
- [Equipe](#equipe)

---

## Tecnologias

| Tecnologia | Versão |
|---|---|
| Java | 21+ |
| Spring Boot | 3+ |
| Spring Data JPA | 3+ |
| Spring Security | 3+ |
| MySQL | 8+ |
| JWT (jjwt) | 0.12+ |
| Maven | 3.9+ |
| Lombok | latest |

---

## Arquitetura

O sistema utiliza **Arquitetura em Camadas**:

```
Apresentação  →  Controller  →  Service  →  Domain  →  Repository  →  MySQL
```

Cada camada tem responsabilidade única e se comunica apenas com a camada adjacente.

---

## Design Patterns

| Pattern | Onde | Por quê |
|---|---|---|
| **Repository** | `ClienteRepository`, `ProdutoRepository`, `VendaRepository`, `UsuarioRepository` | Abstrai o acesso ao banco; facilita testes com mocks |
| **DTO** | `ClienteDTO`, `ProdutoDTO`, `VendaDTO`, `AuthRequestDTO` | Separa o contrato da API das entidades de domínio; evita expor dados internos |
| **Strategy** | `SecurityConfig`, `JwtAuthenticationFilter` | Permite trocar a estratégia de autenticação sem alterar controllers |

---

## Estrutura do Projeto

```
sgc/
├── docs/
│   ├── diagrama-de-classes.png
│   ├── diagrama-de-dominio.png
│   ├── diagrama-logico-bd.png
│   ├── script-banco.sql
│   └── documentacao-entrega1.pdf
│
└── backend/
    └── src/main/java/br/com/sgc/
        ├── config/          # SecurityConfig, JwtService, JwtAuthenticationFilter
        ├── controller/      # AuthController, ClienteController, ProdutoController, VendaController
        ├── service/         # AuthService, ClienteService, ProdutoService, VendaService
        ├── domain/
        │   ├── model/       # Cliente, Produto, Venda, ItemVenda, Usuario
        │   ├── repository/  # ClienteRepository, ProdutoRepository, VendaRepository, UsuarioRepository
        │   └── enums/       # PerfilUsuario
        ├── dto/             # AuthRequestDTO, ClienteDTO, ProdutoDTO, VendaDTO
        └── exception/       # BusinessException, ResourceNotFoundException, GlobalExceptionHandler
```

---

## Banco de Dados

Cinco tabelas principais: `usuarios`, `clientes`, `produtos`, `vendas`, `itens_venda`.

Principais regras de integridade:
- CPF e e-mail únicos por cliente
- Preço e estoque não podem ser negativos
- Cliente com vendas não pode ser removido
- Venda exige ao menos um item

> Script completo em [`docs/script-banco.sql`](./docs/script-banco.sql).

---


## Documentação

Os documentos da Entrega 1 estão em [`docs/`](./docs/):

| Arquivo | Conteúdo |
|---|---|
| `documentacao-entrega1.pdf` | Requisitos, arquitetura e padrões de projeto |
| `diagrama-de-classes.png` | Diagrama de classes |
| `diagrama-de-dominio.png` | Diagrama de domínio |
| `diagrama-logico-bd.png` | Diagrama lógico do banco |
| `script-banco.sql` | Script de criação das tabelas |

---

## Equipe

| Nome | GitHub |
|---|---|
| Integrante 1 | [@usuario1](https://github.com/lucaRomariz) |
| Integrante 2 | [@usuario2](https://github.com/) |
| Integrante 3 | [@usuario3](https://github.com/) |


---

> Projeto acadêmico — Disciplina de Arquitetura de Software — CEUB · 2026
