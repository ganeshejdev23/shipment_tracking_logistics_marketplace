package com.shipment.logistics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.shipment.logistics.entity.Bid;
import com.shipment.logistics.service.BidService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Bid Management", description = "APIs for carrier bidding on shipment loads")
@RestController
@RequestMapping("/api/bids")
public class BidController {

	@Autowired
	private BidService bidService;

	@Operation(summary = "Place bid on shipment", description = "Allows carrier to place bid for shipment load")

	// POST BID
	@PostMapping
	public Bid createBid(@RequestBody Bid bid) {

		return bidService.createBid(bid);
	}

	@Operation(summary = "Get bids by shipment", description = "Returns all bids for selected shipment load")

	// GET BIDS BY LOAD ID
	@GetMapping("/load/{loadId}")
	public List<Bid> getBidsByLoadId(@PathVariable Long loadId) {

		return bidService.getBidsByLoadId(loadId);
	}
}