package com.reading.reading.dto;

import java.util.LinkedList;
import java.util.List;

public final class ClientDto {
	
	private String id;
	private List<ReadingDto> readingClient;
	
	public ClientDto() {
		this.id = null;
		this.readingClient = new LinkedList<>();
	}
	
	public ClientDto(String id) {
		this.id = id;
		this.readingClient = new LinkedList<>();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<ReadingDto> getReadingClient() {
		return readingClient;
	}

	public void setReadingClient(List<ReadingDto> readingClient) {
		this.readingClient = readingClient;
	}	
}
