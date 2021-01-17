package com.reading.reading.print;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.reading.reading.convert.ClientDtoToClientEntityConvert;
import com.reading.reading.dto.ClientDto;
import com.reading.reading.entity.ClientEntity;
/**
 * 1 - Get the clients processed in file
 * 2 - Get the suspicious Clients
 *
 * @author
 *
 */
public abstract class ResultToPrint {
	
	private static Integer limitRead;
	
	private ResultToPrint() {}
	
	public static void executeResult(Map<String, ClientDto> mapClients) {
		ReadingPrint.printHeader();
		List<ClientEntity> suspiciousClients = getSuspiciousClients(mapClients);
		suspiciousClients.stream().forEach(ReadingPrint::printResults);
		ReadingPrint.printFooter();

	}
	
	public static List<ClientEntity> getSuspiciousClients(Map<String, ClientDto> mapClients) {
		List<ClientDto> clients = mapClients.values().stream().collect(Collectors.toList());
		return clients.stream().map(ResultToPrint::getSuspicious).flatMap(List::stream).collect(Collectors.toList());		
	}
	
	private static List<ClientEntity> getSuspicious(ClientDto client) {
		return client.getReadingClient()
						.stream()
						.filter(reading->reading.getReading()>limitRead)
						.map(reading->ClientDtoToClientEntityConvert.convert(client, reading.getPeriod(), reading.getReading()))
						.collect(Collectors.toList());
			
	}
	
	public static void setLimitRead(Integer limitRead) {
		ResultToPrint.limitRead = limitRead;
	}

}
