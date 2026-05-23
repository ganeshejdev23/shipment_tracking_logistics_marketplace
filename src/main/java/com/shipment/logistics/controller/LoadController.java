package com.shipment.logistics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shipment.logistics.entity.LoadPosting;
import com.shipment.logistics.service.LoadService;

@RestController
@RequestMapping("/loads")
public class LoadController {

	@Autowired
	private LoadService service;

	@PostMapping
	public LoadPosting createLoad(@RequestBody LoadPosting load) {

		return service.createLoad(load);
	}

	@GetMapping
	public List<LoadPosting> getAllLoads() {
		return service.getAllLoads();
	}
}
