package com.reading.reading.saxparser;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

import javax.xml.parsers.SAXParser;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.reading.reading.dto.ClientDto;
import com.reading.reading.xml.saxparser.ReadingSAXParser;

@RunWith(SpringRunner.class)
public class ReadingSAXParserTest {
	
	private static final String ID_CLIENT_1 = "583ef6329df6b";
	private static final String ID_CLIENT_2 = "583ef6329e41b";
	private static final Integer READING_1 = 37232;
	private static final Integer READING_2 = 36537;
	private static final Integer READING_3 = 31033;
	private static final Integer READING_4 = 32075;
	private static final Integer READING_5 = 31537;
	
	
	@Test
	public void testSaxParser() throws FileNotFoundException {
		String resourceName = "src/test/resources/2016-readings-test.xml";

		File file = new File(resourceName);
		InputStream fileInputStream = new DataInputStream(new FileInputStream(file));
		SAXParser parser = ReadingSAXParser.createSaxParser();
		Map<String, ClientDto> response = ReadingSAXParser.executeSaxParser(fileInputStream, parser);
		assertTrue(response.containsKey(ID_CLIENT_1));
		assertTrue(response.containsKey(ID_CLIENT_2));
		assertEquals(2, response.size());
		assertEquals(2, response.get(ID_CLIENT_1).getReadingClient().size());
		assertEquals(3, response.get(ID_CLIENT_2).getReadingClient().size());
		assertEquals(READING_1, response.get(ID_CLIENT_1).getReadingClient().get(0).getReading());
		assertEquals(READING_2, response.get(ID_CLIENT_1).getReadingClient().get(1).getReading());
		assertEquals(READING_3, response.get(ID_CLIENT_2).getReadingClient().get(0).getReading());
		assertEquals(READING_4, response.get(ID_CLIENT_2).getReadingClient().get(1).getReading());
		assertEquals(READING_5, response.get(ID_CLIENT_2).getReadingClient().get(2).getReading());
	}

}
