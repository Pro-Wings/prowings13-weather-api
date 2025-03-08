package com.prowings.service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prowings.models.CurrentWeatherData;
import com.prowings.models.response.CityWeatherData;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class WeatherService {

	@Autowired
	private RestTemplate restTemplate;

	private String apiKey = "aea2c2eaeb4020c7d96e8c22ce8d0bb2";

	private ObjectMapper objectMapper = new ObjectMapper();

	public CityWeatherData getWeatherByLatLong(Double lat, Double lon) {

		log.info("Fetching weather data from OpenWeather API for lat: {} and long: {}", lat, lon);

		String url = "https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon + "&appid=" + apiKey;

		log.info("URL: {}", url);
		log.info("Calling OpenWeather API...");
		ResponseEntity<CurrentWeatherData> response = restTemplate.getForEntity(url, CurrentWeatherData.class);
		log.info("Response received from OpenWeather API", response);

		if (response.getStatusCode().is2xxSuccessful()) {
			try {
				String jsonResponse = objectMapper.writerWithDefaultPrettyPrinter()
						.writeValueAsString(response.getBody());
				log.info("Weather API Response:\n{}", jsonResponse);
			} catch (Exception e) {
				log.error("Error converting response to JSON", e);
			}
		} else {
			log.error("Failed to fetch data. HTTP Status: {}", response.getStatusCode());
		}

		CurrentWeatherData currentWeatherData = response.getBody();

		return convertToCityWeatherData(currentWeatherData);

	}

	private CityWeatherData convertToCityWeatherData(CurrentWeatherData currentWeatherData) {

		log.info("Converting CurrentWeatherData to CityWeatherData...");
		CityWeatherData cityWeatherData = new CityWeatherData();

		cityWeatherData.setCityName(currentWeatherData.getName());
		cityWeatherData.setTemperature(kelvinToCelsius(currentWeatherData.getMain().getTemp()));
		cityWeatherData.setHumidity(currentWeatherData.getMain().getHumidity());
		cityWeatherData.setFeels_like(kelvinToCelsius(currentWeatherData.getMain().getFeels_like()));
		cityWeatherData.setVisibility(currentWeatherData.getVisibility());
		cityWeatherData.setSunrise(convertEpochToTimestamp(currentWeatherData.getSys().getSunrise()));
		cityWeatherData.setSunset(convertEpochToTimestamp(currentWeatherData.getSys().getSunset()));
		return cityWeatherData;
	}

	private double kelvinToCelsius(double kelvin) {
		return kelvin - 273.15;
	}

	public static String convertEpochToTimestamp(long epochSeconds) {
		// Convert epoch seconds to ZonedDateTime in IST (GMT+5:30)
		ZonedDateTime dateTime = Instant.ofEpochSecond(epochSeconds).atZone(ZoneId.of("Asia/Kolkata"));

		// Define date-time format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		// Format and return the timestamp
		return dateTime.format(formatter);
	}

}
