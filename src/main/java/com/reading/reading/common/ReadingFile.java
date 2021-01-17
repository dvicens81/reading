package com.reading.reading.common;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.ClassPathResource;
/**
 * 
 * Get the inputStream from fileName
 * @author 
 *
 */
public interface ReadingFile {
	
	public static InputStream getInputStreamFromFileName(String fileName) throws IOException {
		return new ClassPathResource(fileName).getInputStream();
	}

}
