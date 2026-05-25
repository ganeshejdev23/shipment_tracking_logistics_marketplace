package com.shipment.logistics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.shipment.logistics.entity.ShipmentTracking;
import com.shipment.logistics.service.ShipmentTrackingService;

@RestController
@RequestMapping("/api/tracking")
public class ShipmentTrackingController {

	@Autowired
	private ShipmentTrackingService trackingService;

	// DRIVER GPS UPDATE
	@PostMapping("/update")
	public ShipmentTracking updateTracking(@RequestBody ShipmentTracking tracking) {

		return trackingService.updateTracking(tracking);
	}

	// GET TRACKING HISTORY
	@GetMapping("/{shipmentId}")
	public List<ShipmentTracking> getTrackingHistory(@PathVariable Long shipmentId) {

		return trackingService.getTrackingHistory(shipmentId);
	}
}