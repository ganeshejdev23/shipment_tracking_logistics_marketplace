package com.shipment.logistics.repository;

import com.shipment.logistics.entity.ShipmentTracking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentTrackingRepository extends JpaRepository<ShipmentTracking, Long> {
}
