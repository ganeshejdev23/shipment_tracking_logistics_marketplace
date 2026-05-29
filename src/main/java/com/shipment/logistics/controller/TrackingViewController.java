package com.shipment.logistics.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TrackingViewController {

	@GetMapping("/tracking-page")
	public String trackingPage() {

		return "tracking";
	}
}