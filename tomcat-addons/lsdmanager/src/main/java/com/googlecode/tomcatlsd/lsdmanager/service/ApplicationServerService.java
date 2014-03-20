package com.googlecode.tomcatlsd.lsdmanager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlecode.tomcatlsd.common.model.ApplicationServer;
import com.googlecode.tomcatlsd.lsdmanager.dao.ApplicationServerDao;
import com.googlecode.tomcatlsd.lsdmanager.listener.ApplicationServerListener;
import com.googlecode.tomcatlsd.lsdmanager.model.ServerBrowserFolder;

@Service
public class ApplicationServerService {

	@Autowired
	private ApplicationServerDao appServerDao;
	
	private List<ApplicationServerListener> listeners = new ArrayList<ApplicationServerListener>();

	public void addListener(ApplicationServerListener aListener) {
		this.listeners.add(aListener);
	}
	
	public void removeListener(ApplicationServerListener aListener) {
		this.listeners.remove(aListener);
	}
	
	private void fireServerAddedToGroup(ApplicationServer appServer) {
		for (ApplicationServerListener aListener : this.listeners) {
			aListener.onApplicationServerAdded(appServer);
		}
	}

	public boolean isExistInDatabase(ApplicationServer appServer) {
		String alias = appServer.getAlias();
		String hostname = appServer.getHostname();
		List<ApplicationServer> result = appServerDao.findByAliasAndHostname(alias, hostname);
		if (result == null) {
			return false;
		}
		if (result.size() > 0) {
			return true;
		}
		return false;
	}
	
	
	public void save(ApplicationServer appServer) {
		if (!isExistInDatabase(appServer)) {
			this.appServerDao.save(appServer);
			fireServerAddedToGroup(appServer);
		}
	}
	

}
