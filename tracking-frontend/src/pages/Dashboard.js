import '../App.css';
import { useState } from 'react';
import {
    MapContainer,
    TileLayer,
    Marker,
    Popup
} from 'react-leaflet';

import 'leaflet/dist/leaflet.css';
import L from 'leaflet';

import markerIcon2x
    from 'leaflet/dist/images/marker-icon-2x.png';

import markerIcon
    from 'leaflet/dist/images/marker-icon.png';

import markerShadow
    from 'leaflet/dist/images/marker-shadow.png';

delete L.Icon.Default.prototype._getIconUrl;

L.Icon.Default.mergeOptions({
    iconRetinaUrl: markerIcon2x,
    iconUrl: markerIcon,
    shadowUrl: markerShadow,
});

function Dashboard() {

    const [shipmentId,
        setShipmentId] =
        useState('');

    const [status,
        setStatus] =
        useState('Not Tracked Yet');

    const [latitude,
        setLatitude] =
        useState(null);

    const [longitude,
        setLongitude] =
        useState(null);

    const handleTracking =
        async () => {

            if (
                shipmentId === ''
            ) {

                alert(
                    'Please enter shipment ID'
                );

                return;
            }

            try {

                const response =
                    await fetch(
                        `http://localhost:8081/api/tracking/${shipmentId}`,
                        {
                            headers: {
                                Authorization:
                                    `Bearer ${localStorage.getItem('token')}`
                            }
                        }
                    );

                const data =
                    await response.json();

                if (
                    data.length > 0
                ) {

                    setStatus(
                        'IN_TRANSIT'
                    );

                    setLatitude(
                        data[0].latitude
                    );

                    setLongitude(
                        data[0].longitude
                    );

                } else {

                    setStatus(
                        'No Tracking Found'
                    );
                }

            } catch (error) {

                console.error(
                    error
                );

                alert(
                    'Error connecting to backend'
                );
            }
        };
    const handleLogout = () => {

        localStorage.removeItem(
            'token'
        );

        window.location.href = '/';
    };

    return (

        <div className="app-container">

            <div className="card">

                <div
                    style={{
                        display: 'flex',
                        justifyContent: 'space-between',
                        alignItems: 'center',
                        width: '100%'
                    }}
                >

                    <h1
                        style={{
                            margin: 0
                        }}
                    >
                        🚚 Shipment Tracking Dashboard
                    </h1>

                    <button
                        onClick={handleLogout}
                        style={{
                            background: '#dc3545',
                            color: 'white',
                            border: 'none',
                            padding: '10px 15px',
                            borderRadius: '8px',
                            cursor: 'pointer'
                        }}
                    >
                        Logout
                    </button>

                </div>

                <p>
                    Track your shipment in real time
                </p>

                <div className="search-box">

                    <input
                        type="text"
                        placeholder="Enter Shipment ID"
                        value={shipmentId}
                        onChange={(e) =>
                            setShipmentId(
                                e.target.value
                            )
                        }
                    />

                    <button
                        onClick={
                            handleTracking
                        }
                    >
                        Track Shipment
                    </button>

                </div>

                <div className="result-box">

                    <h3>
                        Shipment Details
                    </h3>

                    <p>
                        Status:
                        {' '}
                        {status}
                    </p>

                    <p>
                        Latitude:
                        {' '}
                        {latitude ?? '--'}
                    </p>

                    <p>
                        Longitude:
                        {' '}
                        {longitude ?? '--'}
                    </p>

                </div>

                {
                    latitude &&
                    longitude && (

                        <MapContainer
                            center={[
                                parseFloat(
                                    latitude
                                ),
                                parseFloat(
                                    longitude
                                )
                            ]}
                            zoom={13}
                            style={{
                                height:
                                    '450px',
                                width:
                                    '100%',
                                marginTop:
                                    '20px',
                                borderRadius:
                                    '10px'
                            }}
                        >

                            <TileLayer
                                url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                            />

                            <Marker
                                position={[
                                    parseFloat(
                                        latitude
                                    ),
                                    parseFloat(
                                        longitude
                                    )
                                ]}
                            >

                                <Popup>
                                    Shipment
                                    Current
                                    Location
                                </Popup>

                            </Marker>

                        </MapContainer>
                    )
                }

            </div>

        </div>
    );
}

export default Dashboard;