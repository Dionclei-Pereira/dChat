![WebSocket](https://img.shields.io/badge/WebSocket-orange)
![RabbitMQ](https://img.shields.io/badge/RabbitMQ-orange)
![Stomp](https://img.shields.io/badge/Stomp-blue)
![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.5-green)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6.4.1-blue)
![JWT](https://img.shields.io/badge/JWT-HMAC-blue)
![TypeScript](https://img.shields.io/badge/TypeScript-blue)
![Angular](https://img.shields.io/badge/Angular-19-red)


# dChat - Full Stack

## 📖 Description

dChat is a full stack, real-time chat application that enables users to communicate privately or globally. Built with a modern 
microservice-inspired architecture, it integrates WebSocket, STOMP, 
and RabbitMQ to deliver responsive and scalable messaging functionality.
Users can register, authenticate securely via JWT, and start chatting instantly in real-time. 
All messages are persisted and routed to the correct recipients via message queues.

## 🏗️ Project Structure

├─ backend/   --> Spring Boot REST API <br>
├─ frontend/  --> Angular Web Application <br>
├─ Dockerfile/  --> RabbitMQ with STOMP plugin <br>
└─ docker-compose.yml --> Build and run the application 

## 🚀 **Technologies**

### 🖥️ Backend

- ⚙️ Java 21  
- 🌱 Spring Boot  
- 🐰 RabbitMQ
- 🍃 MongoDB  
- 🔐 Spring Security
- 🌐 WebSocket + STOMP
- 🔑 JWT - HMAC
- 🐳 Docker & Docker Compose
  
### 🌐 Frontend

- 🔺 Angular 19
- 🎨 Bootstrap 5
- 🟦 TypeScript
- 🔰 Nginx
- 📮 StompJS (WebSocket client)
- 🐳 Docker & Docker Compose

## 🎯 **Features**

- 🔐 Secure Authentication & Authorization (JWT HMAC)
- 💬 Private and Global Chat Rooms
- 📡 Real-Time Messaging with WebSocket + STOMP
- 📨 Reliable Routing via RabbitMQ
- 🕓 Message History Persistence
- ⚙️ Dockerized Setup for quick deployment
- 🧍‍♂️ Auto-delivery to connected users only

## ⚙ Prerequisites

Install these programs:

- 🐳 Docker & Docker Compose

## ⚡ Steps to Run the Project

### 1. Clone the repository

Clone the project to your local environment:

```bash
git clone https://github.com/Dionclei-Pereira/dChat.git
```

### 2. Build the application

```bash
docker-compose build
```

### 3. Run the Project

```bash
docker-compose up -d rabbitMQ
docker-compose up -d
```

## 📜Author

**Dionclei de Souza Pereira**

[Linkedin](https://www.linkedin.com/in/dionclei-de-souza-pereira-07287726b/)

⭐️ If you like this project, give it a star!  
