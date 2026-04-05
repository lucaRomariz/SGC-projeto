# SGC — Sistema de Gestão Comercial

Projeto acadêmico desenvolvido para a disciplina de Arquitetura de Software — CEUB · 2026.

---

## 1. Descrição

O Sistema de Gestão Comercial (SGC) tem como objetivo apoiar pequenos negócios no gerenciamento de suas operações comerciais, incluindo controle de clientes, produtos e vendas.

A proposta do sistema é centralizar informações, organizar processos e fornecer suporte à tomada de decisão por meio de uma solução estruturada e segura.

---

## 2. Escopo do Sistema

O sistema será responsável por:

- Gerenciamento de clientes  
- Controle de produtos e estoque  
- Registro de vendas  
- Associação de vendas a clientes e usuários  
- Geração de relatórios gerenciais  

As funcionalidades serão implementadas respeitando regras de negócio definidas na modelagem do sistema.

---

## 3. Arquitetura Proposta

O sistema será desenvolvido utilizando o padrão de **Arquitetura em Camadas**, com separação clara de responsabilidades.

Estrutura prevista:

Apresentação → Controller → Serviço → Domínio → Persistência → Banco de Dados


Essa organização visa:

- Reduzir acoplamento entre componentes  
- Facilitar manutenção e evolução  
- Permitir testes mais isolados  

---

## 4. Modelagem

A modelagem do sistema contempla os seguintes artefatos:

- Diagrama de Domínio  
- Diagrama de Classes  
- Diagrama Lógico do Banco de Dados  

Esses elementos definem a estrutura conceitual do sistema e orientam sua futura implementação.

---

## 5. Padrões de Projeto (Planejados)

Para garantir organização e boas práticas, está prevista a utilização dos seguintes padrões:

- **Repository**: abstração do acesso aos dados  
- **DTO (Data Transfer Object)**: separação entre dados de transporte e domínio  

Outros padrões poderão ser incorporados conforme a evolução do sistema.

---

## 6. Estrutura Inicial do Projeto

A organização do projeto seguirá a divisão por responsabilidades alinhada à arquitetura em camadas:

backend/
 ├── config/
 ├── controller/
 ├── service/
 ├── domain/
 │    ├── model/
 │    ├── repository/
 │    └── enums/
 ├── dto/
 ├── exception/
 └── util/


Essa estrutura será refinada conforme o desenvolvimento do sistema.

---

## 7. Banco de Dados (Visão Inicial)

O sistema será baseado em um modelo relacional, contendo entidades principais como:

- Usuários  
- Clientes  
- Produtos  
- Vendas  
- Itens de venda  

As definições completas encontram-se na documentação do projeto.

---

## 8. Documentação

Os artefatos da primeira entrega estão disponíveis na pasta `docs/`, incluindo:

- Documento de modelagem  
- Diagramas do sistema  
- Script inicial do banco de dados  

---

## 9. Repositório

O projeto está sendo versionado no GitHub para controle de versões e colaboração entre os integrantes da equipe.

---

## 10. Equipe

| Nome | GitHub |
|------|--------|
| Luca Romariz | https://github.com/lucaRomariz |
| Integrante  | — |
| Integrante  | — |

---

## 11. Observações

Este repositório representa a fase inicial do projeto, com foco em modelagem, definição arquitetural e planejamento da solução.

A implementação completa será desenvolvida nas etapas posteriores.
