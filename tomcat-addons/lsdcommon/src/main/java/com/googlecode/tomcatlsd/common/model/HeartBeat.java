package com.googlecode.tomcatlsd.common.model;

import java.util.Date;

public class HeartBeat {

	private Long id;

	private ApplicationServer applicationServer;

	private ApplicationList applicationList;

	private MemoryUsageSnapshot memoryUsageSnapshot;
	
	private Date receptionDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ApplicationServer getApplicationServer() {
		return applicationServer;
	}

	public void setApplicationServer(ApplicationServer tomcatInstance) {
		this.applicationServer = tomcatInstance;
	}

	public ApplicationList getApplicationList() {
		return applicationList;
	}

	public void setApplicationList(ApplicationList applicationListSnapshot) {
		this.applicationList = applicationListSnapshot;
	}

	public MemoryUsageSnapshot getMemoryUsageSnapshot() {
		return memoryUsageSnapshot;
	}

	public void setMemoryUsageSnapshot(MemoryUsageSnapshot memoryUsageSnapshot) {
		this.memoryUsageSnapshot = memoryUsageSnapshot;
	}

	public Date getReceptionDate() {
		return receptionDate;
	}

	public void setReceptionDate(Date receptionDate) {
		this.receptionDate = receptionDate;
	}


	
	

}
