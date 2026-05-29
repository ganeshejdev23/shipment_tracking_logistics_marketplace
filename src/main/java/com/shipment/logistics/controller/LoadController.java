package com.shipment.logistics.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.shipment.logistics.entity.LoadPosting;
import com.shipment.logistics.service.LoadService;

@RestController
@RequestMapping("/api/loads")
public class LoadController {

	@Autowired
	private LoadService loadService;

	// POST API
	@PostMapping
	public LoadPosting createLoad(@RequestBody LoadPosting load) {

		return loadService.createLoad(load);
	}

	// GET ALL LOADS
	@GetMapping
	public List<LoadPosting> getAllLoads() {

		return loadService.getAllLoads();
	}

	// GET LOAD BY ID
	@GetMapping("/{id}")
	public Optional<LoadPosting> getLoadById(@PathVariable Long id) {

		return loadService.getLoadById(id);
	}

	// UPDATE LOAD
	@PutMapping("/{id}")
	public LoadPosting updateLoad(@PathVariable Long id, @RequestBody LoadPosting load) {

		return loadService.updateLoad(id, load);
	}

	// DELETE LOAD
	@DeleteMapping("/{id}")
	public String deleteLoad(@PathVariable Long id) {

		loadService.deleteLoad(id);

		return "Load deleted successfully";
	}

	@PutMapping("/{loadId}/award/{carrierId}")
	public LoadPosting awardLoad(@PathVariable Long loadId, @PathVariable Long carrierId) {

		return loadService.awardLoad(loadId, carrierId);
	}

	// START DELIVERY
	@PutMapping("/{id}/pickup")
	public LoadPosting pickupShipment(@PathVariable Long id) {

		return loadService.pickupShipment(id);
	}

	// DELIVER SHIPMENT
	@PutMapping("/{id}/deliver")
	public LoadPosting deliverShipment(@PathVariable Long id) {

		return loadService.deliverShipment(id);
	}
}
