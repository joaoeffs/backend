# Teste Técnico - Backend

Este projeto é uma API RESTful desenvolvida com Spring Boot, e Java 17 que permite o gerenciamento de produtos, serviços e pedidos com regras de negócio específicas.

### Observação

O projeto está configurado para utilizar o banco de dados **H2 em memória** por padrão, o que facilita a execução local e a realização de testes rápidos.

Caso prefira utilizar **PostgreSQL**, a configuração já está disponível no arquivo `application.yml`.  
Basta ajustar os parâmetros de conexão e iniciar a aplicação normalmente.

---

## Como Executar o Projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/joaoeffs/backend
   ```
2. Navegue até o diretório do projeto:
   ```bash
   cd backend
   ```
3. Instale as dependências
   ```bash
   mvn clean install -DskipTests
   ```
4. Suba a aplicação:
   ```bash
   mvn spring-boot:run
   ```

---

## Como utilizar a Aplicação

1. Acesse o Swagger UI:
   - URL: http://localhost:8080/swagger-ui/index.html
2. Os endpoints estão organizado em dois grupos:
   - Core: operações de escrita no banco de dados (POST, PUT, DELETE).
   - Query: operações de leitura no banco de dados, com filtros (GET).
3. Banco de dados:
   - Utilizei H2 em memória para facilitar o desenvolvimento e testes.
   - Para visualizar os dados, acesse o console do H2:
     - URL: http://localhost:8080/h2-console
     - username: `sa` | password: `123`
   - Os dados são persistidos em memória e serão perdidos ao reiniciar a aplicação.
4. Para testar:
   - Expanda os endpoints no Swagger para visualizar os parâmetros e o formato esperado do `body`.
   - Preencha os dados e clique em "Execute" para testar as operações.

---

## Funcionalidades

### Gerenciamento de Produtos e Serviços

- **Cadastro**: criação de itens com `descrição`, `preço` e `tipo` (`PRODUTO` ou `SERVIÇO`).
- **Alteração**: edição dos dados de um item existente.
- **Exclusão**: remoção definitiva do item (quando permitido).
- **Inativação**: torna o item indisponível para novos pedidos.
- **Ativação**: ativa um item inativado.
- **Listagem**: consulta com paginação e filtros por `descrição`, `tipo`, `situação` e `preço`.

---

### Gerenciamento de Pedidos

- **Cadastro**: criação de pedidos com `cliente` e `número do pedido`.
- **Adição de Itens**: inclusão de produtos ou serviços no pedido.
- **Remoção de Itens**: exclusão de itens antes do fechamento do pedido.
- **Aplicação de Desconto**: desconto informado pelo usuário é aplicado apenas em itens do tipo `PRODUTO`.
- **Fechamento**: impede alterações e consolida os dados do pedido.
- **Cancelamento**: marca o pedido como `CANCELADO`, sem possibilidade de reativação.
- **Listagem**: consulta com paginação e filtros por `cliente`, `número do pedido` e `situação`.

