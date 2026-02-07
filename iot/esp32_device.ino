#include <WiFi.h>
#include <HTTPClient.h>

// WiFi Credentials
const char* ssid = "YOUR_SSID";
const char* password = "YOUR_PASSWORD";

// Server API
const char* serverUrl = "http://YOUR_SERVER_IP:8080/api/v1/device-data";

// Device ID
String deviceId = "cow-001";

// Simulated Sensors (Replace with real sensors later)
float temperature;
float heartRate;

// Timer
unsigned long lastTime = 0;
unsigned long timerDelay = 15000; // 15 seconds

// Generate Random Sensor Data (Demo Purpose)
float getTemperature() {
  return random(360, 395) / 10.0; // 36.0°C to 39.5°C
}

float getHeartRate() {
  return random(60, 100); // 60 to 100 BPM
}

// Connect to WiFi
void connectWiFi() {
  WiFi.begin(ssid, password);

  Serial.print("Connecting to WiFi");

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }

  Serial.println("\nWiFi Connected!");
  Serial.print("IP Address: ");
  Serial.println(WiFi.localIP());
}

// Send Data to Server
void sendData(float temp, float heart) {

  if (WiFi.status() == WL_CONNECTED) {

    HTTPClient http;
    http.begin(serverUrl);
    http.addHeader("Content-Type", "application/json");

    // JSON Payload
    String payload = "{";
    payload += "\"deviceId\":\"" + deviceId + "\",";
    payload += "\"temperature\":" + String(temp) + ",";
    payload += "\"heartRate\":" + String(heart);
    payload += "}";

    int httpCode = http.POST(payload);

    Serial.println("Data Sent: " + payload);
    Serial.println("Response Code: " + String(httpCode));

    http.end();

  } else {
    Serial.println("WiFi Disconnected! Reconnecting...");
    connectWiFi();
  }
}

void setup() {

  Serial.begin(115200);
  delay(1000);

  connectWiFi();

  // Seed random for demo sensor data
  randomSeed(analogRead(0));
}

void loop() {

  if ((millis() - lastTime) > timerDelay) {

    temperature = getTemperature();
    heartRate = getHeartRate();

    Serial.println("Reading Sensors...");
    Serial.print("Temperature: ");
    Serial.print(temperature);
    Serial.println(" °C");

    Serial.print("Heart Rate: ");
    Serial.print(heartRate);
    Serial.println(" BPM");

    sendData(temperature, heartRate);

    lastTime = millis();
  }
}
