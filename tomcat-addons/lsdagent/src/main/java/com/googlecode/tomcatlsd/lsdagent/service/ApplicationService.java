package com.googlecode.tomcatlsd.lsdagent.service;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

import com.googlecode.tomcatlsd.common.model.ApplicationDescription;
import com.googlecode.tomcatlsd.common.model.ApplicationList;
import com.googlecode.tomcatlsd.lsdagent.util.injection.ManiocFramework.ManagedBean;


@ManagedBean
public class ApplicationService {
	
	public ApplicationList getDeployedApps() throws MalformedObjectNameException, NullPointerException {
	    ApplicationList appsInfoBean = new ApplicationList();
	    List<ApplicationDescription> deployedApps = new ArrayList<ApplicationDescription>();
		ObjectName name=new ObjectName("Catalina:type=Manager,path=/*,host=localhost");
		MBeanServer platformMBeanServer = ManagementFactory.getPlatformMBeanServer();
		Set<ObjectName> names = platformMBeanServer.queryNames(name, null);
		for (ObjectName aReturnedName : names) {
		    ApplicationDescription appInfoBean = new ApplicationDescription();
		    String path = aReturnedName.getKeyProperty("path");
		    appInfoBean.setPath(path);
			deployedApps.add(appInfoBean);
		}
		appsInfoBean.setApplications(deployedApps);
		return appsInfoBean;
		
		//ManagementFactory.getPlatformMBeanServer().getAttribute(name, "activeSessions");
	}

}
