package com.googlecode.tomcatlsd.lsdmanager.servlet;

import java.beans.XMLDecoder;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.googlecode.tomcatlsd.common.model.HeartBeat;
import com.googlecode.tomcatlsd.common.model.ApplicationServer;
import com.googlecode.tomcatlsd.lsdmanager.service.ApplicationServerService;
import com.googlecode.tomcatlsd.lsdmanager.service.HeartBeatService;
import com.googlecode.tomcatlsd.lsdmanager.service.ServerBrowserService;

@Component("AgentListenerServlet")
public class AgentListenerServlet extends HttpServlet {

	@Autowired
	private HeartBeatService service;
	
	@Autowired
	private ServerBrowserService serverGroupService;
	
	@Autowired
	private ApplicationServerService applicationServerService;
	
	/** Log instance. */
	private final Logger log = Logger.getLogger(this.getClass());

	@Override
	public void init() throws ServletException {
		WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();
		autowireCapableBeanFactory.configureBean(this, "AgentListenerServlet");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletInputStream is = req.getInputStream();
		XMLDecoder xmlDecoder = new XMLDecoder(is);
		try {
			HeartBeat heartBeat = (HeartBeat) xmlDecoder.readObject();
			this.service.consume(heartBeat);
			ApplicationServer appServer = heartBeat.getApplicationServer();
			log.debug("Ping received from : " + appServer.getHostname() + "/" + appServer.getAlias());
			if (!this.applicationServerService.isExistInDatabase(appServer)) {
				this.applicationServerService.save(appServer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			xmlDecoder.close();
		}
	}

}
