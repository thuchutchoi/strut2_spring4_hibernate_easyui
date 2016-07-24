package com.howtodoinjava.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.howtodoinjava.dto.Report;
import com.opensymphony.xwork2.ActionSupport;

public class ReadJSON extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = -6765991741441442190L;

	private List<Report> data;

	private HttpServletRequest servletRequest;

	public String readJSON() {
		System.out.println("getJSON Method Call Before");

		data = new ArrayList<Report>();
		Report obj = new Report.Builder()
				     .active(false)
				     .color("Green")
				     .date("05-Sep-2013")
				     .id(1).name("Rajesh")
				     .build();
		this.data.add(obj);
		System.out.println("getJSON Method Call");
		System.out.println("Length of Data is " + data.size());

		try {
			for (int i = 0; i < data.size(); i++) {
				System.out.println("Color is " + data.get(i).getColor());
				System.out.println("Date  is " + data.get(i).getDate());
				System.out.println("ID is " + data.get(i).getId());
				System.out.println("Names is " + data.get(i).getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String writeJSON() {
		try {
			System.out.println(data.size());

			for (int i = 0; i < data.size(); i++) {
				System.out.println("Data  " + data.get(i).getColor() + "-" + data.get(i).getDate() + "-" + data.get(i).getId() + "-" + data.get(i).getName());
			}

			System.out.println("Execute Method");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public List<Report> getData() {
		System.out.println("Getter Call");
		return data;
	}

	public void setData(List<Report> data) {
		System.out.println("Setter Call Flow");
		this.data = data;
	}

	@Override
	public void setServletRequest(HttpServletRequest servletRequest) {
		// TODO Auto-generated method stub
		this.servletRequest=servletRequest;
	}

}