package com.googlecode.tomcatlsd.lsdagent.service;

import java.beans.XMLEncoder;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.management.MalformedObjectNameException;

import com.googlecode.tomcatlsd.common.model.HeartBeat;
import com.googlecode.tomcatlsd.lsdagent.util.injection.ManiocFramework.Inject;
import com.googlecode.tomcatlsd.lsdagent.util.injection.ManiocFramework.ManagedBean;

@ManagedBean
public class HeartBeatService extends TimerTask
{
    private static final int PING_TIMEOUT = 5000;

    private ExecutorService executor = Executors.newFixedThreadPool(1);

    private String collecServerURL = System.getProperty("tomcatlsd.manager.pingurl", "http://127.0.0.1:8080/lsdmanager/ping");

    @Inject
    private ApplicationService appplicationService;

    @Inject
    private MemoryUsageService memoryUsageService;
    
    @Inject
    private ApplicationServerService appServerInstanceService;

    @Override
    public void run()
    {
        doPingWithTimeout(PING_TIMEOUT);
    }

    public void doPing() throws Exception
    {
        HeartBeat heartBeatContentBean = buildHeartBeatContent();
        sendData(heartBeatContentBean);
    }

	public HeartBeat buildHeartBeatContent() throws MalformedObjectNameException {
        HeartBeat heartBeatContentBean = new HeartBeat();
		heartBeatContentBean.setApplicationServer(this.appServerInstanceService.getDescription());
        heartBeatContentBean.setMemoryUsageSnapshot(this.memoryUsageService.getCurrentMemoryStatus());
        heartBeatContentBean.setApplicationList(this.appplicationService.getDeployedApps());
		return heartBeatContentBean;
	}

    public void doPingWithTimeout(int timeoutSecs)
    {

        // set the executor thread working
        final Future future = executor.submit(new Runnable()
        {
            public void run()
            {
                try
                {
                    doPing();
                }
                catch (Exception e)
                {
                    throw new RuntimeException(e);
                }
            }
        });

        // check the outcome of the executor thread and limit the time allowed for it to complete
        try
        {
            future.get(timeoutSecs, TimeUnit.MILLISECONDS);
        }
        catch (Exception e)
        {
            // ExecutionException: deliverer threw exception
            // TimeoutException: didn't complete within downloadTimeoutSecs
            // InterruptedException: the executor thread was interrupted

            // interrupts the worker thread if necessary
            future.cancel(true);
        }
    }

    private boolean sendData(HeartBeat contentBean)
    {
        try
        {
            // Send data
            URL url = new URL(this.collecServerURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            XMLEncoder encoder = new XMLEncoder(conn.getOutputStream());
            encoder.writeObject(contentBean);
            encoder.flush();
            encoder.close();

            int responseCode = conn.getResponseCode();
            if (HttpURLConnection.HTTP_OK == responseCode)
            {
                return true;
            }

            return false;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

}
