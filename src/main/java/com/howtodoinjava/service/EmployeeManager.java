package com.howtodoinjava.service;

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;

import com.howtodoinjava.entity.EmployeeEntity;

public interface EmployeeManager {
	//This method will be called when a employee object is added
    public void addEmployee(EmployeeEntity employee)throws HibernateException;
    //This method return list of employees in database
    public List<EmployeeEntity> getAllEmployees();
    //Deletes a employee by it's id
    public void deleteEmployee(Integer employeeId)throws ObjectNotFoundException,HibernateException;
	public List<EmployeeEntity> getAllEmployees(int page, int row);
	public int getTotalEmployees();
	public int updateEmployee(EmployeeEntity employee);
	public void removeEmpl(int i)throws ObjectNotFoundException,HibernateException ;
	public List<EmployeeEntity> getAllEmployeesByNativeCode(int page, int row);
	public Long getTotalEmployeesByNativeCode();
	public List<EmployeeEntity> getAllEmployeesByNativeCodeArray(int page, int row);
	public List<EmployeeEntity> getAllEmployeesByNativeCodeArrayUsingProcedure(int page, int row);
	public int getTotalEmployeesByNativeAndCallFunction();
	public List<EmployeeEntity> getAllEmployees(int page, int row, String firstName, String lastName);
	public int getTotalEmployees(String firstName, String lastName);
	public List<EmployeeEntity> getAllEmployees(int page, int row, String firstName, String lastName, String sortField, String typeSort);
	public List<EmployeeEntity> getAllEmployees(int page, int row, String firstName, String lastName, Map<String, String> map);
}
