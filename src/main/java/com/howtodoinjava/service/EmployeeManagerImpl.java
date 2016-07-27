package com.howtodoinjava.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.howtodoinjava.dao.EmployeeDAO;
import com.howtodoinjava.entity.EmployeeEntity;

public class EmployeeManagerImpl implements EmployeeManager {
	// Employee dao injected by Spring context
	private EmployeeDAO employeeDAO;

	// This method will be called when a employee object is added
	@Override
	@Transactional
	public void addEmployee(EmployeeEntity employee) throws HibernateException {
		employeeDAO.addEmployee(employee);
	}

	// This method return list of employees in database
	@Override
	@Transactional
	public List<EmployeeEntity> getAllEmployees() {
		return employeeDAO.getAllEmployees();
	}

	@Override
	public String toString() {
		return "EmployeeManagerImpl [employeeDAO=" + employeeDAO + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	// Deletes a employee by it's id
	@Override
	@Transactional
	public void deleteEmployee(Integer employeeId) throws ObjectNotFoundException, HibernateException {
		employeeDAO.deleteEmployee(employeeId);
	}

	// This setter will be used by Spring context to inject the dao's instance
	public void setEmployeeDAO(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

	@Override
	@Transactional
	public List<EmployeeEntity> getAllEmployees(int page, int row) {
		return employeeDAO.getAllEmployees(page, row);
	}

	@Override
	@Transactional
	public int getTotalEmployees() {
		return employeeDAO.getTotalEmployees();
	}

	@Override
	@Transactional
	public int updateEmployee(EmployeeEntity employee) {
		return employeeDAO.updateEmployee(employee);
	}

	@Override
	@Transactional
	public void removeEmpl(int idEmp) throws ObjectNotFoundException, HibernateException {
		employeeDAO.removeEmpl(idEmp);
	}

	@Override
	@Transactional
	public List<EmployeeEntity> getAllEmployeesByNativeCode(int page, int row) {
		return employeeDAO.getAllEmployeesByNativeCode(page, row);
	}

	@Override
	@Transactional
	public Long getTotalEmployeesByNativeCode() {
		return employeeDAO.getTotalEmployeesByNativeCode();
	}

	@Override
	@Transactional
	public List<EmployeeEntity> getAllEmployeesByNativeCodeArray(int page, int row) {
		return employeeDAO.getAllEmployeesByNativeCodeArray(page, row);
	}

	@Override
	@Transactional
	public List<EmployeeEntity> getAllEmployeesByNativeCodeArrayUsingProcedure(int page, int row) {
		List<EmployeeEntity> lstEmp = new ArrayList<EmployeeEntity>();
		List<Object[]> lstRs= employeeDAO.getAllEmployeesByNativeCodeArrayUsingProcedure(page,row);
		for (int i = 0; i < lstRs.size(); i++) {
			Object[] objects = lstRs.get(i);
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
			lstEmp.add(emp);
		}
		return lstEmp;
	}

	@Override
	@Transactional
	public int getTotalEmployeesByNativeAndCallFunction() {
		return  employeeDAO.getTotalEmployeesByNativeAndCallFunction();
	}

	@Override
	@Transactional
	public List<EmployeeEntity> getAllEmployees(int page, int row, String firstName, String lastName) {
		return employeeDAO.getAllEmployees(page, row,firstName,lastName);
	}

	@Override
	@Transactional
	public int getTotalEmployees(String firstName, String lastName) {
		return employeeDAO.getTotalEmployees(firstName,lastName);
	}
}
