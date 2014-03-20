package com.googlecode.tomcatlsd.lsdmanager.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.tomcatlsd.lsdmanager.model.ServerBrowserFolder;

@Transactional
@Repository
public class ServerBrowserFolderDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void save(ServerBrowserFolder bean) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(bean);
	}
	
	public List<ServerBrowserFolder> findByName(String groupName) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(ServerBrowserFolder.class);
		criteria.add(Restrictions.like("name", groupName));
		List<ServerBrowserFolder> result = criteria.list();
		return result;
	}
	
//	public List<ServerBrowserFolder> findByServerName(String serverName) {
//		String hql = "from ServerBrowserFolder as a inner join a.serverList as b where b = :serverName";
//		Session session = sessionFactory.getCurrentSession();
//		Query query = session.createQuery(hql);
//		query.setString("serverName", serverName);
//		List result = query.list();
//		return result;
//	}
	
	public List<ServerBrowserFolder> findAll() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(ServerBrowserFolder.class).list();
	}
	
}
