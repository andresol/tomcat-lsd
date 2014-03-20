package com.googlecode.tomcatlsd.lsdagent.service;

import com.googlecode.tomcatlsd.common.model.ApplicationServer;
import com.googlecode.tomcatlsd.lsdagent.util.injection.ManiocFramework.ManagedBean;


@ManagedBean
public class ApplicationServerService {
	
	private String aliasname = System.getProperty("catalina.instance", "unknown-tomcat-check-config");
	
	private String hostname = System.getProperty("hostname.alias", "unknown-host-check-config");
	
	public ApplicationServer getDescription() {
		ApplicationServer instance = new ApplicationServer();
		instance.setAlias(this.aliasname);
		instance.setHostname(this.hostname);
		return instance;
	}

}
