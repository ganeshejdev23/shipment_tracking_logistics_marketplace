import './App.css';

function App() {
    return (
        <div className="container">
            <h1>🚚 Shipment Tracking Dashboard</h1>

            <p>
                Track your shipment in real time
            </p>

            <input
                type="text"
                placeholder="Enter Shipment ID"
            />

            <button>
                Track Shipment
            </button>
        </div>
    );
}

export default App;