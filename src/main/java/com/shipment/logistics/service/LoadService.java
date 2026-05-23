package com.shipment.logistics.service;

import com.shipment.logistics.entity.LoadPosting;
import com.shipment.logistics.repository.LoadPostingRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoadService {

	@Autowired
	private LoadPostingRepository repository;

	public LoadPosting createLoad(LoadPosting load) {
		load.setStatus("OPEN");
		return repository.save(load);
	}

	public List<LoadPosting> getAllLoads() {
		return repository.findAll();
	}
}
