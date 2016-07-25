package com.howtodoinjava.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.howtodoinjava.entity.User;

public class UserDAOImpl implements UserDAO {
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<User> listUser() throws HibernateException {
		return this.sessionFactory.getCurrentSession().createQuery("from User").list();
	}

	@Override
	@Transactional
	public void saveUser(User user) throws HibernateException {
		this.sessionFactory.getCurrentSession().save(user);
	}

	// This setter will be used by Spring context to inject the sessionFactory
	// instance
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
