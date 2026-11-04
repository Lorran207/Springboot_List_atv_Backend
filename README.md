# springBootList

Aplicação web CRUD de produtos desenvolvida com Spring Boot, Spring Data JPA, DTOs (Java Records) e Thymeleaf com Bootstrap 5.

## Requisitos

- Java 17+
- Maven 3.9+
- MySQL 8+

## Como rodar localmente

Suba o MySQL (ou use o compose):
```bash
docker compose up -d
```

Execute a aplicação:
```bash
mvn spring-boot:run
```

Acesse em: `http://localhost:8080/products`

## Variáveis de ambiente (Nuvem / Render)

Para rodar em produção/nuvem (ex: Render + TiDB Cloud):
- `SPRING_DATASOURCE_URL`
- `SPRING_DATASOURCE_USERNAME`
- `SPRING_DATASOURCE_PASSWORD`
- `PORT` (definida automaticamente pelo provedor)
