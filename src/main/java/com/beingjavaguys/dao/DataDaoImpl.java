package com.beingjavaguys.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import com.beingjavaguys.model.Table1;

public class DataDaoImpl implements DataDao {

	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction tx = null;

	@Override
	public boolean addEntity(Table1 ms) throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		session.save(ms);
		tx.commit();
		session.close();
		return false;
	}

	@Override
	public Table1 getEntityById(long id) throws Exception {
		session = sessionFactory.openSession();
		Table1 employee = (Table1) session.load(Table1.class, new Long(id));
		tx = session.getTransaction();
		session.beginTransaction();
		tx.commit();
		return employee;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Table1> getEntityList() throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<Table1> employeeList = session.createCriteria(Table1.class).list();
		tx.commit();
		session.close();
		return employeeList;
	}
	
	@Override
	public boolean deleteEntity(long id) throws Exception {
		session = sessionFactory.openSession();
		Object o = session.load(Table1.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(o);
		tx.commit();
		return false;
	}

}
