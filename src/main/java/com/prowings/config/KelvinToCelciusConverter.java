package com.prowings.config;

import org.dozer.CustomConverter;

public class KelvinToCelciusConverter implements CustomConverter{
	
	@Override
	public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass,
			Class<?> sourceClass) {
		if (sourceFieldValue == null) {
			return null;
		}
		double kelvin = (double) sourceFieldValue;
		double celsius = kelvin - 273.15f;
		System.out.println("Kelvin : " + kelvin + " to Celsius : " + celsius);
		return celsius;
	}

}
