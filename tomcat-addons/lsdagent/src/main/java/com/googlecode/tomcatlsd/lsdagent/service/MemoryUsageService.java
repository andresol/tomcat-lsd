package com.googlecode.tomcatlsd.lsdagent.service;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

import com.googlecode.tomcatlsd.common.model.MemoryUsageSnapshot;
import com.googlecode.tomcatlsd.common.model.MemoryUsageDetailSnapshot;
import com.googlecode.tomcatlsd.lsdagent.util.injection.ManiocFramework.Inject;
import com.googlecode.tomcatlsd.lsdagent.util.injection.ManiocFramework.ManagedBean;

@ManagedBean
public class MemoryUsageService {

	private static int ONE_MB = 1024 * 1024;

	public MemoryUsageSnapshot getCurrentMemoryStatus() {
		MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
		MemoryUsage heapMemoryUsage = memoryBean.getHeapMemoryUsage();
		MemoryUsage nonHeapMemoryUsage = memoryBean.getNonHeapMemoryUsage();
		MemoryUsageDetailSnapshot heapMemoryUsageBean = getMemoryUsageBean(heapMemoryUsage);
		MemoryUsageDetailSnapshot nonHeapMemoryUsageBean = getMemoryUsageBean(nonHeapMemoryUsage);
		MemoryUsageDetailSnapshot totalMemoryUsageBean = getTotalMemoryUsageBean(heapMemoryUsageBean, nonHeapMemoryUsageBean);
		MemoryUsageSnapshot memoryFacade = new MemoryUsageSnapshot();
		memoryFacade.setHeap(heapMemoryUsageBean);
		memoryFacade.setNonheap(nonHeapMemoryUsageBean);
		memoryFacade.setTotal(totalMemoryUsageBean);
		return memoryFacade;
	}
	
	
	

	private MemoryUsageDetailSnapshot getMemoryUsageBean(MemoryUsage memoryUsage) {
		int init = (int) memoryUsage.getInit() / ONE_MB;
		int max = (int) memoryUsage.getMax() / ONE_MB;
		int used = (int) memoryUsage.getUsed() / ONE_MB;
		int committed = (int) memoryUsage.getCommitted() / ONE_MB;
		int ratio = Math.round(100 * used / max);
		MemoryUsageDetailSnapshot memoryUsageBean = new MemoryUsageDetailSnapshot();
		memoryUsageBean.setInit(init);
		memoryUsageBean.setUsed(used);
		memoryUsageBean.setMax(max);
		memoryUsageBean.setCommitted(committed);
		memoryUsageBean.setRatio(ratio);
		return memoryUsageBean;
	}
	
	private MemoryUsageDetailSnapshot getTotalMemoryUsageBean(MemoryUsageDetailSnapshot heapMemoryUsageBean, MemoryUsageDetailSnapshot nonHeapMemoryUsageBean) {
		int init = heapMemoryUsageBean.getInit() + nonHeapMemoryUsageBean.getInit();
		int max = heapMemoryUsageBean.getMax() + nonHeapMemoryUsageBean.getMax();
		int used = heapMemoryUsageBean.getUsed() + nonHeapMemoryUsageBean.getUsed();
		int committed = heapMemoryUsageBean.getCommitted() + nonHeapMemoryUsageBean.getCommitted();
		int ratio = Math.round(100 * used / max);
		MemoryUsageDetailSnapshot memoryUsageBean = new MemoryUsageDetailSnapshot();
		memoryUsageBean.setInit(init);
		memoryUsageBean.setUsed(used);
		memoryUsageBean.setMax(max);
		memoryUsageBean.setCommitted(committed);
		memoryUsageBean.setRatio(ratio);
		return memoryUsageBean;
	}

}
