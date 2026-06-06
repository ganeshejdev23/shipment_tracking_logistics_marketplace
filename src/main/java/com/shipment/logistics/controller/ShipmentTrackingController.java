package com.shipment.logistics.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import com.shipment.logistics.entity.ShipmentTracking;
import com.shipment.logistics.repository.ShipmentTrackingRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Shipment Tracking", description = "APIs for live shipment tracking updates")
@RestController
@RequestMapping("/api/tracking")
public class ShipmentTrackingController {

	@Autowired
	private ShipmentTrackingRepository trackingRepository;

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@Operation(summary = "Update shipment coordinates", description = "Updates shipment latitude and longitude")
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

	@Operation(summary = "Get shipment tracking", description = "Returns shipment tracking details by shipment ID")
	// TRACKING HISTORY
	@GetMapping("/{shipmentId}")
	public List<ShipmentTracking> getTrackingHistory(@PathVariable Long shipmentId) {

		return trackingRepository.findByShipmentId(shipmentId);
	}
}