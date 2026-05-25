package com.shipment.logistics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.shipment.logistics.entity.Bid;
import com.shipment.logistics.service.BidService;

@RestController
@RequestMapping("/api/bids")
public class BidController {

	@Autowired
	private BidService bidService;

	// POST BID
	@PostMapping
	public Bid createBid(@RequestBody Bid bid) {

		return bidService.createBid(bid);
	}

	// GET BIDS BY LOAD ID
	@GetMapping("/load/{loadId}")
	public List<Bid> getBidsByLoadId(@PathVariable Long loadId) {

		return bidService.getBidsByLoadId(loadId);
	}
}