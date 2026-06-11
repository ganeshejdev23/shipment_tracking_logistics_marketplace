package com.shipment.logistics.entity;

import jakarta.persistence.*;

@Entity
public class LoadPosting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String origin;
	private String destination;
	private double weight;
	private String status;
	private Long awardedCarrierId;

	// GETTERS AND SETTERS

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getAwardedCarrierId() {
		return awardedCarrierId;
	}

	public void setAwardedCarrierId(Long awardedCarrierId) {

		this.awardedCarrierId = awardedCarrierId;
	}
}
