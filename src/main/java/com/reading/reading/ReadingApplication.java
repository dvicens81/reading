package com.reading.reading;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.reading.reading.common.ReadingCommon;
import com.reading.reading.dispatcher.ReadingDispatcher;
import com.reading.reading.print.ResultToPrint;

/***
 * 
 * Start of the application.
 * Take the filename and the limit to take the reading like suspicious,
 * 
 * @author 
 *
 */
@SpringBootApplication
public class ReadingApplication implements CommandLineRunner{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReadingApplication.class);
	private static final String FOLDER_NAME = "/files/";
	private static final String DEFAULT_FILE = "2016-readings.xml";
	
	public static void main(String[] args) {
		SpringApplication.run(ReadingApplication.class, args);
	}

	
	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("Processing ...");
		//Read name file. If not arguments are passed. Then throw error
		String fileName = Optional.ofNullable(args).filter(argument-> argument.length > 0).map(argument->FOLDER_NAME + argument[0]).orElse(FOLDER_NAME + DEFAULT_FILE);
		//SET the read value that for us is suspicious to show in this application
		ResultToPrint.setLimitRead(Optional.ofNullable(args).filter(argument-> argument.length > 1).map(argument->new Integer(argument[1])).orElse(ReadingCommon.DEFAULT_READ));
		//Send to the dispatcher the filename
		ReadingDispatcher.dispatcher(fileName);
		LOGGER.info("Finished");
	}
	
}
