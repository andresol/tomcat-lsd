package com.googlecode.tomcatlsd.common.model;

import java.io.Serializable;

public class ApplicationServer implements Serializable {

	private String hostname;

	private String alias;

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	@Override
	public String toString() {
		return alias + " [" + hostname + "]";
	}

}
