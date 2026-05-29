package com.shipment.logistics.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import com.shipment.logistics.entity.ShipmentTracking;
import com.shipment.logistics.repository.ShipmentTrackingRepository;

@RestController
@RequestMapping("/api/tracking")
public class ShipmentTrackingController {

	@Autowired
	private ShipmentTrackingRepository trackingRepository;

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	// UPDATE TRACKING
	@PostMapping("/update")
	public ShipmentTracking updateTracking(@RequestBody ShipmentTracking tracking) {

		tracking.setTimestamp(LocalDateTime.now());

		ShipmentTracking savedTracking = trackingRepository.save(tracking);

		// LIVE WEBSOCKET UPDATE
		messagingTemplate.convertAndSend("/topic/tracking/" + tracking.getShipmentId(),

				savedTracking);

		return savedTracking;
	}

	// TRACKING HISTORY
	@GetMapping("/{shipmentId}")
	public List<ShipmentTracking> getTrackingHistory(@PathVariable Long shipmentId) {

		return trackingRepository.findByShipmentId(shipmentId);
	}
}