package com.shipment.logistics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shipment.logistics.entity.Bid;
import com.shipment.logistics.repository.BidRepository;

@Service
public class BidService {

	@Autowired
	private BidRepository bidRepository;

	// CREATE BID
	public Bid createBid(Bid bid) {

		bid.setStatus("PENDING");

		return bidRepository.save(bid);
	}

	// GET BIDS BY LOAD ID
	public List<Bid> getBidsByLoadId(Long loadId) {

		return bidRepository.findByLoadId(loadId);
	}
}
