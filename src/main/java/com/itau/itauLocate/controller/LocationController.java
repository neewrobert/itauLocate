package com.itau.itauLocate.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.maps.GeoApiContext;

@RestController
public class LocationController {
	
	
	public void getGeocode(@RequestParam String address) throws UnsupportedEncodingException {
		
		GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyCmyZCs4slOhJW-b8J5gHLO_5kpvVU2-oU").build();
		
	}
	
	

}
