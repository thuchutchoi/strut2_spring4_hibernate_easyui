package com.howtodoinjava.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
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

	@Override
	public List<Object[]> getAllEmployeesByNativeCodeArrayUsingProcedure(int page, int row) {
		List<EmployeeEntity> lstRs = new ArrayList<EmployeeEntity>();
		page=page-1;
		StringBuilder sql = new StringBuilder("CALL GetAllEmployee("+row+","+page*row+")");
		SQLQuery q = this.sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
//		q.setParameter("limit", row);
//		q.setParameter("offset", (page-1)*row);
		List<Object[]> lst= q.list();
		return lst;
	}

	@Override
	public int getTotalEmployeesByNativeAndCallFunction() {
		SQLQuery q = this.sessionFactory.getCurrentSession().createSQLQuery(" SELECT getTotalEmployees()");
		q.uniqueResult();
		return (Integer) q.uniqueResult();
	}

	@Override
	public List<EmployeeEntity> getAllEmployees(int page, int row, String firstName, String lastName) {
		StringBuilder sql = new StringBuilder("from EmployeeEntity where 1 = 1");
		if(!StringUtils.isBlank(firstName)){
			sql.append(" and firstname = "+"'"+firstName+"'");
		}
		if(!StringUtils.isBlank(lastName)){
			sql.append("and lastName = "+"'"+lastName+"'");
		}
		Query q = this.sessionFactory.getCurrentSession().createQuery(sql.toString());
		q.setFirstResult((page - 1) * row);
		q.setMaxResults(row);
		return q.list();
	}

	@Override
	public int getTotalEmployees(String firstName, String lastName) {
		StringBuilder sql = new StringBuilder("SELECT COUNT(e.id) FROM EmployeeEntity e where 1 = 1 ");
		if(!StringUtils.isBlank(firstName)){
			sql.append("and firstname = "+"'"+firstName+"'");
		}
		if(!StringUtils.isBlank(lastName)){
			sql.append("and lastName = "+"'"+lastName+"'");
		}
		Query q = this.sessionFactory.getCurrentSession().createQuery(sql.toString());
		Long count = (Long) q.uniqueResult();
		return count.intValue();
	}
	public static void main(String[] args) {
//		String a="'"+"xxx"+"'";
//		System.out.println(a);
//		org.apache.commons.lang.ArrayUtils aa;
//		org.apache.commons.lang.BitField bb;
//		org.apache.commons.lang.BooleanUtils bl;
//		org.apache.commons.lang.CharEncoding ce;
//		org.apache.commons.lang.CharRange cr;
//		org.apache.commons.lang.CharSet cs;
//		org.apache.commons.lang.CharSetUtils csu;
//		org.apache.commons.lang.StringUtils un;
		String a="from EmployeeEntity where 1 = 1 ORDER BY firstname asc,";
		int abc=a.lastIndexOf(",");
		System.out.println(a.substring(0,abc));
		
	}

	@Override
	public List<EmployeeEntity> getAllEmployees(int page, int row, String firstName, String lastName, String sortField, String typeSort) {
		StringBuilder sql = new StringBuilder("from EmployeeEntity where 1 = 1");
		if(!StringUtils.isBlank(firstName)){
			sql.append(" and firstname = "+"'"+firstName+"'");
		}
		if(!StringUtils.isBlank(lastName)){
			sql.append(" and lastName = "+"'"+lastName+"'");
		}
		if(!StringUtils.isBlank(sortField)){
			sql.append(" ORDER BY "+sortField);
			if(!StringUtils.isBlank(typeSort)){
				sql.append(" "+typeSort);
			}
			else{
				sql.append(" ASC");
			}
		}
		Query q = this.sessionFactory.getCurrentSession().createQuery(sql.toString());
		q.setFirstResult((page - 1) * row);
		q.setMaxResults(row);
		return q.list();
	}

	@Override
	public List<EmployeeEntity> getAllEmployees(int page, int row, String firstName, String lastName, Map<String, String> map) {
		StringBuilder sql = new StringBuilder("from EmployeeEntity where 1 = 1");
		if(!StringUtils.isBlank(firstName)){
			sql.append(" and firstname = "+"'"+firstName+"'");
		}
		if(!StringUtils.isBlank(lastName)){
			sql.append(" and lastName = "+"'"+lastName+"'");
		}
		Set<Entry<String, String>> entrySet = map.entrySet();
		if(entrySet.size()>0){
			 sql.append(" ORDER BY ");
			 for ( Map.Entry<String, String> entry : entrySet) {
				    String sortField = entry.getKey();
				    String typeSort = entry.getValue();
				    sql.append(sortField);
				    sql.append(" "+typeSort);
				    sql.append(",");
				}
		}
		
//		if(!StringUtils.isBlank(sortField)){
//			sql.append(" ORDER BY "+sortField);
//			if(!StringUtils.isBlank(typeSort)){
//				sql.append(" "+typeSort);
//			}
//			else{
//				sql.append(" ASC");
//			}
//		}
		String sqlQuery = sql.toString();
		int lastIndexOf = sqlQuery.lastIndexOf(",");
		if(lastIndexOf>0){
			sqlQuery=sqlQuery.substring(0, lastIndexOf);
		}
		Query q = this.sessionFactory.getCurrentSession().createQuery(sqlQuery);
		q.setFirstResult((page - 1) * row);
		q.setMaxResults(row);
		return q.list();
	}
}
