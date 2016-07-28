package com.howtodoinjava.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.howtodoinjava.entity.EmployeeEntity;
import com.howtodoinjava.service.EmployeeManager;
import com.opensymphony.xwork2.ActionSupport;

public class LstEmpAction extends ActionSupport implements ServletRequestAware{
	private static final long serialVersionUID = 1L;
	private int total;
	// List of employees; Setter and Getter are below
	private List<EmployeeEntity> rows = new ArrayList<EmployeeEntity>();
	// Logger configured using log4j
	private static final Logger logger = Logger.getLogger(EditEmployeeAction.class);
	// Employee manager injected by spring context; This is cool !!
	private EmployeeManager employeeManager;
	private HttpServletRequest servletRequest;

	public LstEmpAction() {
		System.out.println("Action Lst");
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<EmployeeEntity> getRows() {
		return rows;
	}

	public void setEmployeeManager(EmployeeManager employeeManager) {
		this.employeeManager = employeeManager;
	}

	public String execute() {
		logger.info("listEmployees method called");
		int page =Integer.parseInt(servletRequest.getParameter("page"));
		int row = Integer.parseInt(servletRequest.getParameter("rows"));
		String firstName=servletRequest.getParameter("firstname");
		String lastName=servletRequest.getParameter("lastName");
		String sortField=servletRequest.getParameter("sort");
		String typeSort = servletRequest.getParameter("order");
		Map<String, String> map = new HashedMap();
		if(sortField!=null && typeSort!=null){
			String [] arraySortField=sortField.split(",");
			String [] arrayTypeSort=typeSort.split(",");
			for (int i = 0; i < arraySortField.length; i++) {
				map.put(arraySortField[i], arrayTypeSort[i]);
			}
		}
		//rows = new ArrayList<EmployeeEntity>();
//		rows=employeeManager.getAllEmployees(page,row,firstName,lastName);
//		total = employeeManager.getTotalEmployees();
//		rows=employeeManager.getAllEmployeesByNativeCode(page,row);
//		rows=employeeManager.getAllEmployeesByNativeCodeArray(page,row);
//		total = employeeManager.getTotalEmployeesByNativeCode().intValue();
//		rows=employeeManager.getAllEmployeesByNativeCodeArrayUsingProcedure(page, row);
//		total = employeeManager.getTotalEmployeesByNativeAndCallFunction();
		rows=employeeManager.getAllEmployees(page,row,firstName,lastName);
		rows=employeeManager.getAllEmployees(page,row,firstName,lastName,map);
		total = employeeManager.getTotalEmployees(firstName,lastName);
		return "success";
	}

	@Override
	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest=servletRequest;
	}
	public static void main(String[] args) {
		String a=" ";
		String[] split = a.split(",");
		for (int i = 0; i < split.length; i++) {
			System.out.println(split.length);
			System.out.println(split[i]);
		}
		
	}
}
