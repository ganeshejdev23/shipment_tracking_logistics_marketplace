import './App.css';
import { useState } from 'react';

function App() {

  const [shipmentId, setShipmentId] = useState('');
  const [status, setStatus] = useState('Not Tracked Yet');
  const [location, setLocation] = useState('--');

  const handleTracking = async () => {

    if (shipmentId === '') {
      alert('Please enter shipment ID');
      return;
    }

    try {

      const response = await fetch(
        `http://localhost:8081/api/tracking/${shipmentId}`,
        {
          headers: {
            Authorization:
              'Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjYXJyaWVyMiIsImlhdCI6MTc4MTA1NzY0MSwiZXhwIjoxNzgxMDkzNjQxfQ.u7lASsJc-wQI9TWHPOOPsQOdumzctaKuTZIxNgoKxUU'
          }
        }
      );

      const data = await response.json();

      if (data.length > 0) {

        setStatus('IN_TRANSIT');

        setLocation(
          `${data[0].latitude},
          ${data[0].longitude}`
        );

      } else {

        setStatus('No Tracking Found');
        setLocation('--');
      }

    } catch (error) {

      console.error(error);

      alert('Error connecting to backend');
    }
  };

  return (
    <div className="app-container">

      <div className="card">

        <h1>🚚 Shipment Tracking Dashboard</h1>

        <p>
          Track your shipment in real time
        </p>

        <div className="search-box">

          <input
            type="text"
            placeholder="Enter Shipment ID"
            value={shipmentId}
            onChange={(e) =>
              setShipmentId(e.target.value)
            }
          />

          <button onClick={handleTracking}>
            Track Shipment
          </button>

        </div>

        <div className="result-box">

          <h3>Shipment Details</h3>

          <p>Status: {status}</p>

          <p>Location: {location}</p>

        </div>

      </div>

    </div>
  );
}

export default App;