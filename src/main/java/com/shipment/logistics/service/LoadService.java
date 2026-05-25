package com.shipment.logistics.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shipment.logistics.entity.LoadPosting;
import com.shipment.logistics.repository.LoadPostingRepository;

@Service
public class LoadService {

	@Autowired
	private LoadPostingRepository loadRepository;

	// CREATE LOAD (POST)
	public LoadPosting createLoad(LoadPosting load) {
		return loadRepository.save(load);
	}

	// GET ALL LOADS
	public List<LoadPosting> getAllLoads() {
		return loadRepository.findAll();
	}

	// GET LOAD BY ID
	public Optional<LoadPosting> getLoadById(Long id) {
		return loadRepository.findById(id);
	}

	// UPDATE LOAD
	public LoadPosting updateLoad(Long id, LoadPosting updatedLoad) {

		LoadPosting existingLoad = loadRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Load not found"));

		existingLoad.setOrigin(updatedLoad.getOrigin());

		existingLoad.setDestination(updatedLoad.getDestination());

		existingLoad.setWeight(updatedLoad.getWeight());

		existingLoad.setStatus(updatedLoad.getStatus());

		return loadRepository.save(existingLoad);
	}

	// DELETE LOAD
	public void deleteLoad(Long id) {
		loadRepository.deleteById(id);
	}

	public LoadPosting awardLoad(Long loadId, Long carrierId) {

		LoadPosting load = loadRepository.findById(loadId).orElseThrow(() -> new RuntimeException("Load not found"));

		load.setAwardedCarrierId(carrierId);

		load.setStatus("ASSIGNED");

		return loadRepository.save(load);
	}
}
