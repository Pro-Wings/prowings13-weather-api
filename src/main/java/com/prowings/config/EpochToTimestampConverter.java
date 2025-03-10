package com.prowings.config;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.dozer.CustomConverter;

public class EpochToTimestampConverter implements CustomConverter {

	@Override
	public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass,
			Class<?> sourceClass) {
		if (sourceFieldValue == null) {
			return null;
		}
		long epoch = (long) sourceFieldValue;
		String timestamp = convertEpochToTimestamp(epoch);
		System.out.println(">>>>>>Epoch : " + epoch + " to Timestamp : " + timestamp);
		return timestamp;
	}

	private String convertEpochToTimestamp(long epochSeconds) {
		// Convert epoch seconds to ZonedDateTime in IST (GMT+5:30)
		ZonedDateTime dateTime = Instant.ofEpochSecond(epochSeconds).atZone(ZoneId.of("Asia/Kolkata"));

		// Define date-time format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		// Format and return the timestamp
		return dateTime.format(formatter);
	}

}
