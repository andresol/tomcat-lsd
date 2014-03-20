package com.googlecode.tomcatlsd.lsdagent.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Timer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.tomcatlsd.lsdagent.service.HeartBeatService;
import com.googlecode.tomcatlsd.lsdagent.util.injection.ManiocFramework.BeanInjector;
import com.googlecode.tomcatlsd.lsdagent.util.injection.ManiocFramework.Inject;

public class HeartBeatSchedulerServlet extends HttpServlet
{

    @Inject
    private HeartBeatService heartBeatService;

    private long timerRepeatInterval = 10000;
    
    private Timer timer = new Timer("TOMCAT LSD AGENT HEARTBEAT", true);

    @Override
    public void init(ServletConfig config) throws ServletException
    {
        super.init(config);
        String intervalString = config.getInitParameter("interval");
        int interval = Integer.parseInt(intervalString);
        this.timerRepeatInterval = interval * 1000;
        BeanInjector.getInjector().inject(this);
        this.timer.schedule(heartBeatService, 0, this.timerRepeatInterval);
    }

    
    @Override
    public void destroy() {
    	this.timer.cancel();
    	super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        super.doGet(req, resp);
        forceRefresh();
        PrintWriter writer = resp.getWriter();
        writer.write("OK - STATUS REFRESHED ON TOMCAT LSD MANAGER");
    }

    private void forceRefresh()
    {
        this.heartBeatService.run();
    }

}
