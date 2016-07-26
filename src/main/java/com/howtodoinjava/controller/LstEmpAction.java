package com.howtodoinjava.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
		//rows = new ArrayList<EmployeeEntity>();
		rows=employeeManager.getAllEmployees(page,row);
		total = employeeManager.getTotalEmployees();
		rows=employeeManager.getAllEmployeesByNativeCode(page,row);
		rows=employeeManager.getAllEmployeesByNativeCodeArray(page,row);
		total = employeeManager.getTotalEmployeesByNativeCode().intValue();
		rows=employeeManager.getAllEmployeesByNativeCodeArrayUsingProcedure(page, row);
		return "success";
	}

	@Override
	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest=servletRequest;
	}
}
