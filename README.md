# Cloud Cuisine Microservices

- Cloud Cuisine Microservices A Spring Boot microservices backend for a food ordering system. 
- It demonstrates service discovery with Eureka, API Gateway, centralized config, distributed tracing, circuit breaking, async messaging, containerisation with Docker/Kubernetes, and JWT security.
- This project demonstrates modern microservices architecture patterns with a focus on scalability, resilience, and observability.

---

## 📐 Architecture Overview

### Infrastructure Services
- **Service Registry (Eureka Server)** → Dynamic service discovery.
- **API Gateway (Spring Cloud Gateway)** → Entry point, routing, JWT validation, logging.
- **Config Server (GitHub-backed)** Spring Cloud Config Server → Centralized configuration management.

### Core Microservices
- **Customer Service (PostgreSQL)** → Manages customer accounts, profiles, and authentication (JWT issuance).
- **Restaurant Service (MongoDB)** → Handles restaurant details and menus.
- **Order Service (PostgreSQL)** → Places and tracks orders, validates menu items.
- **Delivery Service (MongoDB)** → Assigns delivery partners and tracks delivery status.
- **Payment Service (PostgreSQL)** → Processes payments (mock initially, later real integration).
- **GraphQL Aggregation Service (Spring Boot GraphQL API)** → Exposes a single /graphql endpoint.
  - Aggregates data from Customer, Order, and Restaurant services. 
  - Allows frontend clients to fetch exact fields (e.g., customer info + orders + menu items) in one query. 
  - Simplifies client‑side data fetching and reduces multiple REST calls.

### Supporting Tools
- **Kafka / RabbitMQ** → Asynchronous communication (OrderPlaced, PaymentConfirmed events).
- **Zipkin + Sleuth** → Distributed tracing.
- **Hystrix / Resilience4j** → Circuit breaking and fault tolerance.
- **ELK Stack + Grafana** → Logging, monitoring, dashboards.
- **Docker + Kubernetes** → Containerization and orchestration.
- **GitHub Actions** → CI/CD pipeline.

---

## 🔐 Security & Authentication
- **JWT creation** → Customer Service (login/signup).
- **JWT validation** → API Gateway (primary) + each microservice (secondary for role-based access).
- Role-based access control inside services.

---

## ⚙️ Communication Patterns
- **Synchronous (REST)** → Customer → Order → Restaurant → Payment.
- **Asynchronous (Kafka/RabbitMQ)** → Order emits events → Delivery & Payment services consume.

---

## 🛡️ Resilience
- Circuit breaking with **Hystrix/Resilience4j** to prevent cascading failures.
- Retry and fallback mechanisms for critical flows.

---

## 📊 Observability
- **Distributed Tracing** → Sleuth + Zipkin.
- **Logging & Monitoring** → ELK Stack (Elasticsearch, Logstash, Kibana).
- **Metrics & Dashboards** → Prometheus + Grafana.

---

## 🚀 Deployment & DevOps
- **Docker** → Containerization of each service.
- **Kubernetes** → Orchestration, scaling, service discovery.
- **CI/CD** → GitHub Actions for automated builds/tests/deployments.

---

## 📂 Repository Structure
- cloud-cuisine-microservices/
- customer-service/
- restaurant-service/
- order-service/
- delivery-service/
- payment-service/
- api-gateway/
- eureka-server/
- config-server/
- README.md


## 📝 Notes
This project is intended as a **learning exercise** and a **complete showcase of microservices architecture**.  
Each commit represents a small, incremental step toward building a production-ready backend system.
