package com.googlecode.tomcatlsd.lsdmanager.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.googlecode.tomcatlsd.common.model.ApplicationServer;

@Entity
public class ServerBrowserFolder {

	@Id
	@GeneratedValue
	private Long id;

	private String name;
	
	private ServerBrowserFolder parent;
	
	@OneToMany
	@JoinColumn(name="PARENT_FOLDER_ID")
	private Set<ServerBrowserFolder> children;
	
	@OneToMany
	@JoinColumn(name="FOLDER_ID")
	private Set<ApplicationServer> appServerList;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String groupName) {
		this.name = groupName;
	}

	public ServerBrowserFolder getParent() {
		return parent;
	}

	public void setParent(ServerBrowserFolder parent) {
		this.parent = parent;
	}

	public Set<ServerBrowserFolder> getChildren() {
		return children;
	}

	public void setChildren(Set<ServerBrowserFolder> subFolders) {
		this.children = subFolders;
	}

	public Set<ApplicationServer> getAppServerList() {
		return appServerList;
	}

	public void setAppServerList(Set<ApplicationServer> appServerInstances) {
		this.appServerList = appServerInstances;
	}
	
	


}
