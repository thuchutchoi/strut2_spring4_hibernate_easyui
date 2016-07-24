package com.howtodoinjava.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.howtodoinjava.dao.StudentDao;
import com.howtodoinjava.dao.StudentDaoBean;
import com.opensymphony.xwork2.ActionSupport;

public class StudentAction extends ActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private StudentDao studentDao;
	private HttpServletRequest request;
	private int total;
	private List<Object> rows;

	public StudentAction() {
		System.out.println("Action初始化了");
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<Object> getRows() {
		return rows;
	}

	public void setRows(List<Object> rows) {
		this.rows = rows;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String test() {
		return SUCCESS;
	}
	public String login() {
		return SUCCESS;
	}

	public String execute() {
		int page =Integer.parseInt(request.getParameter("page"));
		int row = Integer.parseInt(request.getParameter("rows"));
		studentDao = new StudentDaoBean();
		this.rows = studentDao.getStudent(page, row);
		this.total = studentDao.getTotalPages(row);
		System.out.println("总页数：" + this.total);
		return "success";
	}
}
