package com.mercadolibre.pens_luis_bootcamp_final.controller;

import com.newrelic.api.agent.NewRelic;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

	@GetMapping("/ping")
	public String ping() {
		NewRelic.ignoreTransaction();
		return "pong";
	}
}
