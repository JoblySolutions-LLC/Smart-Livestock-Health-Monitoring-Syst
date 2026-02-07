# Smart Livestock Health Monitoring System

This project is an IoT-based system for monitoring livestock health using ESP32 sensors,
backend services, and a web dashboard.

---

##  Features

- Real-time temperature and heart rate monitoring
- MQTT-based data ingestion
- Automated alert notifications
- Web-based dashboard
- Dockerized deployment

---

##  System Architecture

ESP32 → MQTT Broker → Ingestor Service → Database → Notification Service → Dashboard

---

##  Technologies Used

- ESP32 (Arduino)
- Java Spring Boot
- Node.js
- React.js
- MQTT
- Docker

---

##  Quick Start

### 1. Start Services (MQTT + Backend)

```bash
docker-compose up --build
###2. Run Device Ingestor Service
cd backend/ingestor
mvn spring-boot:run
###3. Start Notification Service
cd backend/notification
node index.js
###4. Start Frontend Dashboard
cd frontend/react-dashboard
npm install
npm start


```bash
docker-compose up --build
