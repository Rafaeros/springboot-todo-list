# ✅ To-Do List com Login, Cadastro e Categorias

Um sistema de **gerenciamento de tarefas (To-Do List)** desenvolvido em **Java com Spring Boot**, com **autenticação de usuários** e **organização de tarefas por categoria**.

---

## 🚀 Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot 3.x**
  - Spring Web
  - Spring Data JPA
  - Spring Security
  - Thymeleaf
- **Hibernate**
- **Maven**
- **TailwindCSS** (para o front-end responsivo)

---

## 📦 Estrutura do Projeto

📂 src
┣ 📂 main
┃ ┣ 📂 java/br/rafaeros/todo
┃ ┃ ┣ 📂 controller
┃ ┃ ┣ 📂 model
┃ ┃ ┣ 📂 repository
┃ ┃ ┣ 📂 service
┃ ┃ ┗ 📜 TodoApplication.java
┃ ┗ 📂 resources
┃ ┣ 📂 templates (HTML com Thymeleaf)
┃ ┣ 📂 static (CSS, JS)
┃ ┗ 📜 application.properties
┗ 📂 test

---

## 🧩 Funcionalidades Principais

- 🔐 **Login e Cadastro de Usuários**
  - Registro de novos usuários
  - Autenticação e controle de sessão
  - Proteção de rotas com Spring Security

- 🗂️ **Categorias de Tarefas**
  - Criação, edição e exclusão de categorias
  - Cada tarefa pertence a uma categoria

- 📝 **Gerenciamento de Tarefas**
  - Criar, visualizar, atualizar e excluir tarefas
  - Status: pendente, em andamento ou concluída
  - Atribuição de prioridade
  - Registro de data de criação e conclusão

- 📊 **Painel do Usuário**
  - Listagem de tarefas do usuário logado
  - Filtro por categoria e status

---