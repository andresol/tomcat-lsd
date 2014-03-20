package com.googlecode.tomcatlsd.common.model;


public class MemoryUsageSnapshot {

	private Long id;
	
	private MemoryUsageDetailSnapshot heap;

	private MemoryUsageDetailSnapshot nonheap;

	private MemoryUsageDetailSnapshot total;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MemoryUsageDetailSnapshot getHeap() {
		return heap;
	}

	public void setHeap(MemoryUsageDetailSnapshot heap) {
		this.heap = heap;
	}

	public MemoryUsageDetailSnapshot getNonheap() {
		return nonheap;
	}

	public void setNonheap(MemoryUsageDetailSnapshot nonheap) {
		this.nonheap = nonheap;
	}

	public MemoryUsageDetailSnapshot getTotal() {
		return total;
	}

	public void setTotal(MemoryUsageDetailSnapshot total) {
		this.total = total;
	}

}
