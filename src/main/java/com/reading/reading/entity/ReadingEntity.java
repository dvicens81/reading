package com.reading.reading.entity;

public class ReadingEntity {
	
	private String period;
	private Integer reading;
	
	public ReadingEntity() {
		this.period = null;
		this.reading = 0;
	}
	
	public ReadingEntity(String period, Integer reading) {
		this.period = period;
		this.reading = reading;
	}
	
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public Integer getReading() {
		return reading;
	}
	public void setReading(Integer reading) {
		this.reading = reading;
	}
	
	
}
