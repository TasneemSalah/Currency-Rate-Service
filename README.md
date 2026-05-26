# Currency Rate Service

A clean backend portfolio project built with Java 21 and Spring Boot.

The service exposes REST APIs for exchange-rate lookup and currency conversion. It is intentionally small, but structured like a production backend service: layered architecture, validation, persistence, caching, Docker, tests, OpenAPI documentation, and CI.

## Why I Built This

I built this as a public code sample to demonstrate backend engineering skills that are hard to show from private professional repositories.

## Tech Stack

- Java 21
- Spring Boot 3
- Spring Web
- Spring Data JPA
- PostgreSQL
- Redis
- Docker / Docker Compose
- Swagger / OpenAPI
- JUnit 5
- GitHub Actions

## Features

- Retrieve latest exchange rates
- Convert currency amounts
- Store conversion records
- Cache rate lookups
- Validate request payloads
- Return consistent API errors using Problem Details
- Health/metrics endpoints through Spring Actuator

## Running Locally

```bash
docker-compose up --build
```

API:

```text
http://localhost:8080
```

Swagger UI:

```text
http://localhost:8080/swagger-ui.html
```

Health check:

```text
http://localhost:8080/actuator/health
```

## API Examples

### Get latest exchange rate

```http
GET /api/v1/rates/EUR/USD
```

### Convert currency

```http
POST /api/v1/conversions
Content-Type: application/json

{
  "sourceCurrency": "EUR",
  "targetCurrency": "USD",
  "amount": 100
}
```

## Supported Demo Currency Pairs

- EUR → USD
- USD → EUR
- EUR → EGP
- EGP → EUR
- USD → EGP
- EGP → USD

A production version would integrate with an external exchange-rate provider and sync rates on a schedule.

## Architecture

```text
Controller
   ↓
Service
   ↓
Repository
   ↓
PostgreSQL / Redis
```

## Future Improvements

- Integrate a live exchange-rate provider
- Add scheduled daily rate synchronization
- Add JWT authentication
- Add rate limiting
- Add Testcontainers integration tests
- Add Kubernetes manifests
- Add observability dashboards

## Author

Tasneem Eid  
GitHub: github.com/TasneemSalah
