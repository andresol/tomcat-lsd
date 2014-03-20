package com.googlecode.tomcatlsd.lsdmanager.dao;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.googlecode.tomcatlsd.lsdmanager.model.ServerBrowserFolder;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class ServerGroupDaoTest extends TestCase {

	@Autowired
	private ServerBrowserFolderDao dao;

	@Test
	public void testSave() {
		boolean isOK = false;
		try {
			ServerBrowserFolder serverGroup = new ServerBrowserFolder();
			serverGroup.setName("group1");
			dao.save(serverGroup);
			isOK = true;
		} catch (Throwable t) {
			assertTrue(isOK);
		}
	}

}
