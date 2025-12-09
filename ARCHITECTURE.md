# ARCHITECTURE.md

## 1. Overview
**Project Name:** MicroServiceTask  
**Module:** `dj-sre-task`  
**Purpose:** Design and develop a microservice application that handles product management and inventory tracking.  
**End Users:** Clients who interact with the system via APIs to manage products and inventory.

---

## 2. Architecture Style
The system follows a **microservices architecture**, with separate services for products and inventory, coordinated via an API gateway and service discovery.

---

## 3. Technology Stack
| Layer/Component | Technology |
|-----------------|------------|
| Programming Language | Java |
| Framework | Spring Boot |
| Database | MySQL, PostgreSQL |
| Communication | HTTP, TCP requests |
| Caching | None |
| Containerization | Docker |
| Orchestration | Kubernetes |
| Resilience | Circuit Breaker pattern (Spring) |

---

## 4. Key Components

1. **API Gateway**
    - Entry point for all client requests.
    - Routes requests to appropriate services (`product-service` and `inventory-service`).

2. **Product Service**
    - Handles product creation, update, and retrieval.
    - Communicates with the inventory service to ensure stock consistency.
    - Implements **Circuit Breaker** pattern for resilience.

3. **Inventory Service**
    - Manages inventory stock.
    - Receives requests from `product-service` to validate or update stock.

4. **Eureka (Service Discovery)**
    - Enables dynamic discovery of services for communication between microservices.

---

## 5. Data Flow

1. Client sends a request to **API Gateway** to create a product.
2. API Gateway forwards the request to **Product Service**.
3. Product Service validates data and calls **Inventory Service** to check stock availability.
4. Inventory Service responds with stock confirmation.
5. Product Service saves the product in the database and sends a request to **Inventory Service** to update the stock count.
6. Any exceptions are handled via **Spring exception handling**, with custom error messages exposed to the client.

*Diagram suggestion:*  
Client --> API Gateway --> Product Service --> Inventory Service
| ^
|---------------------------|
---

---

## 6. Design Decisions & Rationale

- **Microservices**: Chosen for modularity and independent scaling of product and inventory services.
- **Spring Boot**: Provides rapid development, dependency injection, and resilience patterns.
- **Circuit Breaker**: Ensures system stability when `inventory-service` is unavailable.
- **No caching**: Data consistency is critical, so requests go directly to the database.
- **Docker & Kubernetes**: Simplifies deployment, scaling, and orchestration of services.

---

## 7. Operational Considerations

- **Deployment**: Each service is containerized using Docker and deployed in Kubernetes clusters.
- **Resilience**: Circuit breaker in `product-service` prevents cascading failures when `inventory-service` is down.
- **Error Handling**: Centralized exception handling in Spring Boot provides meaningful error messages to clients.
- **Scalability**: Services can scale independently based on load.

---

## 8. Known Limitations / Trade-offs

- No caching may increase database load under heavy traffic.
- Circuit breaker protects the producer but may result in temporary unavailability of product creation if inventory service fails.
- System depends on synchronous HTTP/TCP calls, which may introduce latency.  
