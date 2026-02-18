import React, { useEffect, useState } from "react";

// Backend URL from environment variable (for deployment support)
const API_URL = process.env.REACT_APP_API_URL || "http://localhost:8080";

function App() {

  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  // Fetch data from backend
  useEffect(() => {

    const fetchData = async () => {
      try {

        setLoading(true);
        setError("");

        const response = await fetch(`${API_URL}/api/v1/device-data/latest`);

        if (!response.ok) {
          throw new Error("Failed to fetch data from server");
        }

        const result = await response.json();
        setData(result);

      } catch (err) {

        console.error("API Error:", err);
        setError("Unable to connect to backend server");

      } finally {

        setLoading(false);

      }
    };

    // Initial fetch and automatic refresh every 10 seconds
    fetchData();
    const interval = setInterval(fetchData, 10000);

    return () => clearInterval(interval);

  }, []);

  return (
    <div style={{ padding: 20, fontFamily: "Arial, sans-serif" }}>

      <h1>Smart Livestock Health Dashboard</h1>

      <p>Real-time animal health monitoring system</p>

      <hr />

      {loading && <p>Loading data...</p>}

      {error && <p style={{ color: "red" }}>{error}</p>}

      {!loading && !error && data && (

        <div
          style={{
            border: "1px solid #cccccc",
            padding: 16,
            width: 320,
            borderRadius: 6,
            backgroundColor: "#f5f5f5"
          }}
        >

          <h3>Device ID: {data.deviceId}</h3>

          <p>Temperature: <strong>{data.temperature} °C</strong></p>

          <p>Heart Rate: <strong>{data.heartRate} BPM</strong></p>

          <p>
            Last Updated:{" "}
            {new Date(data.timestamp).toLocaleString()}
          </p>

        </div>

      )}

    </div>
  );
}

export default App;

