# 🎯 Project 75 – Order Service – MySQL Order Service | Spring Boot + MySQL 

<p align="left">
<img src="https://img.shields.io/badge/Java-21-E76F00?logo=openjdk&logoColor=white" alt="Java 21">
<img src="https://img.shields.io/badge/Spring%20Boot-4.1.1-6DB33F?logo=springboot&logoColor=white" alt="Spring Boot 4.1.1">
<img src="https://img.shields.io/badge/MySQL-5.5.5%20MariaDB-4479A1?logo=mysql&logoColor=white" alt="MySQL">
<img src="https://img.shields.io/badge/Lombok-NOT_USED-FF0000?logo=lombok&logoColor=white" alt="No Lombok">
<img src="https://img.shields.io/badge/Fix-JsonManagedReference-FF6F00" alt="Infinite Fix 440B">
<img src="https://img.shields.io/badge/Port-8083-00BFFF" alt="8083">
<img src="https://img.shields.io/badge/Status-Completed-20B000" alt="Completed">
</p>

## 📖 Project Overview

Project 75 is Tier 8 – Microservices Order, built with Spring Boot 4.1.1, MySQL 5.5.5 MariaDB, Spring Data JPA, Hibernate 7.4.5.Final, HikariCP and Port 8083.

This project uses **MYSQL MICROSERVICE WITHOUT Lombok**:

- Backend runs on port 8083 – http://localhost:8083/api/orders/test
- MySQL 5.5.5 MariaDB – root@localhost – order_db – orders + order_items tables
- Real MySQL MariaDB Production DB – Tables created via Hibernate update
- Order CRUD with Infinite Recursion Fix – @JsonManagedReference + @JsonBackReference – 13.77KB -> 440B
- 4 Major Fixes – Found 0 JPA -> Found 1 JPA, Eureka 8761 Connection refused -> disabled, Lombok Red X -> Manual Getters/Setters, Infinite JSON 13.77KB -> 440B

Backend endpoints:

- GET /api/orders/test – Order Service is running on port 8083 – demo1.png
- GET /api/orders – Get All Orders – [] empty demo2.png, clean list demo3.png
- POST /api/orders/place – Place Order – userId 1 + Laptop 101 50000 – demo4.png 200 OK 440B
- GET /api/orders/{id} – Get By ID
- PUT /api/orders/{id}/status?status=SHIPPED – Update Status
- DELETE /api/orders/{id} – Delete Order

Verified with 4 screenshots:

- Browser demo1 – localhost:8083/api/orders/test – Order Service is running on port 8083
- Browser demo2 – localhost:8083/api/orders – [] empty
- Browser demo3 – localhost:8083/api/orders – Clean JSON with Laptop order
- Postman demo4 – POST /api/orders/place – 200 OK 440B – Main proof

## ✨ Features

### 📦 Order CRUD – Without Lombok

- Create with userId, orderItems List – Manual Getters/Setters – No @Data – Public Order() {} + getId/setId etc.
- Save via orderRepository.save(order) – Hibernate update – orders table auto created
- orderDate – LocalDateTime.now() – Set in service
- totalAmount – Auto calc price*quantity – 50000 for Laptop
- orderItems – OneToMany mappedBy order cascade ALL – FK order_id
- Value – Laptop 50000 – Real E-Commerce logic

### 📊 Health & Test

- GET /api/orders/test – String running 8083 – demo1.png
- GET /api/orders – [] empty demo2.png – Before order
- GET /api/orders – Clean list demo3.png – After order – No infinite loop
- Verified via log – Tomcat started on port 8083 – HikariPool-1 Added connection – Found 1 JPA repository – Started in 30 seconds

### ➕ Create – Main Proof

- POST /api/orders/place – Body userId 1, orderItems productId 101 Laptop quantity 1 price 50000
- total calc – total += price*quantity – 50000*1=50000
- Order entity – id IDENTITY, userId, orderDate, status PENDING, totalAmount, orderItems @JsonManagedReference
- OrderItem entity – id IDENTITY, productId, productName, quantity, price, order @ManyToOne @JsonBackReference
- Save – setOrderDate now, setStatus PENDING, setOrder FK, save
- Tested – demo4.png – 200 OK 440B – Clean JSON

### 🛡 4 Major Fixes – Completed

- Fix 1 Found 0 JPA -> Found 1 JPA – Package fix + @Repository JpaRepository
- Fix 2 Eureka 8761 Connection refused -> eureka.client.enabled=false
- Fix 3 Lombok Red X -> Manual getters/setters
- Fix 4 Infinite JSON 13.77KB -> 440B -> @JsonManagedReference + @JsonBackReference

## 🛠 Technologies Used

| Technology | Version | Purpose |
|---|---|---|
| Java | 21 | Backend language |
| Spring Boot | 4.1.1 | REST APIs, Tomcat 11, Port 8083 |
| Spring Data JPA | 7.4.5.Final | ORM – Order + OrderItem |
| MySQL | 5.5.5 MariaDB | Real DB – root@localhost – order_db |
| Manual Getters/Setters | No Lombok | Fixes Lombok issue |
| Jackson | @JsonManagedReference | Infinite Fix 13KB -> 440B |
| HikariCP | Default | Connection Pool |
| Maven Wrapper | mvnw.cmd | Build – Fixes mvn not recognized |
| Frontend | Browser + Postman | Verification |

## 📂 Project Structure

```text
75-order-service/
│
├── src/main/java/com/order/service/
│   ├── Application.java – Main – Port 8083
│   ├── controller/OrderController.java – /api/orders – /test, POST /place, GET /, GET /{id}, PUT /status, DELETE
│   ├── service/OrderService.java – placeOrder, getAll, getById, updateStatus, delete
│   ├── model/
│   │   ├── Order.java – @Entity orders – id IDENTITY, userId, orderDate, status PENDING, totalAmount, orderItems @JsonManagedReference – Manual getters/setters
│   │   ├── OrderItem.java – @Entity order_items – productId 101 Laptop, quantity 1, price 50000, order @JsonBackReference
│   │   └── OrderStatus.java – enum PENDING, CONFIRMED, SHIPPED, DELIVERED, CANCELLED
│   └── repository/OrderRepository.java – JpaRepository<Order,Long>
│
├── src/main/resources/
│   └── application.yml – server.port 8083, datasource jdbc:mysql://localhost:3306/order_db, jpa ddl-auto update, eureka.client.enabled false
│
├── screenshots/
│   ├── demo1.png – Browser test – running 8083
│   ├── demo2.png – Browser get all [] empty
│   ├── demo3.png – Browser get all clean with order
│   └── demo4.png – Postman POST 200 OK 440B
│
├── pom.xml – web, data-jpa, mysql-connector-j – No lombok – Java 21
├── mvnw.cmd – Maven wrapper – Fixes mvn not recognized
├── .gitignore
└── README.md
```

## ▶ How to Run

### 1. Clone
```bash
git clone https://github.com/raviteja-dev950/75-Order-Service.git
cd 75-Order-Service
```

### 2. MySQL Setup
```sql
mysql -u root -p
CREATE DATABASE order_db;
SHOW DATABASES;
```

### 3. Application YML
```yaml
server:
  port: 8083
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/order_db?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
    username: root
    password: root
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQLDialect
eureka:
  client:
    enabled: false
    register-with-eureka: false
    fetch-registry: false
```

### 4. Run
```bash
mvnw.cmd clean install -DskipTests
mvnw.cmd spring-boot:run
```

Open:
- http://localhost:8083/api/orders/test – demo1.png
- http://localhost:8083/api/orders – demo2.png [] / demo3.png clean
- POST http://localhost:8083/api/orders/place – demo4.png 200 OK 440B

### 5. Backend Logic

```java
// Order.java – Without Lombok – 440B Fix
@Entity @Table(name="orders")
public class Order {
 @Id @GeneratedValue(strategy=IDENTITY)
 private Long id;
 private Long userId;
 private LocalDateTime orderDate;
 @Enumerated(EnumType.STRING)
 private OrderStatus status;
 private double totalAmount;
 @OneToMany(mappedBy="order", cascade=CascadeType.ALL)
 @JsonManagedReference
 private List<OrderItem> orderItems;
 
 public Long getId(){return id;} public void setId(Long id){this.id=id;}
 public Long getUserId(){return userId;} public void setUserId(Long u){this.userId=u;}
 // ... manual getters/setters
}

// OrderService – placeOrder
public Order placeOrder(Order order){
 order.setOrderDate(LocalDateTime.now());
 order.setStatus(OrderStatus.PENDING);
 double total=0;
 for(OrderItem item: order.getOrderItems()){
   total += item.getPrice()*item.getQuantity();
   item.setOrder(order);
 }
 order.setTotalAmount(total);
 return repo.save(order);
}

// OrderController
@RestController @RequestMapping("/api/orders")
public class OrderController {
 @GetMapping("/test")
 public String test(){ return "Order Service is running on port 8083"; }
 
 @PostMapping("/place")
 public Order place(@RequestBody Order order){ return service.placeOrder(order); }
 
 @GetMapping
 public List<Order> getAll(){ return service.getAllOrders(); }
}
```

## 🔄 Application Flow

```text
Browser / Postman
 │
 ▼
http://localhost:8083/api/orders/test – Test – Running 8083 – demo1.png
 │
 ├── Test – GET /test – String running 8083 – Proves Tomcat + MySQL
 │
 ├── Get All Empty – GET /api/orders – [] – demo2.png – Before order
 │
 ├── Create – POST /api/orders/place – demo4.png – 200 OK 440B
 │   ├── Request userId 1, Laptop 101 50000
 │   ├── total calc 50000*1=50000
 │   └── Response id 2, PENDING, totalAmount 50000 – Clean 440B
 │
 └── Get All Clean – GET /api/orders – demo3.png – Clean JSON – No infinite loop
 │
 ▼
MySQL 5.5.5 – order_db – orders + order_items – FK order_id – Real persistence
```

## 🧪 API Testing

```bash
curl http://localhost:8083/api/orders/test
# Order Service is running on port 8083

curl http://localhost:8083/api/orders
# [] or [{"id":2,...}]

curl -X POST http://localhost:8083/api/orders/place \
-H "Content-Type: application/json" \
-d '{"userId":1,"orderItems":[{"productId":101,"productName":"Laptop","quantity":1,"price":50000}]}'
# 200 OK 440B – Clean JSON

curl http://localhost:8083/api/orders/2
# Single order
```

Postman – demo4.png:

```text
POST http://localhost:8083/api/orders/place
Body: userId 1, orderItems 101 Laptop 1 50000
Response: 200 OK 2.40s 440B – id 2 Laptop PENDING 50000 – Clean
```

## 📡 API Endpoints

| Method | Endpoint | Purpose | Demo |
|---|---|---|---|
| GET | `/api/orders/test` | Health check – Running 8083 | demo1.png |
| GET | `/api/orders` | Get all – [] empty / clean list | demo2.png + demo3.png |
| POST | `/api/orders/place` | Place order – Laptop 50000 – 200 OK 440B | demo4.png – Main |
| GET | `/api/orders/{id}` | Get by ID | - |
| PUT | `/api/orders/{id}/status` | Update status | - |
| DELETE | `/api/orders/{id}` | Delete order | - |

## 🗄 Database Note

MySQL uses IDENTITY – ddl-auto update – Tables auto created – No manual DDL needed.

```sql
USE order_db;
SHOW TABLES; -- orders, order_items
DESC orders; -- id, user_id, order_date, status, total_amount
DESC order_items; -- id, product_id, product_name, quantity, price, order_id FK
SELECT * FROM orders; -- id 2, userId 1, PENDING, 50000
SELECT * FROM order_items; -- id 2, Laptop 101, 50000, order_id 2
SELECT COUNT(*) FROM orders; -- 1
```

### Verified

- /api/orders/test – String running 8083 – demo1.png – Tomcat 8083 + MySQL
- /api/orders – [] – demo2.png – Empty before order
- /api/orders – [{"id":2,...}] clean – demo3.png – No infinite – 440B
- POST /api/orders/place – 200 OK 440B – demo4.png – Main proof

## 📸 Screenshots – 4 Demos – Completed

### 1. Test – Browser – localhost:8083/api/orders/test – Running 8083

[Test](screenshots/demo1.png)

---

### 2. Get All Empty – Browser – [] – Before Order

[Empty](screenshots/demo2.png)

---

### 3. Get All Clean – Browser – [{"id":2,...}] – After Fix

[Clean](screenshots/demo3.png)

---

### 4. POST Success – Postman – 200 OK 440B – Main Proof

[POST Success](screenshots/demo4.png)

---

## 🎯 Learning Outcomes

- MySQL Microservice Without Lombok – Real DB not H2 – root@localhost – order_db
- 4 Major Fixes – Found 0 JPA -> Found 1 JPA, Eureka 8761 -> disabled, Lombok Red X -> Manual getters/setters, Infinite 13KB -> 440B via @JsonManagedReference
- Without Lombok – Manual Product() + getId/setId – Fixes Eclipse Lombok issue – Same as 74 pattern
- JPA Bidirectional – @OneToMany + @ManyToOne – Cascade ALL – FK order_id – Real E-Commerce
- Hibernate DDL Auto Update – create table orders + order_items – No manual CREATE TABLE
- Total Amount Calculation – Backend calc price*quantity – Prevents tampering
- OrderService – placeOrder, getAll, getById, updateStatus, delete – Main business logic
- OrderController – 6 endpoints – Same pattern as 74 ProductController
- Postman Testing – POST 200 OK 440B – Before 13KB infinite, after 440B clean
- Browser Testing – 3 browsers – test running, empty [], clean list
- MySQL Verification – SHOW TABLES, SELECT * FROM orders, SELECT * FROM order_items
- Tier 8 Microservices – Third service – Port 8083 – Auth 8081 + Product 8082 + Order 8083 + Gateway 8080 + Discovery 8761
- Real App Proof – 4 screenshots – Not dummy – MySQL persistence

## 🚀 Future Enhancements

- Add JWT filter – Validate token from 73-auth
- Add Feign Client – Call product-service for price check
- Add Eureka Discovery – Register to 8761
- Add API Gateway – Route /api/orders/** via Gateway 8080
- Add payment integration – Razorpay / Stripe – PENDING -> CONFIRMED
- Add Docker – MySQL + Order Service + Gateway + Discovery
- Deploy to Render/Railway – Environment variables
- Add React frontend – Orders page – Table with status badge
- Add unit tests – Test total calc, Test infinite fix 440B
- Add Swagger OpenAPI – /swagger-ui.html

## 👨💻 Author

### Vemula Leela Venkata Ravi Teja

Java Full Stack Developer

100 Java Full Stack Projects Challenge

Project 75 / 100 – MySQL Track – Order Service Without Lombok – Infinite Fix 440B – 4 Demos Completed

Tier 8 – Microservices – Port 8083 – Third of 5

### Test Order – MySQL Persisted

- `Laptop` / `101` / `50000` – ID 2 – Status PENDING – Total 50000 – UserId 1 – MySQL 5.5.5 – order_db – Without Lombok – 200 OK 440B

## ⭐ Support

If you found this project helpful fixing JPA/Eureka/Lombok/Infinite JSON errors, give it a ⭐ Star!

### Repo

https://github.com/raviteja-dev950/75-Order-Service

### Run

```bash
mvnw.cmd spring-boot:run
```

Open:

```text
http://localhost:8083/api/orders/test – Running 8083 – demo1.png
http://localhost:8083/api/orders – [] empty demo2.png / clean demo3.png
POST http://localhost:8083/api/orders/place – 200 OK 440B demo4.png
```
