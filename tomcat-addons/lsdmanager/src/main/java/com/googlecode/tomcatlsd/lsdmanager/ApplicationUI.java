package com.googlecode.tomcatlsd.lsdmanager;

import com.googlecode.tomcatlsd.lsdmanager.ui.MainPanel;
import com.googlecode.tomcatlsd.lsdmanager.util.spring.SpringDependencyInjector;
import com.vaadin.annotations.Push;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Push
public class ApplicationUI extends UI {
	
	public ApplicationUI() {
		super();
		SpringDependencyInjector.getInjector().inject(this);
	}

	public void init(VaadinRequest request) {
		VerticalLayout verticalLayout = new VerticalLayout();
		verticalLayout.setSizeFull();
		verticalLayout.addComponent(new MainPanel());
		setContent(verticalLayout);
	}
	


}
