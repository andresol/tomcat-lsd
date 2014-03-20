package com.googlecode.tomcatlsd.common.model;

import java.util.List;

public class ApplicationList
{
	
	private Long id;
    
    private List<ApplicationDescription> applications;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<ApplicationDescription> getApplications()
    {
        return applications;
    }

    public void setApplications(List<ApplicationDescription> appList)
    {
        this.applications = appList;
    }
    
    
    

}
