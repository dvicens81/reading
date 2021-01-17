package com.reading.reading.convert;

import com.reading.reading.common.ReadingCommon;
import com.reading.reading.dto.ClientDto;
import com.reading.reading.entity.ClientEntity;

/**
 * Convert CLientDto to ClientEntity
 * @author 
 *
 */

public interface ClientDtoToClientEntityConvert {
	
	public static ClientEntity convert(ClientDto clientDto, String period, Integer reading) {
		ClientEntity clientEntity = new ClientEntity(clientDto.getId());
		clientEntity.setMedian(ReadingCommon.getMedian(clientDto.getReadingClient()));
		clientEntity.setMonth(period.substring(0, 4));
		clientEntity.setReading(reading);
		return clientEntity;
	}
	

}
