# Microservices Application — Gestion des Factures

## Présentation

Ce projet consiste à développer une application basée sur une **architecture microservices** permettant de gérer des clients, des produits et des factures.

L'objectif est de mettre en pratique les principaux concepts de l'écosystème **Spring Boot / Spring Cloud**, notamment :

* la création de microservices indépendants ;
* la communication entre microservices ;
* le routage avec Spring Cloud Gateway ;
* la découverte de services avec Eureka ;
* la communication inter-services avec OpenFeign ;
* la centralisation de la configuration ;
* l'intégration d'une interface web Angular.

Le projet est réalisé progressivement, chaque fonctionnalité étant développée et testée avant de passer à l'étape suivante.

---

## Architecture prévue

L'architecture finale du projet sera organisée autour des composants suivants :

```text
                         ┌──────────────────┐
                         │  Angular Client   │
                         │      :4200       │
                         └────────┬─────────┘
                                  │
                                  ▼
                         ┌──────────────────┐
                         │ Spring Cloud     │
                         │ Gateway :8888    │
                         └────────┬─────────┘
                                  │
                    ┌─────────────┼─────────────┐
                    │             │             │
                    ▼             ▼             ▼
             ┌────────────┐ ┌────────────┐ ┌────────────┐
             │ Customer   │ │ Inventory  │ │  Billing   │
             │ Service    │ │ Service    │ │  Service   │
             │   :8081    │ │   :8082    │ │   :8083    │
             └────────────┘ └────────────┘ └──────┬─────┘
                                                  │
                                           OpenFeign
                                                  │
                                                  ▼
                                      Customer / Inventory

                         ┌──────────────────┐
                         │ Eureka Discovery │
                         │      :8761       │
                         └──────────────────┘

                         ┌──────────────────┐
                         │ Config Server    │
                         │      :9999       │
                         └──────────────────┘
```

---

# État actuel du projet

Le projet est actuellement en cours de développement.

### Étape 1 — Customer Service

Le premier microservice a été développé avec **Spring Boot**.

Il permet actuellement de gérer les clients à travers une API REST.

### Technologies utilisées

* Java 17
* Spring Boot
* Spring Web
* Spring Data JPA
* H2 Database
* Maven
* Lombok

### Structure

```text
customer-service/
└── src/
    └── main/
        ├── java/
        │   └── com/example/customer_service/
        │       ├── entities/
        │       │   └── Customer.java
        │       ├── repositories/
        │       │   └── CustomerRepository.java
        │       ├── services/
        │       │   └── CustomerService.java
        │       ├── web/
        │       │   └── CustomerController.java
        │       └── CustomerServiceApplication.java
        │
        └── resources/
            └── application.properties
```

### Modèle `Customer`

Un client contient actuellement :

```text
Customer
├── id
├── name
└── email
```

### API REST

Le microservice est disponible sur le port :

```text
8081
```

#### Récupérer tous les clients

```http
GET http://localhost:8081/customers
```

#### Récupérer un client

```http
GET http://localhost:8081/customers/{id}
```

#### Ajouter un client

```http
POST http://localhost:8081/customers
```

Exemple :

```json
{
  "name": "Youssef",
  "email": "youssef@gmail.com"
}
```

#### Modifier un client

```http
PUT http://localhost:8081/customers/{id}
```

Exemple :

```json
{
  "name": "Houda El Jirari",
  "email": "houda.eljirari@gmail.com"
}
```

#### Supprimer un client

```http
DELETE http://localhost:8081/customers/{id}
```

---

## Base de données

Pour cette première version, le microservice utilise une base de données **H2 en mémoire**.

Configuration principale :

```properties
spring.datasource.url=jdbc:h2:mem:customer-db
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=create
```

Des données initiales sont automatiquement insérées au démarrage de l'application afin de faciliter les tests de l'API.

---

# Étapes prévues

Le projet sera développé progressivement selon les étapes suivantes.

### 1. Customer Service

**Statut : Terminé**

Gestion des clients avec Spring Boot, Spring Data JPA et H2.

### 2. Inventory Service

**Statut : À réaliser**

Gestion des produits :

* création d'un produit ;
* consultation des produits ;
* modification ;
* suppression.

Port prévu :

```text
8082
```

### 3. Spring Cloud Gateway

**Statut : À réaliser**

Mise en place d'une passerelle unique permettant d'accéder aux différents microservices.

Port prévu :

```text
8888
```

Une première configuration sera réalisée avec un **routage statique**.

### 4. Eureka Discovery Service

**Statut : À réaliser**

Mise en place d'un serveur Eureka permettant l'enregistrement et la découverte des microservices.

Port prévu :

```text
8761
```

### 5. Routage dynamique

Le Gateway sera ensuite configuré pour découvrir automatiquement les services enregistrés auprès d'Eureka.

### 6. Billing Service

**Statut : À réaliser**

Création du microservice permettant de gérer les factures.

Une facture sera associée à :

* un client ;
* plusieurs produits.

La communication avec les autres microservices sera réalisée avec **OpenFeign**.

Port prévu :

```text
8083
```

### 7. Config Service

**Statut : À réaliser**

Mise en place d'un **Spring Cloud Config Server** permettant de centraliser la configuration des différents microservices.

Port prévu :

```text
9999
```

Un dépôt de configuration séparé sera utilisé :

```text
config-repo/
```

### 8. Angular Client

**Statut : À réaliser**

Développement d'une interface Angular permettant d'interagir avec l'application à travers le Gateway.

Port prévu :

```text
4200
```

---

# Technologies

| Technologie          | Utilisation                          |
| -------------------- | ------------------------------------ |
| Java 17              | Langage de développement             |
| Spring Boot          | Création des microservices           |
| Spring Data JPA      | Persistance des données              |
| H2                   | Base de données de développement     |
| Spring Cloud Gateway | API Gateway                          |
| Eureka               | Service Discovery                    |
| OpenFeign            | Communication entre microservices    |
| Spring Cloud Config  | Configuration centralisée            |
| Maven                | Gestion du projet et des dépendances |
| Angular              | Interface utilisateur                |
| Git / GitHub         | Versionnement du projet              |

---

# Structure finale prévue

```text
micro-services-app/
│
├── customer-service/
│
├── inventory-service/
│
├── gateway-service/
│
├── discovery-service/
│
├── billing-service/
│
├── config-service/
│
├── config-repo/
│
├── frontend/
│
└── README.md
```

---

# Objectif pédagogique

Ce projet permet de mettre en pratique les principes d'une architecture microservices :

* séparation des responsabilités ;
* indépendance des services ;
* communication entre services ;
* découverte dynamique ;
* routage centralisé ;
* configuration distribuée ;
* exposition d'API REST ;
* développement d'un client web.

Le projet est développé progressivement afin de comprendre le rôle et l'intégration de chaque composant dans l'architecture globale.
