package com.shipment.logistics.repository;

import com.shipment.logistics.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
