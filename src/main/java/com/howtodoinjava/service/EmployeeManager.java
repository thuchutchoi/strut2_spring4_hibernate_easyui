package com.howtodoinjava.service;

import java.util.List;

import com.howtodoinjava.entity.EmployeeEntity;

public interface EmployeeManager {
	//This method will be called when a employee object is added
    public void addEmployee(EmployeeEntity employee);
    //This method return list of employees in database
    public List<EmployeeEntity> getAllEmployees();
    //Deletes a employee by it's id
    public void deleteEmployee(Integer employeeId);
	public List<EmployeeEntity> getAllEmployees(int page, int row);
	public int getTotalEmployees();
	public int updateEmployee(EmployeeEntity employee);
	public void removeEmpl(int i);
	public List<EmployeeEntity> getAllEmployeesByNativeCode(int page, int row);
	public Long getTotalEmployeesByNativeCode();
	public List<EmployeeEntity> getAllEmployeesByNativeCodeArray(int page, int row);
}
