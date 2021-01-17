package com.reading.reading.dispatcher;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
 * Depends of the extension file. This class will send to correct class to process the file
 * 
 * @author 
 *
 */
public abstract class ReadingDispatcher {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReadingDispatcher.class);
	private static final String XML = "xml";
	private static final String CSV = "csv";
	private static final String METHOD_READ = "read";
	private static final String XML_CLASS_NAME = "com.reading.reading.read.xml.ReadingXmlParser";
	private static final String CSV_CLASS_NAME = "com.reading.reading.read.csv.ReadingCSVParser";
	
	private ReadingDispatcher() {}	
	
	public static void dispatcher(String fileName) {
		//XML
		Optional.of(fileName)
				.filter(file->file.contains(XML))
				.ifPresent(file->processDispatcher(file, XML_CLASS_NAME));
		//CSV File
		Optional.of(fileName)
				.filter(file->file.contains(CSV))
				.ifPresent(file->processDispatcher(file, CSV_CLASS_NAME));
	}
	/**
	 * By Reflection call method read depending the extension of the file
	 * @param fileName name of the file
	 * @param className to execute
	 */
	private static void processDispatcher(String fileName, String className) {
		try {
			Class<?> parserClass = Class.forName(className);
			Object parser = parserClass.newInstance();
	        Method setNameMethod = parser.getClass().getMethod(METHOD_READ, String.class);
	        setNameMethod.invoke(parser, fileName);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException 
					| NoSuchMethodException | SecurityException | IllegalArgumentException 
						| InvocationTargetException e) {
			LOGGER.error(e.getMessage());
		}
		
	}

}
