package com.googlecode.tomcatlsd.common.model;

public class GarbageCollectorStatisticsSnapshot {
	
	private Long id;
	
	private String name;
	
	private long numberofcalls;
	
	private long accumulatedtime;
	
	private long averagetime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getNumberofcalls() {
		return numberofcalls;
	}

	public void setNumberofcalls(long numberofcalls) {
		this.numberofcalls = numberofcalls;
	}

	public long getAccumulatedtime() {
		return accumulatedtime;
	}

	public void setAccumulatedtime(long accumulatedtime) {
		this.accumulatedtime = accumulatedtime;
	}

	public long getAveragetime() {
		return averagetime;
	}

	public void setAveragetime(long averagetime) {
		this.averagetime = averagetime;
	}
	
	

}
