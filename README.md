# Instruções para Rodar a Aplicação -- Server --

### Para executar a aplicação da maneira mais simples possível, há um Docker Compose na pasta raiz do projeto completo.

Nesse Docker Compose, há a possibilidade de se alterar as variáveis de ambiente do projeto.

## Requisitos

Deve-se ter instalado o [Docker](https://docs.docker.com/get-docker/) em sua máquina.

## Estrutura do Projeto

A estrutura do projeto deve ser semelhante a esta:

```plaintext
server-teste-vr/
├── Dockerfile
├── docker-compose.yml
├── init.sql
├── pom.xml
└── src/
    ├── main/
        ├── java/
        │   └── teste/
        │       └── vr/
        │           └── server/
        │               ├── ServerApplication.java
        │               ├── controllers/
        │               ├── dtos/
        │               ├── entities/
        │               ├── exception/
        │               ├── repositories/
        │               └── services/
        │                   ├── impl/
        └── resources/

```

## Passos para Rodar a Aplicação

1. **Clone o Repositório**

   Primeiramente, clone o repositório utilizando um dos métodos abaixo:


   **Método SSH:**

   ```bash
   git clone git@github.com:WesleySCorrea/server-teste-vr.git
   ```

   **Método HTTPS:**
   
   ```bash
   git clone https://github.com/WesleySCorrea/server-teste-vr.git
   ```

2. **Executando Docker Compose**

   Após clonar o projeto, execute o comando abaixo na pasta raiz do projeto:

   ```bash
   docker-compose up -d
   ```
   
   A aplicação subirá dois containers e criará uma rede para que eles se comuniquem:

- O primeiro container é o postgres-vrsoftware, responsável pelo banco de dados PostgreSQL onde a aplicação se conectará.

- O segundo container é o server-teste-vr-container, que será a aplicação Spring Boot disponível na porta 8080.


## Acesse a Aplicação

Após os contêineres estarem em execução, você poderá acessar a aplicação Spring Boot através do cliente, disponível no link do repositório abaixo:

[Repository - Client](https://github.com/WesleySCorrea/client-teste-vr)

