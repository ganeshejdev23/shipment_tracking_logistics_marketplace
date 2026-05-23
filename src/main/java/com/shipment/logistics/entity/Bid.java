package com.shipment.logistics.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    private Long carrierId;

    private Long loadId;
}
