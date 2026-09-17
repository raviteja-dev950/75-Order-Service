# 🎯 Project 75 – Order Service – MySQL Order Service | Spring Boot + MySQL 

<p align="left">
<img src="https://img.shields.io/badge/Java-21-E76F00?logo=openjdk&logoColor=white" alt="Java 21">
<img src="https://img.shields.io/badge/Spring%20Boot-4.1.1-6DB33F?logo=springboot&logoColor=white" alt="Spring Boot 4.1.1">
<img src="https://img.shields.io/badge/MySQL-5.5.5%20MariaDB-4479A1?logo=mysql&logoColor=white" alt="MySQL">
<img src="https://img.shields.io/badge/Lombok-NOT_USED-FF0000?logo=lombok&logoColor=white" alt="No Lombok">
<img src="https://img.shields.io/badge/Fix-JsonManagedReference-FF6F00" alt="Infinite Loop Fix 440B">
<img src="https://img.shields.io/badge/Port-8083-00BFFF" alt="8083">
<img src="https://img.shields.io/badge/Status-Completed-20B000" alt="Completed">
</p>

## 📖 Project Overview

Project 75 is Tier 8 – Microservices Order, built with Spring Boot 4.1.1, MySQL 5.5.5 MariaDB, Spring Data JPA, Hibernate 7.4.5.Final, HikariCP and Port 8083.

This project uses **MYSQL MICROSERVICE WITHOUT Lombok**:

- Backend runs on port 8083 – http://localhost:8083/api/orders/test – demo1.png
- MySQL 5.5.5 – root@localhost – order_db – orders + order_items tables with FK order_id – demo2.png [] empty before order
- Real MySQL MariaDB Production DB – Tables created via Hibernate update – demo3.png clean JSON id 2 Laptop PENDING 50000
- Order CRUD with Infinite Recursion Fix – @JsonManagedReference + @JsonBackReference + Manual Getters/Setters – 13.77KB -> 440B – demo4.png 200 OK 2.40s 440B
- 4 Major Fixes Completed – Found 0 JPA -> Found 1 JPA, Eureka 8761 Connection refused -> eureka.client.enabled=false, Lombok Red X -> Manual Getters/Setters, Infinite JSON 13.77KB -> 440B @JsonManagedReference

Backend endpoints:

- GET /api/orders/test – Order Service is running on port 8083 – demo1.png – WITHOUT Lombok
- GET /api/orders – Get All – [] empty demo2.png before, [{"id":2,...}] demo3.png after fix clean
- POST /api/orders/place – Place Order – userId 1 + orderItems productId 101 Laptop quantity 1 price 50000 – demo4.png 200 OK 440B
- GET /api/orders/{id} – Get By ID – For tracking
- PUT /api/orders/{id}/status?status=SHIPPED – Update Status
- DELETE /api/orders/{id} – Delete Order – Cascade deletes items

Verified with 4 screenshots:

- Browser demo1 – localhost:8083/api/orders/test – Order Service is running on port 8083 – White page – Tomcat 8083
- Browser demo2 – localhost:8083/api/orders – [] – Empty before order – Clean DB
- Browser demo3 – localhost:8083/api/orders – [{"id":2,"orderDate":"2026-09-17T18:48:30","orderItems":[{"id":2,"price":50000.0,"productId":101,"productName":"Laptop","quantity":1}],"status":"PENDING","totalAmount":50000.0,"userId":1}] – Clean after fix
- Postman demo4 – POST http://localhost:8083/api/orders/place – 200 OK 2.40s 440B – Laptop PENDING 50000 – Main proof

## ✨ Features

### 📦 Order CRUD – Without Lombok – 4 Demos Proof

- Create with userId, orderItems List – Manual Getters/Setters – No @Data – Public Order() {} + getId/setId etc. – Same as Project 74 Without Lombok pattern
- Save via orderRepository.save(order) – Hibernate update – orders table id BIGINT auto_increment – demo2 [] to demo3 [{"id":2,...}] proof
- orderDate – LocalDateTime.now() – 2026-09-17T18:48:30 – demo3 orderDate
- totalAmount – Auto calc – price*quantity sum – 50000.0 for Laptop – demo4 totalAmount 50000.0
- orderItems – OneToMany mappedBy order cascade ALL @JsonManagedReference – FK order_id – demo3 orderItems id 2 Laptop
- Value – Laptop 50000 – Real E-Commerce order logic

### 📊 Health & Test – demo1.png + demo2.png + demo3.png

- GET /api/orders/test – String running 8083 – demo1.png – Order Service is running on port 8083 – White page – First proof
- GET /api/orders – [] empty – demo2.png – Pretty-print [] – Before order – Clean DB – Empty set proof
- GET /api/orders – [{"id":2,...}] clean – demo3.png – [{"id":2,"orderDate":"2026-09-17T18:48:30","orderItems":[{"id":2,"price":50000.0,"productId":101,"productName":"Laptop","quantity":1}],"status":"PENDING","totalAmount":50000.0,"userId":1}] – After fix clean – No infinite loop – 440B – Working proof

### ➕ Create – demo4.png – Main Proof

- POST /api/orders/place – Body userId 1, orderItems [productId 101 Laptop quantity 1 price 50000] – demo4.png
- totalAmount calc – Service – total += price*quantity – 50000*1=50000 – demo4 totalAmount 50000.0 proof
- Order entity – id IDENTITY Long, userId Long, orderDate LocalDateTime, status PENDING enum, totalAmount double, orderItems @OneToMany @JsonManagedReference – Manual getters/setters
- OrderItem – id IDENTITY, productId 101, productName Laptop, quantity 1, price 50000, order @ManyToOne @JsonBackReference – Breaks infinite loop – 13.77KB -> 440B
- Save – setOrderDate now, setStatus PENDING, setOrder for each item, save – Hibernate insert into orders + order_items
- Tested – demo4.png – POST 200 OK 2.40s 440B – Request 1-8 lines userId 1 orderItems 101 Laptop 1 50000 – Response id 2 price 50000 productId 101 Laptop quantity 1 status PENDING totalAmount 50000 userId 1 – Clean 440B – Before fix 13.77KB infinite

### 🛡 4 Major Fixes – Completed – 75/100

- Fix 1 Found 0 JPA -> Found 1 JPA – Package com.order.service.repository wrong -> Correct package + @Repository JpaRepository<Order,Long> – Now Found 1 JPA repository interface – 535ms scanning – Log proof
- Fix 2 Eureka 8761 Connection refused -> eureka.client.enabled=false + register-with-eureka false + fetch-registry false – Log clean – Standalone MySQL service – No DiscoveryClient error
- Fix 3 Lombok Red X -> Manual getters/setters – Order.java + OrderItem.java – getId/setId getUserId/setUserId getOrderDate/setOrderDate getStatus/setStatus getTotalAmount/setTotalAmount getOrderItems/setOrderItems getProductId/setProductId etc. – All red X gone – Same as 74 Without Lombok
- Fix 4 Infinite JSON 13.77KB -> 440B -> Order @JsonManagedReference + OrderItem @JsonBackReference – import com.fasterxml.jackson.annotation.JsonManagedReference / JsonBackReference – Response clean 440B – demo4 Size 440B – No order inside orderItems – Before infinite [{"id":1,"order":{"id":1... – After clean demo3 [{"id":2,...}] – demo4 440B

## 🛠 Technologies Used

| Technology | Version | Purpose |
|---|---|---|
| Java | 21.0.10 | Backend language |
| Spring Boot | 4.1.1 | REST APIs, Tomcat 11.0.24, Port 8083 – demo1 running |
| Spring Data JPA / Hibernate | 7.4.5.Final | ORM – Order + OrderItem – demo3 proof |
| MySQL / MariaDB | 5.5.5-10.4.32-MariaDB | Real DB – root@localhost – order_db – 3306 – demo2 [] before, demo3 1 order after |
| Manual Getters/Setters | No Lombok – Pure Java | Fixes Lombok red X – Same as 74 – demo4 440B working |
| Jackson | @JsonManagedReference + @JsonBackReference | Infinite Loop Fix – 13.77KB -> 440B – demo4 440B proof |
| HikariCP | Default | Connection Pool – Added connection – Start completed |
| Maven Wrapper | mvnw.cmd | Build – mvnw.cmd spring-boot:run – mysql-connector-j |
| Frontend | Browser + Postman | Chrome demo1 test, demo2 [] empty, demo3 clean, Postman demo4 POST 200 OK 440B |

## 📂 Project Structure

```text
75-order-service/
│
├── src/main/java/com/order/service/
│   ├── Application.java – @SpringBootApplication – Main – Port 8083
│   ├── controller/OrderController.java – @RestController /api/orders – /test String running 8083 demo1, POST /place demo4 440B, GET / demo2 [] demo3 [{"id":2,...}], GET /{id}, PUT /{id}/status, DELETE /{id}
│   ├── service/OrderService.java – @Service – placeOrder setOrderDate now PENDING calc total 50000 setOrder FK save, getAll findAll demo2 demo3, getById, updateStatus, delete
│   ├── model/
│   │   ├── Order.java – @Entity orders – id IDENTITY Long, userId 1, orderDate 2026-09-17T18:48:30 demo3, status PENDING demo3/4, totalAmount 50000 demo3/4, orderItems @OneToMany @JsonManagedReference – Manual getters/setters – 440B fix
│   │   ├── OrderItem.java – @Entity order_items – id 2, productId 101 Laptop, productName Laptop, quantity 1, price 50000, order_id FK 2 @ManyToOne @JsonBackReference – Manual getters/setters
│   │   └── OrderStatus.java – enum PENDING, CONFIRMED, SHIPPED, DELIVERED, CANCELLED
│   └── repository/OrderRepository.java – @Repository JpaRepository<Order,Long> – Found 1 JPA fix
│
├── src/main/resources/application.yml – server.port 8083, datasource url jdbc:mysql://localhost:3306/order_db?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC username root password root driver com.mysql.cj.jdbc.Driver, jpa ddl-auto update show-sql true dialect MySQLDialect, eureka.client.enabled false register-with-eureka false fetch-registry false – Fixes 8761
│
├── screenshots/ – 4 current demos – Final
│   ├── demo1.png – Browser test – localhost:8083/api/orders/test – Order Service is running on port 8083 – White page
│   ├── demo2.png – Browser get all empty – localhost:8083/api/orders – [] – Pretty-print [] – Before order – Empty set
│   ├── demo3.png – Browser get all clean – localhost:8083/api/orders – [{"id":2,"orderDate":"2026-09-17T18:48:30","orderItems":[{"id":2,"price":50000.0,"productId":101,"productName":"Laptop","quantity":1}],"status":"PENDING","totalAmount":50000.0,"userId":1}] – After fix clean 440B
│   └── demo4.png – Postman POST place – POST http://localhost:8083/api/orders/place – Body userId 1 orderItems 101 Laptop 1 50000 – 200 OK 2.40s 440B – Response id 2 Laptop PENDING 50000 – Main proof
│
├── pom.xml – spring-boot-starter-web, data-jpa, mysql-connector-j, jackson – No lombok – Java 21
├── mvnw.cmd – Maven wrapper – mvnw.cmd spring-boot:run – Fixes 'mvn not recognized' error – Use wrapper
├── .gitignore – target/, .idea/, *.log
└── README.md – This file – 4 demos final
```

## ▶ How to Run – Final – 4 Demos

### 1. Clone
```bash
git clone https://github.com/raviteja-dev950/75-Order-Service.git
cd 75-Order-Service
```

### 2. MySQL Setup
```sql
mysql -u root -p
CREATE DATABASE IF NOT EXISTS order_db;
USE order_db;
SHOW TABLES; -- Before run Empty set, after run orders + order_items – 2 tables – demo2 [] proves empty
SELECT * FROM orders; -- After POST – id 2 Laptop PENDING 50000 – demo3 proof
```

### 3. Application YML – Current
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
app:
  name: Order Service - 75
```

### 4. Run – Use Wrapper – Fixes 'mvn not recognized'
```bash
# If mvn not recognized error, use wrapper:
mvnw.cmd clean install -DskipTests
mvnw.cmd spring-boot:run

# OR if Maven in PATH:
mvn clean install -DskipTests
mvn spring-boot:run
```

Open – Your final 4 demos:

- http://localhost:8083/api/orders/test – demo1.png – Order Service is running on port 8083 – White page – First proof
- http://localhost:8083/api/orders – [] empty – demo2.png – Empty set – Before POST – Clean DB
- http://localhost:8083/api/orders – [{"id":2,...}] clean – demo3.png – After fix 440B – No infinite – Working – Laptop order
- POST http://localhost:8083/api/orders/place – demo4.png – 200 OK 2.40s 440B – Laptop PENDING 50000 – Main proof

### 5. Backend Logic – Final – 4 Demos

```java
// Order.java – Without Lombok – 440B fix – demo3 + demo4 proof
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
 // Manual getters/setters – No Lombok – Fixes red X
 public Long getId(){return id;} public void setId(Long id){this.id=id;}
 public Long getUserId(){return userId;} public void setUserId(Long userId){this.userId=userId;}
 public LocalDateTime getOrderDate(){return orderDate;} public void setOrderDate(LocalDateTime d){this.orderDate=d;}
 public OrderStatus getStatus(){return status;} public void setStatus(OrderStatus s){this.status=s;}
 public double getTotalAmount(){return totalAmount;} public void setTotalAmount(double t){this.totalAmount=t;}
 public List<OrderItem> getOrderItems(){return orderItems;} public void setOrderItems(List<OrderItem> list){this.orderItems=list;}
}

// OrderItem.java – @JsonBackReference – 13KB -> 440B – demo4 440B
@Entity @Table(name="order_items")
public class OrderItem {
 @Id @GeneratedValue(strategy=IDENTITY)
 private Long id;
 private Long productId; // 101
 private String productName; // Laptop
 private int quantity; // 1
 private double price; // 50000
 @ManyToOne @JoinColumn(name="order_id")
 @JsonBackReference
 private Order order;
 // Manual getters/setters
 public Long getId(){return id;} public void setId(Long id){this.id=id;}
 public Long getProductId(){return productId;} public void setProductId(Long p){this.productId=p;}
 public String getProductName(){return productName;} public void setProductName(String n){this.productName=n;}
 public int getQuantity(){return quantity;} public void setQuantity(int q){this.quantity=q;}
 public double getPrice(){return price;} public void setPrice(double p){this.price=p;}
 public Order getOrder(){return order;} public void setOrder(Order o){this.order=o;}
}

// OrderService – placeOrder – demo4 total 50000
@Service
public class OrderService {
 @Autowired private OrderRepository repo;
 public Order placeOrder(Order order){
   order.setOrderDate(LocalDateTime.now());
   order.setStatus(OrderStatus.PENDING);
   double total=0;
   for(OrderItem item: order.getOrderItems()){
     total += item.getPrice()*item.getQuantity();
     item.setOrder(order);
   }
   order.setTotalAmount(total); // 50000*1=50000 – demo4 proof
   return repo.save(order);
 }
 public List<Order> getAllOrders(){ return repo.findAll(); } // demo2 [] demo3 [{"id":2,...}]
}

// OrderController – 4 demos endpoints
@RestController @RequestMapping("/api/orders")
public class OrderController {
 @GetMapping("/test")
 public String test(){ return "Order Service is running on port 8083"; } // demo1.png
 @PostMapping("/place")
 public Order place(@RequestBody Order order){ return service.placeOrder(order); } // demo4.png 200 OK 440B
 @GetMapping
 public List<Order> getAll(){ return service.getAllOrders(); } // demo2 [] demo3 [{"id":2,...}]
}
```

## 🔄 Application Flow – 4 Demos Final

```text
Browser / Postman – 4 screenshots final
 │
 ▼
http://localhost:8083/api/orders/test – demo1.png – Order Service is running on port 8083 – White page – Tomcat 8083
 │
 ├── Test – GET /test – demo1.png – Order Service is running on port 8083 – First proof – Tomcat 8083 + MySQL connected – WITHOUT Lombok
 │
 ├── Get All Empty – GET /api/orders – demo2.png – [] – Pretty-print [] – Before order – Empty set proof – 0 orders – Clean DB – Before POST
 │
 ├── Create – POST /api/orders/place – demo4.png – Main proof – 200 OK 2.40s 440B
 │   ├── Request – userId 1, orderItems productId 101 Laptop quantity 1 price 50000 – Raw JSON 1-8 lines – demo4 top
 │   ├── total calc – 50000*1=50000 – totalAmount 50000.0 – demo4 bottom status PENDING totalAmount 50000
 │   ├── Save – setOrderDate now, setStatus PENDING, setOrder FK, save – insert into orders + order_items – Hibernate
 │   └── Response – 200 OK 2.40s 440B – {"id":2,"orderItems":[{"id":2,"price":50000.0,"productId":101,"productName":"Laptop","quantity":1}],"status":"PENDING","totalAmount":50000.0,"userId":1} – Clean 440B – No order inside orderItems – Before fix would be 13.77KB infinite – After fix 440B – demo4 main proof
 │
 └── Get All Clean – GET /api/orders – demo3.png – [{"id":2,"orderDate":"2026-09-17T18:48:30","orderItems":[{"id":2,"price":50000.0,"productId":101,"productName":"Laptop","quantity":1}],"status":"PENDING","totalAmount":50000.0,"userId":1}] – After POST – Clean JSON – No infinite – 440B – Working – Laptop order – Real MySQL persistence – Not H2
 │
 ▼
MySQL 5.5.5-MariaDB – order_db – orders id 2 userId 1 date 2026-09-17 PENDING total 50000 – order_items id 2 productId 101 Laptop quantity 1 price 50000 order_id FK 2 – demo3 proof – Data persists – Not H2 mem wipe – demo2 [] empty before, demo3 1 order after
```

## 🧪 API Testing – Final 4 Demos

```bash
# demo1.png – Test – Running 8083
curl http://localhost:8083/api/orders/test
# Order Service is running on port 8083 – demo1.png – White page

# demo2.png – Get All Empty – []
curl http://localhost:8083/api/orders
# [] – demo2.png – Empty set – Before order

# demo4.png – Place Order – 200 OK 440B – Main
curl -X POST http://localhost:8083/api/orders/place -H "Content-Type: application/json" -d "{"userId":1,"orderItems":[{"productId":101,"productName":"Laptop","quantity":1,"price":50000}]}"
# 200 OK 2.40s 440B – {"id":2,"orderItems":[{"id":2,"price":50000.0,"productId":101,"productName":"Laptop","quantity":1}],"status":"PENDING","totalAmount":50000.0,"userId":1} – demo4.png – Clean 440B

# demo3.png – Get All Clean – [{"id":2,...}] – After POST
curl http://localhost:8083/api/orders
# [{"id":2,"orderDate":"2026-09-17T18:48:30","orderItems":[{"id":2,"price":50000.0,"productId":101,"productName":"Laptop","quantity":1}],"status":"PENDING","totalAmount":50000.0,"userId":1}] – demo3.png – Clean – No infinite
```

Postman – demo4.png – Your main proof:

```text
POST http://localhost:8083/api/orders/place – Body userId 1 orderItems 101 Laptop 1 50000 – Request 1-8 lines – Response Status 200 OK Time 2.40s Size 440B – Body id 2 orderItems id 2 price 50000 productId 101 Laptop quantity 1 status PENDING totalAmount 50000 userId 1 – Clean 440B – Main proof – demo4.png – Before fix 13.77KB infinite, after fix 440B clean
```

Browser – demo1, demo2, demo3 – Your final 3 browser proofs:

```text
GET http://localhost:8083/api/orders/test – demo1.png – Order Service is running on port 8083 – White page – Tomcat 8083

GET http://localhost:8083/api/orders – demo2.png – [] – Empty – Before order – Clean DB – 0 orders

GET http://localhost:8083/api/orders – demo3.png – [{"id":2,"orderDate":"2026-09-17T18:48:30","orderItems":[{"id":2,"price":50000.0,"productId":101,"productName":"Laptop","quantity":1}],"status":"PENDING","totalAmount":50000.0,"userId":1}] – Clean after fix – 440B – After POST – Laptop order – Real MySQL
```

## 📡 API Endpoints – Final 4 Demos

| Method | Endpoint | Purpose | Your Demo |
|---|---|---|---|
| GET | `/api/orders/test` | Health – String running 8083 – WITHOUT Lombok – Tomcat + MySQL proof | demo1.png – Order Service is running on port 8083 – White page – First proof |
| GET | `/api/orders` | Get All – [] empty before POST, [{"id":2,...}] clean after POST 440B – No infinite – Frontend orders page | demo2.png [] empty – Before order + demo3.png [{"id":2,...}] clean – After order – 2 proofs |
| POST | `/api/orders/place` | Place Order – userId + orderItems 101 Laptop 1 50000 – Auto calc total 50000 – Returns id 2 – 200 OK 440B – Main CRUD | demo4.png – 200 OK 2.40s 440B Laptop PENDING 50000 – Main proof – Clean JSON |
| GET | `/api/orders/{id}` | Get By ID – Optional – 200 or 404 – For tracking – /api/orders/2 | Same as demo3 but single object |
| PUT | `/api/orders/{id}/status` | Update Status – PENDING/CONFIRMED/SHIPPED/DELIVERED/CANCELLED | For shipment tracking |
| DELETE | `/api/orders/{id}` | Delete – Cascade deletes items – Clean DB | For admin |

## 🗄 Database Note – MySQL 5.5.5 – Final 4 Demos Proof

MySQL uses IDENTITY – ddl-auto update – Tables auto created – No manual DDL – createDatabaseIfNotExist=true – id IDENTITY – demo2 [] empty before proves tables exist but 0 rows, demo3 [{"id":2,...}] after proves data persisted – Not H2 mem wipe.

### Tables – demo2, demo3, demo4 proof

```text
orders – id BIGINT auto_increment primary key 2 demo3 – userId 1 demo3/4, order_date DATETIME 2026-09-17T18:48:30 demo3, status VARCHAR PENDING demo3/4, total_amount DOUBLE 50000 demo3/4 auto calc, orderItems @OneToMany cascade ALL @JsonManagedReference – Without Lombok – demo2 [] 0 rows, demo3 1 row

order_items – id BIGINT auto_increment 2 demo3/4, product_id BIGINT 101 Laptop, product_name VARCHAR Laptop, productName Laptop demo3/4, quantity INT 1 demo3/4, price DOUBLE 50000 demo3/4, order_id BIGINT FK 2 to orders id 2 @ManyToOne @JsonBackReference – Fixes infinite loop 13KB->440B – demo3 orderItems id 2 price 50000 productId 101 Laptop quantity 1

order_db – database – root@localhost – 3306 – MySQL 5.5.5-10.4.32-MariaDB – createDatabaseIfNotExist=true – Real production – demo2 [] empty proves tables exist, demo3 1 order proves data persisted – Not H2 mem wipe – POST via Postman demo4

Data persists on restart – Not H2 mem wipe – Real production – demo3 proof.

SQL Verification – Final 4 demos:

mysql -u root -p
USE order_db;
SHOW TABLES; -- orders, order_items – 2 tables – Before Empty set – After 2 tables – demo2 [] proves tables exist but empty
SELECT * FROM orders; -- 2 | 1 | 2026-09-17 18:48:30 | PENDING | 50000 | 1 – Real data – Laptop order – demo3 id 2 orderDate 2026-09-17T18:48:30 status PENDING totalAmount 50000 userId 1
SELECT * FROM order_items; -- 2 | 50000 | 101 | Laptop | 1 | 2 – FK 2 to orders 2 – demo3 orderItems id 2 price 50000 productId 101 productName Laptop quantity 1
SELECT COUNT(*) FROM orders; -- 1 – Count – After POST demo3 – Before POST demo2 [] count 0
```

### Verified – Final 4 Demos – Completed – 75/100

- demo1.png – /api/orders/test – Order Service is running on port 8083 – Browser – White page – Tomcat 8083 + MySQL connected – Simple String health – First proof – Running 8083 – WITHOUT Lombok – MySQL 5.5.5
- demo2.png – /api/orders – [] – Pretty-print [] – Browser – Before POST – Empty set proof – 0 orders – Clean DB – No data – Tables exist but 0 rows – Before order placement – Empty array – Second proof
- demo3.png – /api/orders – [{"id":2,"orderDate":"2026-09-17T18:48:30","orderItems":[{"id":2,"price":50000.0,"productId":101,"productName":"Laptop","quantity":1}],"status":"PENDING","totalAmount":50000.0,"userId":1}] – Browser – After POST – Clean JSON – No infinite loop – No order inside orderItems – 440B not 13KB – After fix proof – Working – Laptop order – Real MySQL persistence – Not H2 – Single order list – Third proof
- demo4.png – POST /api/orders/place – Request userId 1 orderItems productId 101 Laptop quantity 1 price 50000 – 1-8 lines raw JSON – Response Status 200 OK Time 2.40s Size 440B – Body Pretty JSON id 2 orderItems id 2 price 50000 productId 101 Laptop quantity 1 status PENDING totalAmount 50000 userId 1 – Postman Lightweight API Client – Main CRUD proof – 440B clean – Without Lombok manual getters/setters working – Infinite loop fixed via @JsonManagedReference 13KB->440B – Total calc 50000*1=50000 – Fourth proof – Main proof – Laptop order – 75/100 Completed

## 📸 Screenshots – Final – 4 Demos – 75-Order-Service Completed – 75/100

### 1. Test – Browser – localhost:8083/api/orders/test – Running 8083 – White Page – demo1.png

[Browser Test](screenshots/demo1.png)
- demo1.png – Order Service is running on port 8083 – First proof – Tomcat 8083 + MySQL connected – White page – WITHOUT Lombok – MySQL 5.5.5

---

### 2. Get All Empty – Browser – localhost:8083/api/orders – [] – Before Order – demo2.png

[Get All Empty](screenshots/demo2.png)
- demo2.png – [] – Pretty-print [] – Empty set – Before POST – Clean DB – No data – 0 orders – Tables exist but empty – Second proof

---

### 3. Get All Clean – Browser – localhost:8083/api/orders – [{"id":2,...}] – After Fix No Loop – demo3.png

[Get All Clean](screenshots/demo3.png)
- demo3.png – [{"id":2,"orderDate":"2026-09-17T18:48:30","orderItems":[{"id":2,"price":50000.0,"productId":101,"productName":"Laptop","quantity":1}],"status":"PENDING","totalAmount":50000.0,"userId":1}] – Clean JSON – No infinite – 440B – After POST – Working – Laptop order – Real MySQL – Third proof

---

### 4. POST Place Order Success – Postman – 200 OK 440B – Laptop PENDING 50000 – demo4.png – Main Proof – Final

[POST Success](screenshots/demo4.png)
- demo4.png – POST http://localhost:8083/api/orders/place – Request userId 1 orderItems productId 101 Laptop quantity 1 price 50000 – 1-8 lines – Response Status 200 OK Time 2.40s Size 440B – Body id 2 orderItems id 2 price 50000 productId 101 Laptop quantity 1 status PENDING totalAmount 50000 userId 1 – Clean 440B – Before fix 13.77KB infinite, after fix 440B – Main proof – 75/100 Completed

---

## 🎯 Learning Outcomes – Final – 4 Demos – 75/100 Completed

- MySQL 5.5.5 MariaDB Microservice Without Lombok – Real DB not H2 mem wipe – root@localhost – order_db – jdbc:mysql://localhost:3306/order_db?createDatabaseIfNotExist=true – mysql-connector-j 8.x – HikariPool-1 Added connection – Production persistence – demo1 running 8083 proves Tomcat + MySQL connected, demo2 [] proves tables exist but empty, demo3 [{"id":2,...}] proves data persisted, demo4 200 OK 440B proves POST working – Final 4 demos
- 4 Major MySQL Fixes – Found 0 JPA -> Found 1 JPA – Correct package com.order.service.repository @Repository JpaRepository<Order,Long> – Found 1 JPA 535ms – Eureka 8761 TransportException Cannot execute request on any known server -> eureka.client.enabled=false + register-with-eureka false + fetch-registry false – Log clean – Standalone – Lombok Red X setOrderDate setStatus getOrderItems setOrder getPrice getQuantity -> Manual getters/setters Order.java OrderItem.java – Fixes all red X – Same as 74 Without Lombok – demo4 440B working proves manual working – Infinite JSON 13.77KB -> 440B -> Order @JsonManagedReference + OrderItem @JsonBackReference – import com.fasterxml.jackson.annotation.JsonManagedReference / JsonBackReference – Response clean 440B demo4 Size 440B – No order inside orderItems – Fixes StackOverflowError – demo3 clean [{"id":2,...}] no infinite vs before infinite [{"id":1,"order":{"id":1... – Final 4 demos proof
- Without Lombok – No @Data @Getter @Setter – Manual Order() constructor + getId/setId getUserId/setUserId getOrderDate/setOrderDate getStatus/setStatus getTotalAmount/setTotalAmount getOrderItems/setOrderItems + OrderItem getId/setId getProductId/setProductId getProductName/setProductName getQuantity/setQuantity getPrice/setPrice getOrder/setOrder – Fixes Lombok annotation processing not working in Eclipse – Same as 74 pattern – Real enterprise pattern – Explicit code – No magic – demo4 440B proves manual working – Final
- Spring Data JPA Bidirectional @OneToMany + @ManyToOne – Order @OneToMany mappedBy order cascade ALL @JsonManagedReference List<OrderItem> – OrderItem @ManyToOne @JoinColumn name order_id @JsonBackReference Order order – FK order_id BIGINT to orders id 2 – Cascade ALL saves items when order saved – For each item setOrder(order) – Hibernate update – Real E-Commerce order_items table – Real foreign key – demo3 orderItems id 2 order_id FK 2 proves FK working – demo4 same – Final 4 demos
- Hibernate DDL Auto Update – ddl-auto update – orders table auto created – create table orders + create table order_items – No manual CREATE TABLE – id IDENTITY – total_amount auto calc 50000*1=50000 demo4 totalAmount 50000 – orderDate LocalDateTime.now 2026-09-17T18:48:30 demo3 orderDate – status PENDING enum STRING demo3/4 PENDING – createDatabaseIfNotExist=true – demo2 [] empty before shows auto creation working, demo3 1 order after shows data – Final
- Total Amount Calculation – Service placeOrder – double total = 0; for(OrderItem item: order.getOrderItems()) total += item.getPrice()*item.getQuantity(); order.setTotalAmount(total); – Auto calc 50000*1=50000 – No frontend total trust – Backend calc – Prevents price tampering – demo4 totalAmount 50000.0 proves auto calc working – Request price 50000 quantity 1 -> total 50000 – Final
- OrderService – @Service – @Autowired OrderRepository – placeOrder setOrderDate now PENDING calc total setOrder FK save, getAllOrders findAll demo2 [] demo3 [{"id":2,...}], getOrderById, updateOrderStatus, deleteOrder deleteById cascade – Main business logic – Without Lombok – Same as 74 ProductService but total calc – Final
- OrderController – @RestController @RequestMapping /api/orders @CrossOrigin * – 6 endpoints test String running 8083 demo1, place POST /place demo4 200 OK 440B, getAll GET List demo2 [] demo3 [{"id":2,...}], getById, updateStatus, delete – ResponseEntity.ok() – HashMap – Same pattern as 74 ProductController but String test – Simple String health – 4 demos cover test + getAll empty + getAll clean + place – Final
- Postman Testing – POST http://localhost:8083/api/orders/place – Body Laptop 50000 – Before fix 200 OK 13.77KB infinite – After fix 200 OK 2.40s 440B – demo4.png – Pretty JSON id 2 orderItems id 2 price 50000 productId 101 Laptop quantity 1 status PENDING totalAmount 50000 userId 1 – Main proof – Without Lombok manual getters/setters working – Infinite loop fixed – Size proof 13.77KB -> 440B – demo4 is after fix clean – 2.40s 440B – Final 4 demos main proof
- Browser Testing – 3 browsers – test String running 8083 demo1 white page Order Service is running on port 8083, empty [] demo2 before order Empty set, clean list demo3 [{"id":2,...}] after fix 440B after POST – 3 browser screenshots final – Real microservice verification – No Postman needed for GET – Same as 74 browser proofs but String test – demo1 white page, demo2 [], demo3 [{"id":2,...}] – 3 browser proofs final
- MySQL Verification – mysql -u root – USE order_db; SHOW TABLES; – orders, order_items – Before Empty set – After 2 tables – DESC orders – id bigint auto_increment, user_id bigint, order_date datetime, status varchar, total_amount double – DESC order_items – id bigint, price double, product_id bigint, product_name varchar, quantity int, order_id bigint FK – SELECT * FROM orders – 2 1 2026-09-17 18:48:30 PENDING 50000 1 – demo3.json id 2 orderDate 2026-09-17T18:48:30 status PENDING totalAmount 50000 userId 1 – SELECT * FROM order_items – 2 50000 101 Laptop 1 2 – demo3 orderItems id 2 price 50000 productId 101 Laptop quantity 1 – Real data – Final 4 demos proof – demo2 [] proves clean after DELETE, demo3 1 order proves data persisted
- Tier 8 Microservices – Third order microservice – Port 8083 – First 73-auth 8081 JWT, Second 74-product 8082 Oracle 11g XE, Third 75-order 8083 MySQL 5.5.5 this project Without Lombok Infinite Fix 440B – Final 4 demos, Fourth 76-Gateway 8080 JWT filter + routes, Fifth 77-Discovery 8761 Eureka – 75 uses Feign to call 74-product for price check future – Real microservices chain – Single JAR – No Docker yet – Same 100 Projects flow – 75/100 – 4 demos prove standalone MySQL microservice working without Discovery – Final
- Real App Proof – Not dummy – Final 4 screenshots – Browser test demo1 running 8083 white page Order Service is running on port 8083 – First proof, Browser empty [] demo2 before order Empty set – Second proof – Clean DB, Browser clean list demo3 [{"id":2,...}] no loop 440B after POST – Third proof – Working – Laptop order, Postman POST success demo4 200 OK 440B Laptop PENDING 50000 after fix clean – Fourth proof – Main CRUD – 440B – Without Lombok – MySQL 5.5.5 MariaDB real DB – Infinite loop fixed 13KB -> 440B – 75/100 challenge proof – Same as 74 real proof but MySQL + 4 final demos – Completed – No OrderVault.PRO name – Only 75-Order-Service – Final

## 🚀 Future Enhancements – After 75/100 Completed – Final

- Add JWT filter – OncePerRequestFilter – Validate token from Authorization Bearer from 73-auth – SecurityContextHolder – For order protection – 76-Gateway will validate – Add @Component JwtAuthenticationFilter – Same as 74 future
- Add Feign Client – Product Service – @FeignClient(name="product-service") – Get product by id 101 Laptop – Check price 50000 + stock from 74-product 8082 – Verify before order – Prevent price tampering – Real E-Commerce – demo4 price 50000 could be validated via Feign – Future
- Add userId validation – Feign call to 73-auth – Check user exists – For real user check – Not any userId 1 – Auth validation – demo4 userId 1 could be validated – Future
- Switch MySQL 5.5.5 to 8.0 – docker run mysql:8.0 – Fix HHH000511 warning – Minimum supported 8.0.0 – But 5.5.5 still works with MySQLDialect – Keep 5.5.5 for learning fixes – MariaDB 10.4.32 – Current 5.5.5 works – demo1 running proof – Future
- Add pagination – Pageable – /api/orders?page=0&size=10&sort=orderDate – For orders page – 1 to 100 orders – findAll(Pageable) – Page<Order> – demo2 [] and demo3 1 order could be paginated – Future
- Add Eureka Discovery – eureka.client.enabled=true – Register to http://localhost:8761/eureka – For 76-Gateway routing – lb://order-service – Currently disabled for standalone testing – Enable after 77-Discovery up – TransportException fix will revert – Current disabled – demo1 running without Eureka proves standalone working – Future
- Add API Gateway – 76 – Spring Cloud Gateway – Route /api/orders/** to lb://order-service – JWT filter – Validate token from 73-auth – Global filter – Rate limiter – Strip prefix – Port 8080 – Gateway will route to your 8083 – Future – After 75
- Add payment integration – Razorpay / Stripe – On placeOrder create payment order – Update status PENDING to CONFIRMED on payment success – Webhook – PENDING -> CONFIRMED flow – demo3/4 status PENDING could become CONFIRMED after payment – Future
- Add Docker – Dockerfile – openjdk:21 – COPY target/75-order-service-0.0.1-SNAPSHOT.jar – EXPOSE 8083 – ENTRYPOINT java -jar – docker-compose.yml mysql 5.5.5 + auth-service 8081 + product-service 8082 + order-service 8083 + gateway 8080 + discovery 8761 – Same as 74 future but MySQL – 4 demos will work in Docker too – Future
- Deploy to Render/Railway – java -jar – MySQL Cloud – Same port 8083 – Environment variables SPRING_DATASOURCE_URL, USERNAME, PASSWORD – Without Lombok works in cloud – Infinite loop fix 440B works in cloud – demo4 440B clean will work in cloud – Future
- Add React frontend – 5173 + 8083 – Orders page – Fetch /api/orders – Table with id 2, date 2026-09-17, status PENDING badge yellow, totalAmount 50000, View items Laptop – Details page /orders/2 – Status timeline PENDING -> CONFIRMED -> SHIPPED -> DELIVERED – demo3 order could be shown in React table – Future
- Add unit tests – @SpringBootTest – Mock OrderRepository – Test placeOrder total calc 50000*1=50000 demo4 proof, Test getById not found, Test infinite loop fix @JsonManagedReference 440B not 13KB demo4 Size 440B proof, Test Found 1 JPA – JUnit 5 + Mockito – @DataJpaTest for repository – demo4 totalAmount 50000 can be unit tested – Future
- Add Swagger OpenAPI – springdoc-openapi – /swagger-ui.html – Document all 6 endpoints – Try it out – POST Laptop 50000 – Same as 74 future – Port 8083 swagger – OpenAPI 3 – 4 demos can be tested via Swagger too – Future

## 👨💻 Author

### Vemula Leela Venkata Ravi Teja

Java Full Stack Developer

100 Java Full Stack Projects Challenge

Project 75 / 100 – MySQL Track – Order Service Real Order Microservice Without Lombok – Infinite Loop Fix 440B – 4 Demos Final – Completed

Tier 8 – Microservices – Port 8083 – Third of 5 – Auth 8081 + Product 8082 + Order 8083 + Gateway 8080 + Discovery 8761 – 75/100 – Final 4 Demos Completed

### Test Order – MySQL Persisted – Final 4 Demos – Clean JSON – Completed

- `Laptop` / `101` / `1` / `50000.0` – ID 2 – Order ID 2 – Item ID 2 – Status PENDING – Total 50000.0 – UserId 1 – MySQL 5.5.5 – order_db – TOTAL_ORDERS 1 – TOTAL_ITEMS 1 – Price 50000 – Quantity 1 – ProductName Laptop – ProductId 101 – Without Lombok manual getters/setters – POST 200 OK 2.40s 440B Before 13.77KB – demo1 test running 8083 – demo2 [] empty – demo3 clean list [{"id":2,...}] – demo4 POST 200 OK 440B – Test running 8083 – Get All [] empty before, clean list after – Infinite Loop Fixed 13KB -> 440B – 75/100 Completed – Final 4 Demos – No OrderVault.PRO – Only 75-Order-Service – Completed

## ⭐ Support

If you found this project helpful fixing Found 0 JPA / Eureka 8761 Connection refused / Lombok Red X / Infinite JSON 13KB -> 440B errors with final 4 demos demo1 running 8083, demo2 [] empty, demo3 clean [{"id":2,...}], demo4 POST 200 OK 440B, give it a ⭐ Star on GitHub!

### Repo

https://github.com/raviteja-dev950/75-Order-Service

### Run – Final 4 Demos – Completed – 75/100

```bash
# Use Maven wrapper – Fixes 'mvn not recognized' error
mvnw.cmd clean install -DskipTests
mvnw.cmd spring-boot:run

# OR if Maven in PATH
mvn clean install -DskipTests
mvn spring-boot:run
```

Open – Final 4 demos – Completed:

```text
http://localhost:8083/api/orders/test – demo1.png – Order Service is running on port 8083 – WITHOUT Lombok – MySQL 5.5.5 – order_db – Tomcat 8083 – First proof – Running 8083 – White page

http://localhost:8083/api/orders – demo2.png – [] – Pretty-print [] – Empty set – Before order – Clean DB – No data – 0 orders – Empty set proof – Before POST – Second proof

http://localhost:8083/api/orders – demo3.png – [{"id":2,"orderDate":"2026-09-17T18:48:30","orderItems":[{"id":2,"price":50000.0,"productId":101,"productName":"Laptop","quantity":1}],"status":"PENDING","totalAmount":50000.0,"userId":1}] – Clean after fix – No infinite loop – 440B – After POST – Working – Laptop order – Real MySQL – Single order list – Third proof

POST http://localhost:8083/api/orders/place – demo4.png – Body userId 1 orderItems productId 101 productName Laptop quantity 1 price 50000 – Request 1-8 lines – Response Status 200 OK Time 2.40s Size 440B – Body Pretty JSON id 2 orderItems id 2 price 50000 productId 101 Laptop quantity 1 status PENDING totalAmount 50000 userId 1 – Clean 440B – Before fix would be 13.77KB infinite – After fix 440B – Main proof – Total calc 50000*1=50000 – Fourth proof – Main proof – Final – Completed – 75/100
```

Curl – Final 4 demos – Completed:

```text
curl http://localhost:8083/api/orders/test – demo1.png – Order Service is running on port 8083 – Tomcat 8083 – MySQL connected – WITHOUT Lombok proof – White page – First proof

curl http://localhost:8083/api/orders – demo2.png – [] – Empty set – Before order – Second proof – Clean DB – No data

curl -X POST http://localhost:8083/api/orders/place -H "Content-Type: application/json" -d "{"userId":1,"orderItems":[{"productId":101,"productName":"Laptop","quantity":1,"price":50000}]}" – demo4.png – 200 OK {"id":2,"orderDate":"2026-09-17T18:48:30","orderItems":[{"id":2,"price":50000.0,"productId":101,"productName":"Laptop","quantity":1}],"status":"PENDING","totalAmount":50000.0,"userId":1} – 440B – 2.40s – After @JsonManagedReference + @JsonBackReference fix – Clean JSON – No order inside orderItems – Size 13.77KB -> 440B – Fourth proof – Main proof – Total calc 50000

curl http://localhost:8083/api/orders – demo3.png – [{"id":2,"orderDate":"2026-09-17T18:48:30","orderItems":[{"id":2,"price":50000.0,"productId":101,"productName":"Laptop","quantity":1}],"status":"PENDING","totalAmount":50000.0,"userId":1}] – List working – MySQL persistence – Not H2 – Clean – No infinite – 440B – Third proof – Laptop order – Real MySQL – After POST
```

Sample Order – Final 4 Demos – Completed – 75/100
```

ID: 2 – USER_ID 1 – ORDER_DATE 2026-09-17T18:48:30 – STATUS PENDING – TOTAL_AMOUNT 50000.0 – ORDER_ITEMS 1 – ITEM ID 2 – PRODUCT_ID 101 – PRODUCT_NAME Laptop – QUANTITY 1 – PRICE 50000.0 – ORDER_ID FK 2 – MySQL 5.5.5-MariaDB – order_db – TOTAL_ORDERS 1 – TOTAL_ITEMS 1 – Price 50000 – Quantity 1 – Laptop – Without Lombok manual – POST 200 OK 2.40s 440B Before 13.77KB – demo1 test running 8083 – demo2 [] empty – demo3 clean list [{"id":2,...}] – demo4 POST 200 OK 440B – Test running 8083 – Get All [] empty before, clean list after – Infinite Loop Fixed 13KB -> 440B – 75/100 Completed – Final 4 Demos – No OrderVault.PRO – Only 75-Order-Service – Completed – 75/100

Project 75 Completed – 75/100 – MySQL Order Service Without Lombok – Infinite Loop Fix 440B – 4 Demos Final – No CMD 5th needed – Completed Here
```
