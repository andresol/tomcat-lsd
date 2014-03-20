package com.googlecode.tomcatlsd.lsdmanager.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.tomcatlsd.common.model.HeartBeat;

@Transactional
@Repository
public class HeartBeatDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void insert(HeartBeat bean) {
		sessionFactory.getCurrentSession().saveOrUpdate(bean);
	}
	
	public List<HeartBeat> findAll() {
		return sessionFactory.getCurrentSession().createCriteria(HeartBeat.class).list();
	}
	
}
