package com.googlecode.tomcatlsd.lsdmanager.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.googlecode.tomcatlsd.common.model.HeartBeat;

@Entity
public class RemoteHeartBeatBean {

	@Id
	@GeneratedValue
	private Long id;

	private Date date;

	private String server;

	@OneToOne
	@PrimaryKeyJoinColumn
	private HeartBeat details;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getServerName() {
		return server;
	}

	public void setServerName(String serverName) {
		this.server = serverName;
	}

	public HeartBeat getDetails() {
		return details;
	}

	public void setDetails(HeartBeat content) {
		this.details = content;
	}

}
