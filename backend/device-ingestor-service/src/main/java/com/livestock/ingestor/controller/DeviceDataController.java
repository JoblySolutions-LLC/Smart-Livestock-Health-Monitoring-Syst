package com.livestock.ingestor.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;

@RestController
@RequestMapping("/api/v1")
public class DeviceDataController {

    public static class DeviceData {
        public String deviceId;
        public String timestamp;
        public double foodConsumed;
        public double waterConsumed;
    }

    @PostMapping("/device-data")
    public ResponseEntity<?> ingestData(@RequestBody DeviceData data) {
        if (data.deviceId == null || data.foodConsumed < 0 || data.waterConsumed < 0) {
            return ResponseEntity.badRequest().body("Invalid payload");
        }
        System.out.println("Ingested: " + data.deviceId + " at " + Instant.now());
        return ResponseEntity.ok().body("Data ingested for device " + data.deviceId);
    }
}
