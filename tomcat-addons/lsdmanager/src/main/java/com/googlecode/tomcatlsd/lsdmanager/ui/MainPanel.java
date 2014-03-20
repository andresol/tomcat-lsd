package com.googlecode.tomcatlsd.lsdmanager.ui;

import com.googlecode.tomcatlsd.lsdmanager.ui.views.server.ServerBrowserPanel;
import com.googlecode.tomcatlsd.lsdmanager.util.spring.SpringDependencyInjector;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

public class MainPanel extends Panel {

	private Panel fullPagePanel;
	private Panel workPanel;


	public MainPanel() {
		super();
		SpringDependencyInjector.getInjector().inject(this);
		setContent(getFullPagePanel());
		changeContent(new ServerBrowserPanel());
		setStyleName(Reindeer.PANEL_LIGHT);
		setSizeFull();
	}



	public void changeContent(Panel newContentPanel) {
		Layout layout = (Layout) getWorkPanel().getContent();
		layout.removeAllComponents();
		layout.addComponent(newContentPanel);
	}


	private Panel getFullPagePanel() {
		if (this.fullPagePanel == null) {
			this.fullPagePanel = new Panel();
			VerticalLayout layout = new VerticalLayout();
			layout.setSizeFull();
			layout.setSpacing(false);
			layout.setMargin(false);
			layout.addComponent(getWorkPanel());
			this.fullPagePanel.setContent(layout);
			this.fullPagePanel.setSizeFull();
			this.fullPagePanel.setStyleName(Reindeer.PANEL_LIGHT);
		}
		return this.fullPagePanel;
	}

	private Panel getWorkPanel() {
		if (this.workPanel == null) {
			this.workPanel = new Panel();
			VerticalLayout layout = new VerticalLayout();
			layout.setSizeFull();
			layout.setSpacing(false);
			layout.setMargin(false);
			this.workPanel.setContent(layout);
			this.workPanel.setSizeFull();
			this.workPanel.setStyleName(Reindeer.PANEL_LIGHT);
		}
		return this.workPanel;
	}


}
