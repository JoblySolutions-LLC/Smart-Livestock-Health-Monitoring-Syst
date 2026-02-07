package com.livestock.ingestor.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*") // Allow frontend access
public class DeviceDataController {

    // In-memory storage (for demo purpose)
    private final ConcurrentHashMap<String, DeviceData> deviceStore = new ConcurrentHashMap<>();


    // Data Model
    public static class DeviceData {

        private String deviceId;
        private Instant timestamp;
        private double temperature;
        private double heartRate;

        // Getters and Setters
        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public Instant getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Instant timestamp) {
            this.timestamp = timestamp;
        }

        public double getTemperature() {
            return temperature;
        }

        public void setTemperature(double temperature) {
            this.temperature = temperature;
        }

        public double getHeartRate() {
            return heartRate;
        }

        public void setHeartRate(double heartRate) {
            this.heartRate = heartRate;
        }
    }


    // Receive Data from ESP32
    @PostMapping("/device-data")
    public ResponseEntity<?> ingestData(@RequestBody DeviceData data) {

        // Validation
        if (data.getDeviceId() == null || data.getDeviceId().isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Device ID is required");
        }

        if (data.getTemperature() <= 0 || data.getHeartRate() <= 0) {
            return ResponseEntity
                    .status(HttpStat


