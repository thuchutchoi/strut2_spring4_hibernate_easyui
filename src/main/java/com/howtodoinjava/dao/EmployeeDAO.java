package com.howtodoinjava.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;

import com.howtodoinjava.entity.EmployeeEntity;

public interface EmployeeDAO 
{
	//This method will be called when a employee object is added
    public void addEmployee(EmployeeEntity employee)throws HibernateException;
    //This method return list of employees in database
    public List<EmployeeEntity> getAllEmployees();
    //Deletes a employee by it's id
    public void deleteEmployee(Integer employeeId)throws ObjectNotFoundException,HibernateException;
	public List<EmployeeEntity> getAllEmployees(int page, int row);
	public int getTotalEmployees();
	public int updateEmployee(EmployeeEntity employee);
	public void removeEmpl(int idEmp)throws ObjectNotFoundException,HibernateException ;
	public List<EmployeeEntity> getAllEmployeesByNativeCode(int page, int row);
	public Long getTotalEmployeesByNativeCode();
	public List<EmployeeEntity> getAllEmployeesByNativeCodeArray(int page, int row);
}