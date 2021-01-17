package com.reading.reading.common;

import java.util.List;

import com.reading.reading.dto.ReadingDto;
/**
 * 
 * Functions that are common on the application
 * 
 * @author 
 *
 */
public abstract class ReadingCommon {
	
	public static final Integer DEFAULT_READ = 70000;
	
	private ReadingCommon() {}
	
	/**
	 * get the median of the client
	 * @param reading readings of the client
	 * @return
	 */
	public static double getMedian(List<ReadingDto> reading) {
		int size= reading.size();
		return reading.stream().mapToInt(ReadingDto::getReading).skip((size-1)/2).limit(2-size%2).average().orElse(Double.NaN);
	}
	/**
	 * Convert String to Integer
	 * @param value string
	 * @return
	 */
	public static Integer getIntegerFromString(String value) {
		Integer defaultRead = 0;
		try {
			defaultRead = new Integer(value);
		}catch (NumberFormatException e) {
			defaultRead = DEFAULT_READ;
		}
		return defaultRead;
	}

}
