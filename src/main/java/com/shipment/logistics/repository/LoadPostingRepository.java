package com.shipment.logistics.repository;

import com.shipment.logistics.entity.LoadPosting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoadPostingRepository extends JpaRepository<LoadPosting, Long> {
}
