package com.shipment.logistics.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
public class ShipmentTracking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long shipmentId;

	private Double latitude;

	private Double longitude;

	private LocalDateTime timestamp;

	// GETTERS & SETTERS

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getShipmentId() {
		return shipmentId;
	}

	public void setShipmentId(Long shipmentId) {

		this.shipmentId = shipmentId;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {

		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {

		this.longitude = longitude;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {

		this.timestamp = timestamp;
	}
}