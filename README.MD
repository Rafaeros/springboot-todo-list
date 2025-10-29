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
- **Maven**
- **TailwindCSS** (para o front-end responsivo)
- **PostgreSQL** (banco de dados)
---

## 🧩 Funcionalidades Principais

- 🔐 **Login e Cadastro de Usuários**
  - Registro de novos usuários
  - Autenticação e controle de sessão
  - Proteção de rotas com Spring Security

- 🗂️ **Categorias de Tarefas (Em desenvolvimento)**
  - Criação, edição e exclusão de categorias
  - Cada tarefa pertence a uma categoria

- 📝 **Gerenciamento de Tarefas**
  - Criar, visualizar, atualizar e excluir tarefas
  - Status: pendente ou concluída
  - Registro de data de conclusão

---

## 🖼️ Capturas de Tela

Login | Cadastro | Home
--- | --- | ---
![Login](https://drive.google.com/file/d/1cF93GqzdY2PfIkyp0vjp86s9xF7y5v1-/view?usp=sharing) | ![Cadastro](https://drive.google.com/file/d/1qAs5kBAYuNLg-3KYaN6LD6ZzAyciDCeD/view?usp=sharing) | ![Home](https://drive.google.com/file/d/1VvqyNa_ysUnbFh0qrp7Lti90UdBRwQZP/view?usp=sharing)

---

## 🚀 Configuração do projeto

1. Clone o repositório do GitHub: `git clone https://github.com/rafaeros/springboot-todo-list.git`
2. Navegue para a pasta do projeto: `cd springboot-todo-list`
3. Instale as dependências: `mvn install`
4. configure o banco de dados em `src/main/resources/application.properties`
  ```properties
  spring.datasource.url=jdbc:postgresql://localhost:5432/yourdatabase
  spring.datasource.username=yourusername
  spring.datasource.password=yourpassword
  ```
5. Execute o projeto: `mvn spring-boot:run`
6. Acesse o projeto em http://localhost:8090

---

## 📝 Contribua

Se vocês gostam do projeto e deseja contribuir, sinta-se livre para fazer um pull request ou abrir um issue.

---

## 📜 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](https://github.com/Rafaeros/springboot-todo-list/blob/main/LICENSE) para mais detalhes.


---
