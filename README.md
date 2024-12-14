# Library System
<div>
    <img alt="GitHub commit activity" src="https://img.shields.io/github/commit-activity/t/guilhermevnbraga/librarysystem">
    <img alt="Último commit" src="https://img.shields.io/github/last-commit/guilhermevnbraga/librarysystem">
    <img alt="Tamanho do repositório" src="https://img.shields.io/github/repo-size/guilhermevnbraga/librarysystem">
    <img alt="Github contributors" src="https://img.shields.io/github/contributors/guilhermevnbraga/librarysystem">
    <img alt="GitHub top language" src="https://img.shields.io/github/languages/top/guilhermevnbraga/librarysystem">
    <img alt="License" src="https://img.shields.io/github/license/guilhermevnbraga/librarysystem">
</div>

## Sobre
O **Library System** é uma aplicação desenvolvida em Java para gerenciar uma biblioteca. Os usuários podem registrar-se, fazer login, adicionar livros, pesquisar livros, emprestar e devolver livros. Administradores têm permissões adicionais para listar todos os clientes e visualizar o histórico de empréstimos.

## Requisitos e Como Rodar a Aplicação Localmente

### Pré-requisitos
- **Java** JDK instalado

### Passos para Configuração

1. Clone o repositório:
    ```bash
    git clone https://github.com/guilhermevnbraga/LibrarySystem.git
    ```

2. Navegue até o diretório do projeto em seu terminal;

3. Compile o arquivo principal na pasta do projeto:
    ```bash
    javac Libray.java
    ```

4. Execute a aplicação:
    ```bash
    java Library
    ```

## Estrutura do Projeto

O projeto é dividido em várias partes principais:

- **Models**: Contém as classes de modelo como Customer, Author, Book, e Borrow.
- **Main**: Contém a classe principal Library que gerencia a lógica da aplicação.
- **Database**: Contém os arquivos CSV que armazenam os dados dos clientes, autores, livros e empréstimos.