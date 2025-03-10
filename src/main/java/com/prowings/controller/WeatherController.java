package com.prowings.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.prowings.models.response.CityWeatherData;
import com.prowings.service.WeatherService;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class WeatherController {
	
	@Autowired
	WeatherService weatherService;
	
	@GetMapping("/weather/{cityName}")
	public CityWeatherData getWeather(@PathVariable String cityName) {
		
		log.info("Request received to fetch current weather data for city: {}", cityName);
		//calculate lat and long from city name
		Map<String, Double> latLong = getLatLong(cityName);
		
		//call weather api with lat and long and api key
		return weatherService.getWeatherByLatLong(latLong.get("lat"), latLong.get("long"));
	}

	private Map<String, Double> getLatLong(String cityName) {
		
		log.info("Fetching lat and long for city: {}", cityName);
		
		//call another api to get lat/long from city name
		
		Map<String, Double> latLong = new HashMap<>();
		latLong.put("lat", 18.6135);
		latLong.put("long", 73.7666);
		log.info("Lat and Long for city: {} is {} : {}", cityName, latLong.get("lat"), latLong.get("long"));
		return latLong;
	}


}
