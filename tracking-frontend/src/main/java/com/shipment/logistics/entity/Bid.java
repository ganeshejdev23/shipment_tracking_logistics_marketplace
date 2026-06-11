package com.shipment.logistics.entity;

import jakarta.persistence.*;

@Entity
public class Bid {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Double amount;

	private Long carrierId;

	private Long loadId;

	private String status;

	// GETTERS & SETTERS

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {

		this.amount = amount;
	}

	public Long getCarrierId() {
		return carrierId;
	}

	public void setCarrierId(Long carrierId) {

		this.carrierId = carrierId;
	}

	public Long getLoadId() {
		return loadId;
	}

	public void setLoadId(Long loadId) {

		this.loadId = loadId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {

		this.status = status;
	}
}
