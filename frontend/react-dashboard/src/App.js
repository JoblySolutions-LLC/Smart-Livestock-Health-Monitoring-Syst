import React, { useEffect, useState } from "react";

function App() {

  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  // Fetch data from backend
  useEffect(() => {

    const fetchData = async () => {
      try {

        const response = await fetch("http://localhost:8080/api/v1/device-data/latest");

        if (!response.ok) {
          throw new Error("Failed to fetch data");
        }

        const result = await response.json();
        setData(result);
        setLoading(false);

      } catch (err) {
        setError("Server not reachable");
        setLoading(false);
      }
    };

    // Fetch every 10 seconds
    fetchData();
    const interval = setInterval(fetchData, 10000);

    return () => clearInterval(interval);

  }, []);

  return (
    <div style={{ padding: 20, fontFamily: "Arial" }}>

      <h1>🐄 Smart Livestock Health Dashboard</h1>

      <p>Real-time animal health monitoring system</p>

      <hr />

      {loading && <p>Loading data...</p>}

      {error && <p style={{ color: "red" }}>{error}</p>}

      {data && (

        <div style={{
          border: "1px solid #ccc",
          padding: 15,
          width: 300,
          borderRadius: 8
        }}>

          <h3>Device ID: {data.deviceId}</h3>

          <p>🌡 Temperature: <b>{data.temperature} °C</b></p>

          <p>❤️ Heart Rate: <b>{data.heartRate} BPM</b></p>

          <p>⏰ Last Updated: {new Date(data.timestamp).toLocaleString()}</p>

        </div>

      )}

    </div>
  );
}

export default App;
