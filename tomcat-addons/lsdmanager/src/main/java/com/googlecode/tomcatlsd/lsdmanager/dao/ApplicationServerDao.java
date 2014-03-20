package com.googlecode.tomcatlsd.lsdmanager.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.tomcatlsd.common.model.ApplicationServer;

@Transactional
@Repository
public class ApplicationServerDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void save(ApplicationServer bean) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(bean);
	}
	
	public List<ApplicationServer> findByAliasAndHostname(String alias, String hostname) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(ApplicationServer.class);
		criteria.add(Restrictions.like("alias", alias));
		criteria.add(Restrictions.like("hostname", hostname));
		List<ApplicationServer> result = criteria.list();
		return result;
	}
	
}
