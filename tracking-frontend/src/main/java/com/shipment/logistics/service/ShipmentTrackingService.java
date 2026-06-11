package com.shipment.logistics.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.shipment.logistics.entity.ShipmentTracking;
import com.shipment.logistics.repository.ShipmentTrackingRepository;

@Service
public class ShipmentTrackingService {

	@Autowired
	private ShipmentTrackingRepository trackingRepository;

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	// SAVE GPS UPDATE
	public ShipmentTracking updateTracking(ShipmentTracking tracking) {

		tracking.setTimestamp(LocalDateTime.now());

		ShipmentTracking savedTracking = trackingRepository.save(tracking);

		// BROADCAST LIVE UPDATE
		messagingTemplate.convertAndSend("/topic/tracking/" + tracking.getShipmentId(), savedTracking);

		return savedTracking;
	}

	// GET TRACKING HISTORY
	public List<ShipmentTracking> getTrackingHistory(Long shipmentId) {

		return trackingRepository.findByShipmentId(shipmentId);
	}
}