package com.googlecode.tomcatlsd.lsdmanager.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlecode.tomcatlsd.lsdmanager.dao.ServerBrowserFolderDao;
import com.googlecode.tomcatlsd.lsdmanager.model.ServerBrowserFolder;

@Service
public class ServerBrowserService {

	private Log log = LogFactory.getLog(ServerBrowserService.class);

	@Autowired
	private ServerBrowserFolderDao serverGroupDao;

	public List<ServerBrowserFolder> getRootFolders() {
		List<ServerBrowserFolder> rootFolders = new ArrayList<ServerBrowserFolder>();
		List<ServerBrowserFolder> allFolders = this.serverGroupDao.findAll();
		for (ServerBrowserFolder aFolder : allFolders) {
			if (aFolder.getParent() == null) {
				rootFolders.add(aFolder);
			}
		}
		return rootFolders;
	}

}
