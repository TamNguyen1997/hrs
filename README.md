# HRS Parcel Management API

A Spring Boot 3 application that helps receptionists track guests and parcels.  
Receptionists can:

- Check which guests are currently checked in.
- Accept parcels for guests (only if checked in).
- Check which parcels are waiting for pickup.

---

## Features
- Guest management.
- Parcel management.
- REST API documented with Swagger UI.
- PostgreSQL persistence with JPA/Hibernate.
- Containerized with Docker.
- CI/CD via GitHub Actions + Terraform (mock AWS EC2 deployment).

---

## Tech Stack
- Java 17, Spring Boot 3
- Spring Data JPA
- PostgreSQL
- Springdoc OpenAPI (Swagger)
- Docker
- Spock Framework (unit + functional tests)
- Terraform (AWS EC2 mock deployment)
- GitHub Actions (CI/CD pipeline)


## Run application in Docker
```
docker-compose up --build
```
