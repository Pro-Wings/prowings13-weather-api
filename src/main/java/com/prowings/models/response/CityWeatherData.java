package com.prowings.models.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CityWeatherData {
	
	private String cityName;
	private double temperature;
	private double humidity;
	private double feels_like;
	private long visibility;
	private String sunrise;
	private String sunset;

}
