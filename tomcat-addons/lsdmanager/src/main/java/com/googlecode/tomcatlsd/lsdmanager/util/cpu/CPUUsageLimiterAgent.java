package com.googlecode.tomcatlsd.lsdmanager.util.cpu;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.Date;

public class CPUUsageLimiterAgent {
	
	private void getCPUUage() {
		ThreadMXBean TMB = ManagementFactory.getThreadMXBean();
		long time = new Date().getTime() * 1000000;
		long cput = 0;
		double cpuperc = -1;
		 
		//Begin loop.
		 
		if( TMB.isThreadCpuTimeSupported() )
		{
		        if(new Date().getTime() * 1000000 - time > 1000000000) //Reset once per second
		        {
		            time = new Date().getTime() * 1000000;
		            cput = TMB.getCurrentThreadCpuTime();
		        }
		                     
		        if(!TMB.isThreadCpuTimeEnabled())
		        {
		            TMB.setThreadCpuTimeEnabled(true);
		        }
		                     
		        if(new Date().getTime() * 1000000 - time != 0)
		            cpuperc = (TMB.getCurrentThreadCpuTime() - cput) / (new Date().getTime() * 1000000.0 - time) * 100.0;               
		}
		else
		{
		    cpuperc = -2;
		}
		
	}

}
