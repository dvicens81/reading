package com.reading.reading.entity;

public class ClientEntity {

	private String id;
	private double median;
	private String month;
	private Integer reading;
	
	public ClientEntity() {}
	
	public ClientEntity(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public double getMedian() {
		return median;
	}
	public void setMedian(double median) {
		this.median = median;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Integer getReading() {
		return reading;
	}

	public void setReading(Integer reading) {
		this.reading = reading;
	}
}
