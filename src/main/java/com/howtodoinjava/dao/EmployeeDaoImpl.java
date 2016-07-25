package com.howtodoinjava.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.howtodoinjava.entity.EmployeeEntity;

@Repository
public class EmployeeDaoImpl implements EmployeeDAO {
	// Session factory injected by spring context
	private SessionFactory sessionFactory;

	// This method will be called when a employee object is added
	@Override
	public void addEmployee(EmployeeEntity employee) throws HibernateException{
		this.sessionFactory.getCurrentSession().save(employee);
	}

	// This method return list of employees in database
	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeEntity> getAllEmployees() {
		return this.sessionFactory.getCurrentSession().createQuery("from EmployeeEntity").list();
	}

	// Deletes a employee by it's id
	@Override
	public void deleteEmployee(Integer employeeId) throws ObjectNotFoundException,HibernateException {
		EmployeeEntity employee = (EmployeeEntity) sessionFactory.getCurrentSession().load(EmployeeEntity.class,
				employeeId);
		if (null != employee) {
			this.sessionFactory.getCurrentSession().delete(employee);
		}
	}

	// This setter will be used by Spring context to inject the sessionFactory
	// instance
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<EmployeeEntity> getAllEmployees(int page, int row) {
		Query q = this.sessionFactory.getCurrentSession().createQuery("from EmployeeEntity");
		q.setFirstResult((page - 1) * row);
		q.setMaxResults(row);
		return q.list();
	}

	@Override
	public int getTotalEmployees() {
		Query q = this.sessionFactory.getCurrentSession().createQuery("SELECT COUNT(e.id) FROM EmployeeEntity e");
		Long count = (Long) q.uniqueResult();
		return count.intValue();
	}

	@Override
	public int updateEmployee(EmployeeEntity employee) {
		StringBuilder builder = new StringBuilder();
		builder.append("update EmployeeEntity set firstname = :fn,");
		builder.append(" lastname = :ln,");
		builder.append(" email = :em,");
		builder.append(" telephone = :tlp");
		builder.append(" where id = :id");
		Query query = this.sessionFactory.getCurrentSession().createQuery(builder.toString());
		query.setParameter("fn", employee.getFirstname());
		query.setParameter("ln", employee.getLastname());
		query.setParameter("em", employee.getEmail());
		query.setParameter("tlp",employee.getTelephone());
		query.setParameter("id", employee.getId());
		int result = query.executeUpdate();
		return result;
	}

	@Override
	public void removeEmpl(int idEmp)throws ObjectNotFoundException,HibernateException  {
		deleteEmployee(idEmp);
	}

	@Override
	public List<EmployeeEntity> getAllEmployeesByNativeCode(int page, int row) {
		StringBuilder sql = new StringBuilder("SELECT * FROM employee");
		sql.append(" LIMIT :limit");
		sql.append(" OFFSET :offset");
		SQLQuery q = this.sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		q.setParameter("limit", row);
		q.setParameter("offset", (page-1)*row);
		q.addEntity(EmployeeEntity.class);
		return q.list();
	}

	@Override
	public Long getTotalEmployeesByNativeCode() {
		SQLQuery q = this.sessionFactory.getCurrentSession().createSQLQuery("SELECT COUNT(id) FROM employee");
		BigInteger result =(BigInteger) q.uniqueResult();
		Long count= result.longValue();
		return count;
	}

	@Override
	public List<EmployeeEntity> getAllEmployeesByNativeCodeArray(int page, int row) {
		List<EmployeeEntity> lstRs = new ArrayList<EmployeeEntity>();
		StringBuilder sql = new StringBuilder("SELECT * FROM employee");
		sql.append(" LIMIT :limit");
		sql.append(" OFFSET :offset");
		SQLQuery q = this.sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		q.setParameter("limit", row);
		q.setParameter("offset", (page-1)*row);
		List<Object[]> lst= q.list();
		for (int i = 0; i < lst.size(); i++) {
			Object[] objects = lst.get(i);
			Integer id=(Integer) objects[0];
			String firstname=(String) objects[1];
			String lastname=(String) objects[2];
			String telephone=(String) objects[3];
			String email=(String) objects[4];
			EmployeeEntity emp = new EmployeeEntity.Builder()
			                     .id(id)
			                     .firstname(firstname)
			                     .lastname(lastname)
			                     .telephone(telephone)
			                     .email(email).build();
			lstRs.add(emp);
		}
		return lstRs;
	}
}
