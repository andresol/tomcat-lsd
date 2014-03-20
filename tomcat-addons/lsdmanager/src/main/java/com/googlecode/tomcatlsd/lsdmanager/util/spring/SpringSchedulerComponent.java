package com.googlecode.tomcatlsd.lsdmanager.util.spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SpringSchedulerComponent {

	private List<ISpringScheduledComponent> components;

	public SpringSchedulerComponent() {
		this.components = new ArrayList<ISpringScheduledComponent>();
	}

	public void register(ISpringScheduledComponent component) {
		this.components.add(component);
	}

	public void unregister(ISpringScheduledComponent component) {
		this.components.remove(component);
	}

	
	@Scheduled(fixedRate = 5000)
	public void doAllWork() {
		for (ISpringScheduledComponent component : this.components) {
			component.doScheduledTask();
		}
	}
}