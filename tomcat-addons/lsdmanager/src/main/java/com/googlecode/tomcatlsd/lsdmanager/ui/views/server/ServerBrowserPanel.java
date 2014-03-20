package com.googlecode.tomcatlsd.lsdmanager.ui.views.server;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.googlecode.tomcatlsd.common.model.ApplicationServer;
import com.googlecode.tomcatlsd.lsdmanager.dao.ServerBrowserFolderDao;
import com.googlecode.tomcatlsd.lsdmanager.listener.ApplicationServerListener;
import com.googlecode.tomcatlsd.lsdmanager.model.ServerBrowserFolder;
import com.googlecode.tomcatlsd.lsdmanager.service.ApplicationServerService;
import com.googlecode.tomcatlsd.lsdmanager.service.ServerBrowserService;
import com.googlecode.tomcatlsd.lsdmanager.util.spring.SpringDependencyInjector;
import com.vaadin.data.Container.Hierarchical;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

public class ServerBrowserPanel extends Panel implements ApplicationServerListener {

	private TreeTable treeTable;

	private Panel leftPanel;

	private Panel rightPanel;

	@Autowired
	private ServerBrowserFolderDao serverGroupDao;

	@Autowired
	private ServerBrowserService browserService;

	@Autowired
	private ApplicationServerService appServerService;

	public ServerBrowserPanel() {
		super();
		SpringDependencyInjector.getInjector().inject(this);
		setStyleName(Reindeer.PANEL_LIGHT);
		GridLayout gridLayout = new GridLayout(2, 1);
		gridLayout.setSpacing(false);
		gridLayout.setMargin(false);
		gridLayout.addComponent(getLeftPanel(), 0, 0);
		gridLayout.addComponent(getRightPanel(), 1, 0);
		gridLayout.setColumnExpandRatio(0, 0.3f);
		gridLayout.setColumnExpandRatio(1, 0.7f);
		gridLayout.setSizeFull();
		setContent(gridLayout);
		setSizeFull();
	}

	@Override
	public void attach() {
		super.attach();
		this.appServerService.addListener(this);
	}

	@Override
	public void detach() {
		this.appServerService.removeListener(this);
		super.detach();
	}

	@Override
	public void onApplicationServerAdded(ApplicationServer appServer) {
		TreeTable tree = getTreeTable();
		Hierarchical datasource = tree.getContainerDataSource();
		addApplicationServer(datasource, appServer);
	}

	private Panel getLeftPanel() {
		if (this.leftPanel == null) {
			this.leftPanel = new Panel();
			VerticalLayout layout = new VerticalLayout();
			layout.setSizeFull();
			layout.setSpacing(false);
			layout.setMargin(false);
			layout.addComponent(getTreeTable());
			this.leftPanel.setContent(layout);
			this.leftPanel.setSizeFull();
		}
		return this.leftPanel;
	}

	private Panel getRightPanel() {
		if (this.rightPanel == null) {
			this.rightPanel = new Panel();
			this.rightPanel.setVisible(false);
		}
		return this.rightPanel;
	}

	private TreeTable getTreeTable() {
		if (this.treeTable == null) {
			this.treeTable = new TreeTable();
			HierarchicalContainer hierarchicalContainer = new HierarchicalContainer();
			hierarchicalContainer.addContainerProperty("name", String.class, "");
			this.treeTable.setContainerDataSource(hierarchicalContainer);
			this.treeTable.setSizeFull();
			List<ServerBrowserFolder> folderStack = this.browserService.getRootFolders();
			while (!folderStack.isEmpty()) {
				ServerBrowserFolder folder = folderStack.get(0);
				Item folderItem = hierarchicalContainer.addItem(folder);
				Property<String> itemProperty = folderItem.getItemProperty("name");
				itemProperty.setValue(folder.getName());
				Set<ServerBrowserFolder> subFolders = folder.getChildren();
				boolean hasChildren = subFolders.size() > 0;
				hierarchicalContainer.setChildrenAllowed(folder, hasChildren);
				ServerBrowserFolder parentFolder = folder.getParent();
				if (parentFolder != null) {
					hierarchicalContainer.setParent(folder, parentFolder);
				}
				folderStack.addAll(subFolders);
				folderStack.remove(0);
				Set<ApplicationServer> appServerList = folder.getAppServerList();
				for (ApplicationServer appServer : appServerList) {
					Item appServerItem = hierarchicalContainer.addItem(appServer);
					Property<String> appServerItemProperty = folderItem.getItemProperty("name");
					itemProperty.setValue(appServer.toString());
					hierarchicalContainer.setChildrenAllowed(appServer, false);
					hierarchicalContainer.setParent(appServer, folder);
				}
			}
			this.treeTable.setContainerDataSource(hierarchicalContainer);
			this.treeTable.setSizeFull();
		}
		return this.treeTable;
	}

	private void addApplicationServer(Hierarchical datasource, ApplicationServer appServer) {
		Item appServerItem = datasource.addItem(appServer);
		boolean isAlreadyAdded = (appServerItem == null);
		if (isAlreadyAdded) {
			return;
		}
		Property<String> appServerItemProperty = appServerItem.getItemProperty("name");
		appServerItemProperty.setValue(appServer.toString());
		datasource.setChildrenAllowed(appServer, false);
		datasource.setParent(appServer, null);
	}

	// private void addFolder(Hierarchical datasource, String groupName) {
	// Item serverItem = datasource.addItem(groupName);
	// Property<String> groupItemProperty = serverItem.getItemProperty("name");
	// groupItemProperty.setValue(groupName);
	// datasource.setChildrenAllowed(serverItem, true);
	// }

}
