package com.googlecode.tomcatlsd.lsdagent.service;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.tomcatlsd.common.model.GarbageCollectorStatisticsSnapshot;
import com.googlecode.tomcatlsd.lsdagent.util.injection.ManiocFramework.Inject;
import com.googlecode.tomcatlsd.lsdagent.util.injection.ManiocFramework.ManagedBean;

@ManagedBean
public class GarbageCollectorService {
	
	private static final String GLOBAL_GC_NAME = "total";
	
	public List<GarbageCollectorStatisticsSnapshot> getAllInfo() {
		long totalNumberOfCalls = 0;
		long totalAccumulatedTime = 0;
		List<GarbageCollectorStatisticsSnapshot> gcList = new ArrayList<GarbageCollectorStatisticsSnapshot>();
		List<GarbageCollectorMXBean> mxBeans = getMXBeans();
		for (GarbageCollectorMXBean aBean : mxBeans) {
			GarbageCollectorStatisticsSnapshot infoBean = new GarbageCollectorStatisticsSnapshot();
			String name = aBean.getName();
			long numberOfCalls = aBean.getCollectionCount();
			long accumulatedTime = aBean.getCollectionTime();
			long averageTime = accumulatedTime / numberOfCalls;
			if (numberOfCalls < 0) numberOfCalls = 0;
			if (accumulatedTime < 0) accumulatedTime = 0;
			totalAccumulatedTime = totalAccumulatedTime + accumulatedTime;
			totalNumberOfCalls = totalNumberOfCalls + numberOfCalls;
			infoBean.setName(name);
			infoBean.setNumberofcalls(numberOfCalls);
			infoBean.setAccumulatedtime(accumulatedTime);
			infoBean.setAveragetime(averageTime);
			gcList.add(infoBean);
		}
		long totalAverageTime = totalAccumulatedTime / totalNumberOfCalls;
		GarbageCollectorStatisticsSnapshot globalInfoBean = new GarbageCollectorStatisticsSnapshot();
		globalInfoBean.setName(GLOBAL_GC_NAME);
		globalInfoBean.setNumberofcalls(totalNumberOfCalls);
		globalInfoBean.setAccumulatedtime(totalAccumulatedTime);
		globalInfoBean.setAveragetime(totalAverageTime);
		gcList.add(globalInfoBean);
		return gcList;
	}
	
	private List<GarbageCollectorMXBean> getMXBeans() {
		List<GarbageCollectorMXBean> garbageCollectorMXBeans = ManagementFactory.getGarbageCollectorMXBeans();
		return garbageCollectorMXBeans;
	}

}
