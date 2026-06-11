package com.shipment.logistics.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shipment.logistics.entity.Bid;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {

	List<Bid> findByLoadId(Long loadId);
}
