package com.googlecode.tomcatlsd.lsdmanager.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlecode.tomcatlsd.common.model.HeartBeat;
import com.googlecode.tomcatlsd.lsdmanager.dao.HeartBeatDao;

@Service
public class HeartBeatService {

	@Autowired
	private HeartBeatDao heartBeatDao;

	public void consume(HeartBeat heartBeat) {
		heartBeat.setReceptionDate(new Date());
		heartBeatDao.insert(heartBeat);
	}

}
