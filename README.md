This project is a simple online store application to practise microservices architecture and microservices tech stack.

Technologies used in project:

### Java

* Spring Boot
* Spring JPA (with H2 database)
* Spring Boot Test
* Eureka as Service Discovery
* Spring Cloud LoadBalancer
* Spring Cloud Gateway
* Hystrix as Circut Breaker
* RestTemplate
* Lombok

### Python:

* Flask
* Requests

### Infrastructure

* Kafka
* Kafdrop
* Docker (with Docker Compose)

## Microservices description

Application consists of 6 microservices which 2 of them are pure technical.

### Customers

Microservice written in Java. Holds customers which are created on startup and provides information about them through
REST API. New users registration has special behaviour which simulates database connection issues. It has a 0.5
probability to successfully create new user. If database is "down", a Hystrix fallback registration method is invoked
and stores user to register in special context. There is a scheduler which fires every 1 minute and checks if there are
users to register and if so, it attempts to store them in database.

### Products

Microservice written in Java. Allows retrieving information about products and purchase concrete product. After a user
buys product, a summary event is sent to Kafka.

### Coupons

Microservice written in Python with Flask. Holds information about coupons with discounts, which can be applied
purchasing product. Has custom implementation of registering to Eureka server. On startup phase, calls Eureka server
through REST API to check if server is up. If response is successful, sends request with details needed to register in
service discovery. If response is not successful waits 5 seconds and tries again. Registering in Eureka is necessary to
properly start application.

### Management

Microservice written in Java. Receives events from Kafka about purchased products. Collects and stores information about
sold products, can be utilised for creating summaries.

### Eureka

Technical microservice providing Service Discovery functionality. Creates a registry where all other microservices
register on their startup phase. Thanks to it, microservices can communicate with each others using chosen names instead
of exposed ip addresses and ports.

### ApiGateway

Technical microservice which works as a gate, hiding internal application architecture. Outside clients communicate with
microservices through Api Gateway, which exposes special API and works as a proxy.

## Microservices diagrams

### Customers

![Customers_GET_customer](http://www.plantuml.com/plantuml/png/ZT112i8m40NGVKun5oZT5v5AbLx0lKoRgGIQf2GJfOXtjmKZGtNXBf3tFv-f8sFWP4TbqRWXe2LeYQ5FaRsb0DqCHgjcDEGOYZqS9dDfaUcESmNjwGmbJgRCF9OFevyAUpOtP99QYVTQrEJ8ksPJyVbUxH4PEunKmVLxcw2xGj10d8BRQBbYuM95zatsFe3p38DFJaEWE7aNALzYYOgcVyEQVbwc9gUJ7Ly0)

![Customers_GET_customers](http://www.plantuml.com/plantuml/png/TS_12i8m30RW-vuYBp1s7sDCflJYqRl4DOD0MqURAhwz6rWCGs-XVCsVVqY2KR9tbKSESqHFO4W0dOCf9xbxYgaQ7LCGg7iuB7mrAFJ4LmlcT8465sw-4YVX1mffMAbvsp4M_TcWSigqiICAtZ1H2pC7kxj8qQvMD9Ba67POvQzQvHT-vYHTUUvBtPMgd_-mfP-T1mesU_S6)

![Customers_POST_register](http://www.plantuml.com/plantuml/png/hPBDJiD038JlVGg_G2aNBbM5YWgYdAXeZqZ8IPpMOdyYhpTftfvi8I95oOrBj596typONWT5qMZDp2AxKj0IlD6HWvA0emRo6DHRaZ3R62QdCB-7Xue_db6fmNO9kzVz0HPOyU9NYhboZKf3vMpmJf1Dh-uH0wV-bNIFg9XXe2MKx0f0Os1CkPeFb48QnKrghzrhD5oaiDq7fxnLMb0F0Mkw6Wiqh2UulRc34FESGe1AV8OP6zPsAbukXFg7umNq9Bu1YKxP4j0vfqhPkudWVSlUk8p6j0bJiX1isw32PoZHc0ppJv3BKWKJ4TuTkcAaJEa532f9Qf6Wx8vNUrlDHm9i3uSTR2wTEgYYnj0vUFd-yUAKnA61FKbD0ayY_cTijdF08_MNrS47Nl-7BsZq7jVaYcZD5m00)

### Products

![Products_GET_product](http://www.plantuml.com/plantuml/png/ZT2n2i8m40RWFKznBr2w5v6AIjSExd9jhX9eav1Sb2A-kmr4cgA3M-2--o_yjMTq7APHJAZqu70YQ8Z1EYD3pz3DeAGuZeeqGx67WrNN1faUE5VGd2zGebLbqhvyAlaIsBEw8rEEHVQEEMtQI27RWCymmXCoTkYfWjlNjrQuApBeY8FJMvlVZtgzdtIRj76W3SDWWfRBp5kZFQK2ucAMy-Ukf9-5rAJbqliR)

![Products_GET_products](http://www.plantuml.com/plantuml/png/TS_12i8m30RW-vuYBp1s7sDCL7RnuC6xn3M3G5j7cog-lHJgp41l9Nn_at-8Wa6IivL3zdD0Hp2I05eBIxYRD4cizfR92zGzx1Q-ZYZqn5SBu_423IxSh10duGSAQLUfT_xYN739hzdFF9i32jumKWipUxCzvWkxMi50aeBVKhsvgzNcWaySfIlJlhJCKEN-M4C_gmxaJNBs3G00)

![Products_POST_purchase_product](http://www.plantuml.com/plantuml/png/ZLBDRk8m4BxdAUO1GDpHQiMAhTggXoB14QaQx06indOw7eT6LT-zJeD38a7LcpFvVhxFdaKGP8csB2mQjsUq1AldzGQgoEg8WQ1YhwEIObaQSWBJl_2lCY_tA7J2Pjw1PrYPsHaOPXz6VnQenDGeD4GNWtChj3epiifO8VzjaVzHS8U1vd0WoP7WPEG8Rn6T66aWnVTiKOnt5-wVwO3B997TTVh8RXc3U4iSEZzr_eHTSj4ZyHwPrM-2HtrhB8r6eTv-Sk6bEKxQa8f2wD_XndK4hAd7w5nj04xe9zo_uXmKK-iOehN83L1DJehHawG-cTZNUTXiLh3yfce8WX83UEx6Zqw87PQm9gw9uOxPStUjbaB00y7McJsWQo0wUgz8jIexEc9jV6JmIaNcDDAHZJla8kcO--UrIz46INzCjtM_prTekhge2t8wsl8B)

### Coupons

![Coupons_GET_active_coupons](http://www.plantuml.com/plantuml/png/bP1H2i8m343_UufSOEn_Y4ocx0B-IrmpABJfQ5F5sxkw8MKCmQ-MyDuZf8s2GPApog7XCQ0Zw4a01p4FWi6doNDKdJN40jKHJfEvzIZqn5S3_UKADKwcNhbwqTJdcQ6ILiK_Xxf5MIERG9UYU4SXPlACWdUCrC1eM0DQkrslu0zLmGUI57ZFA9R9ojV_uO0Fm5vWz8drZCKyfFKqEL8q_yzesZrQIwoJisy0)

![Coupons_GET_coupons](http://www.plantuml.com/plantuml/png/RSzD2i8m40NWVKyn5oZT5n6aIYxWNiPs2e7cXsHYyVQcMiC4kXl8zpBp36ex1pG4YXXmMM1qoJiRcs7HP1dQCroyVYXaMl7TWxhTeKElkx_3aVKhlqhMY7dxOVZPFLqsfCZEK8WRkIBZ4oFrC6ixLPS8U6e53CGfs8hAlTcMu27U6sPNDHJosurAOAAZiXy0)

### Management

![Management_process_event](http://www.plantuml.com/plantuml/png/XSyx3i8m30RWFQVm1Ng13gW36qBg5NuI5ofmMjb996wFUI0Mf8ws_i-FmGgqLBa5mPmdXJ2DkaGsexLglC0uqQfBgh4GDyubZE_AgEjsTC1qHK_6EVq1LaMWzmzyPSdH4Hbd5k_rf1nvRho9V-OvOO-2awTzCnf_es7mC_oP1m00)