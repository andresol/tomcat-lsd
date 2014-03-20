package com.googlecode.tomcatlsd.common.model;

public class MemoryUsageDetailSnapshot {
	
	private Long id;
	
	private int init;
	
	private int used;
	
	private int max;
	
	private int committed;
	
	private int ratio;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getInit() {
		return init;
	}

	public void setInit(int init) {
		this.init = init;
	}

	public int getUsed() {
		return used;
	}

	public void setUsed(int used) {
		this.used = used;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getCommitted() {
		return committed;
	}

	public void setCommitted(int committed) {
		this.committed = committed;
	}

	public int getRatio() {
		return ratio;
	}

	public void setRatio(int ratio) {
		this.ratio = ratio;
	}
	
	

}
