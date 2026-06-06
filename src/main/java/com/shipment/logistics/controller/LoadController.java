package com.shipment.logistics.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.shipment.logistics.entity.LoadPosting;
import com.shipment.logistics.service.LoadService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Load Management", description = "APIs for shipment load creation, awarding and lifecycle tracking")
@RequestMapping("/api/loads")

public class LoadController {

	@Autowired
	private LoadService loadService;

	@Operation(summary = "Create shipment load", description = "Allows shipper to create a shipment load")

	// POST API
	@PostMapping
	public LoadPosting createLoad(@RequestBody LoadPosting load) {

		return loadService.createLoad(load);
	}

	@Operation(summary = "Get all shipment loads", description = "Returns all available shipment loads")
	// GET ALL LOADS
	@GetMapping
	public List<LoadPosting> getAllLoads() {

		return loadService.getAllLoads();
	}

	@Operation(summary = "Get shipment by ID", description = "Returns shipment details using load ID")

	// GET LOAD BY ID
	@GetMapping("/{id}")
	public Optional<LoadPosting> getLoadById(@PathVariable Long id) {

		return loadService.getLoadById(id);
	}

	@Operation(summary = "Update shipment load", description = "Updates shipment details")

	// UPDATE LOAD
	@PutMapping("/{id}")
	public LoadPosting updateLoad(@PathVariable Long id, @RequestBody LoadPosting load) {

		return loadService.updateLoad(id, load);
	}

	@Operation(summary = "Delete shipment load", description = "Deletes shipment by ID")

	// DELETE LOAD
	@DeleteMapping("/{id}")
	public String deleteLoad(@PathVariable Long id) {

		loadService.deleteLoad(id);

		return "Load deleted successfully";
	}

	@Operation(summary = "Award shipment to carrier", description = "Accepts selected carrier bid and rejects remaining bids")

	@PutMapping("/{loadId}/award/{carrierId}")
	public LoadPosting awardLoad(@PathVariable Long loadId, @PathVariable Long carrierId) {

		return loadService.awardLoad(loadId, carrierId);
	}

	@Operation(summary = "Start shipment delivery", description = "Changes shipment status to IN_TRANSIT")

	// START DELIVERY
	@PutMapping("/{id}/pickup")
	public LoadPosting pickupShipment(@PathVariable Long id) {

		return loadService.pickupShipment(id);
	}

	@Operation(summary = "Deliver shipment", description = "Changes shipment status to DELIVERED")

	// DELIVER SHIPMENT
	@PutMapping("/{id}/deliver")
	public LoadPosting deliverShipment(@PathVariable Long id) {

		return loadService.deliverShipment(id);
	}
}
