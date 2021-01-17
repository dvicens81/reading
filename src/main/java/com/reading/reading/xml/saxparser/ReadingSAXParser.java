package com.reading.reading.xml.saxparser;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import com.reading.reading.dto.ClientDto;
import com.reading.reading.xml.handler.ReadingHandler;
/**
 * 
 * Read XML File by SAX Parser
 * 
 * 1-  Create SAX Parser
 * 
 * 2- Read XML File to SAX  with customize handler
 * 
 * @author 
 *
 */
public abstract class ReadingSAXParser {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReadingSAXParser.class);
	
	private ReadingSAXParser() {}	
	
	public static SAXParser createSaxParser() {
        SAXParser saxParser = null;
        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            saxParser = factory.newSAXParser();

            return saxParser;
        } catch (ParserConfigurationException | SAXException ex) {
            LOGGER.error(ex.getMessage());
        }
        return saxParser;
    }
	
	public static Map<String,ClientDto> executeSaxParser(InputStream fileInputStream, SAXParser parser) {
		ReadingHandler handler= new ReadingHandler();
		Map<String, ClientDto> mapClients = new LinkedHashMap<>();
		try {
			parser.parse(fileInputStream, handler);			
			mapClients = handler.getMapClients();
		} catch (SAXException | IOException e) {
			LOGGER.error(e.getMessage());
		}
		return mapClients;
	}

}
