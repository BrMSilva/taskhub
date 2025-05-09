# TaskHub

TaskHub é uma API REST desenvolvida com Spring Boot e Kotlin para gerenciamento de projetos e tarefas.  
Ideal para equipes que desejam organizar seus fluxos de trabalho com usuários, projetos e tarefas, de forma clara e eficiente.

---

## Tecnologias Utilizadas

- Kotlin
- Spring Boot
- Spring Data JPA
- H2 Database (para testes locais)
- JUnit 5
- Mockito
- Gradle Kotlin DSL
- Git & GitHub

---

## Funcionalidades

- CRUD de Projetos
- CRUD de Tarefas com associação a projetos e usuários
- CRUD de Usuários
- Validações com DTOs
- Tratamento global de exceções com `@ControllerAdvice`
- Testes unitários para camada de service
- Endpoints REST prontos para integração

---

## Estrutura de Diretórios

```
src/
├── main/
│   ├── kotlin/
│   │   └── com/seunome/taskhub/
│   │       ├── controller/
│   │       ├── dto/
│   │       ├── model/
│   │       ├── repository/
│   │       ├── service/
│   │       └── exception/
│   └── resources/
│       └── application.properties
└── test/
    └── kotlin/
        └── com/seunome/taskhub/
            ├── service/
            └── controller/
```

---

## Exemplos de Endpoints

- `GET /users`
- `POST /projects`
- `GET /tasks/{id}`
- `POST /tasks`

---

## Melhorias Futuras

- Autenticação com Spring Security e JWT
- Integração com banco de dados PostgreSQL
- Deploy na nuvem (Heroku, Railway, Vercel)
- Desenvolvimento de um frontend em React ou Vue

---

## Autor

Desenvolvido por Bruno Silva  
GitHub: [github.com/BrMSilva](https://github.com/BrMSilva)

