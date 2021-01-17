package com.reading.reading.print;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.reading.reading.dto.ClientDto;
import com.reading.reading.dto.ReadingDto;
import com.reading.reading.entity.ClientEntity;


@RunWith(SpringRunner.class)
public class ResultToPrintTest {
	
	private static final String ID_CLIENT_1 = "1";
	private static final String ID_CLIENT_PERIOD_1 = "2016-12";
	private static final int ID_CLIENT_READING_1 = 10;
	private static final String ID_CLIENT_1_PERIOD_2 = "2015-12";
	private static final int ID_CLIENT_1_READING_2 = 20;
	
	private static final String ID_CLIENT_2 = "2";
	private static final String ID_CLIENT_PERIOD_2 = "2014-10";
	private static final int ID_CLIENT_READING_2 = 30;
	private static final String ID_CLIENT_2_PERIOD_2 = "2013-09";
	private static final int ID_CLIENT_2_READING_2 = 15;
	
	private static Map<String, ClientDto> mapClients;
	
	@BeforeAll
	public static void init() {
		mapClients = new LinkedHashMap<String, ClientDto>();
		//CLIENT 1
		ClientDto clientDto = new ClientDto(ID_CLIENT_1);
		ReadingDto readingDto = new ReadingDto();
		readingDto.setPeriod(ID_CLIENT_PERIOD_1);
		readingDto.setReading(ID_CLIENT_READING_1);
		
		ReadingDto readingDto2 = new ReadingDto();
		readingDto2.setPeriod(ID_CLIENT_1_PERIOD_2);
		readingDto2.setReading(ID_CLIENT_1_READING_2);
		
		clientDto.setReadingClient(Stream.of(readingDto, readingDto2).collect(Collectors.toList()));
		
		mapClients.put(ID_CLIENT_1, clientDto);
		
		//CLIENT 2
		ClientDto clientDto2 = new ClientDto(ID_CLIENT_2);
		ReadingDto readingDto21 = new ReadingDto();
		readingDto21.setPeriod(ID_CLIENT_PERIOD_2);
		readingDto21.setReading(ID_CLIENT_READING_2);
		
		ReadingDto readingDto22 = new ReadingDto();
		readingDto22.setPeriod(ID_CLIENT_2_PERIOD_2);
		readingDto22.setReading(ID_CLIENT_2_READING_2);
		
		clientDto2.setReadingClient(Stream.of(readingDto21, readingDto22).collect(Collectors.toList()));
		
		mapClients.put(ID_CLIENT_2, clientDto2);
	}
	
	@AfterAll
	public static void cleanUp() {
		mapClients = null;
	}
	
	@Test
	public void resultToPrintLimit10() {
		//SET limit in 12
		ResultToPrint.setLimitRead(10);
		//execute
		List<ClientEntity> response = ResultToPrint.getSuspiciousClients(mapClients);
		assertEquals(3, response.size());
		assertEquals(ID_CLIENT_1, response.get(0).getId());
		assertEquals(ID_CLIENT_2, response.get(1).getId());
		assertEquals(ID_CLIENT_2, response.get(2).getId());
		assertEquals("2015", response.get(0).getMonth());
		assertEquals("2014", response.get(1).getMonth());
		assertEquals("2013", response.get(2).getMonth());
		assertEquals(ID_CLIENT_1_READING_2, response.get(0).getReading());
		assertEquals(ID_CLIENT_READING_2, response.get(1).getReading());
		assertEquals(ID_CLIENT_2_READING_2, response.get(2).getReading());
	}
	
	@Test
	public void resultToPrintLimit25() {
		//SET limit in 12
		ResultToPrint.setLimitRead(25);
		//execute
		List<ClientEntity> response = ResultToPrint.getSuspiciousClients(mapClients);
		assertEquals(1, response.size());
		assertEquals(ID_CLIENT_2, response.get(0).getId());
		assertEquals("2014", response.get(0).getMonth());
		assertEquals(ID_CLIENT_READING_2, response.get(0).getReading());
	}

}
