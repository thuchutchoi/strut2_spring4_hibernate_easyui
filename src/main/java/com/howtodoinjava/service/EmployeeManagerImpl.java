package com.howtodoinjava.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.howtodoinjava.dao.EmployeeDAO;
import com.howtodoinjava.entity.EmployeeEntity;

public class EmployeeManagerImpl implements EmployeeManager {
	//Employee dao injected by Spring context
    private EmployeeDAO employeeDAO;
	
	//This method will be called when a employee object is added
	@Override
	@Transactional
	public void addEmployee(EmployeeEntity employee) {
		employeeDAO.addEmployee(employee);
	}
	
	//This method return list of employees in database
	@Override
	@Transactional
	public List<EmployeeEntity> getAllEmployees() {
		return employeeDAO.getAllEmployees();
	}
	//Deletes a employee by it's id
	@Override
	@Transactional
	public void deleteEmployee(Integer employeeId) {
		employeeDAO.deleteEmployee(employeeId);
	}
	
	//This setter will be used by Spring context to inject the dao's instance
	public void setEmployeeDAO(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

	@Override
	@Transactional
	public List<EmployeeEntity> getAllEmployees(int page, int row) {
		return employeeDAO.getAllEmployees(page,row);
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
	public void removeEmpl(int idEmp) {
	    employeeDAO.removeEmpl(idEmp);
	}

	@Override
	@Transactional
	public List<EmployeeEntity> getAllEmployeesByNativeCode(int page, int row) {
		return employeeDAO.getAllEmployeesByNativeCode(page,row);
	}

	@Override
	@Transactional
	public Long getTotalEmployeesByNativeCode() {
		return employeeDAO.getTotalEmployeesByNativeCode();
	}

	@Override
	@Transactional
	public List<EmployeeEntity> getAllEmployeesByNativeCodeArray(int page, int row) {
		return employeeDAO.getAllEmployeesByNativeCodeArray(page,row);
	}
}
