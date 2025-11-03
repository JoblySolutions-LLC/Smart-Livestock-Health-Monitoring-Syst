# Smart Livestock Health Monitoring - Starter Repo

This repo contains minimal starter components for ingestion, notification, IoT, and frontend.

## Quick Start
1. Start MQTT broker and services:
   ```bash
   docker-compose up
   ```

2. Run Device Ingestor Service:
   ```bash
   mvn spring-boot:run
   ```

3. Start Notification Service:
   ```bash
   node index.js
   ```

4. Start Frontend Dashboard:
   ```bash
   cd frontend/react-dashboard
   npm install
   npm start
   ```

5. Run ESP32 or Python simulator to test data ingestion.
