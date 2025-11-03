#include <WiFi.h>
#include <HTTPClient.h>

const char* ssid = "YOUR_SSID";
const char* password = "YOUR_PASS";
const char* serverUrl = "http://YOUR_SERVER:8080/api/v1/device-data";

void setup() {
  Serial.begin(115200);
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("\nConnected");
}

void loop() {
  if (WiFi.status() == WL_CONNECTED) {
    HTTPClient http;
    http.begin(serverUrl);
    http.addHeader("Content-Type", "application/json");
    String payload = "{\"deviceId\":\"cow-001\", \"timestamp\":\"2025-01-01T00:00:00Z\", \"foodConsumed\":3.2, \"waterConsumed\":5.1}";
    int httpCode = http.POST(payload);
    Serial.println("Posted, code=" + String(httpCode));
    http.end();
  }
  delay(15000);
}