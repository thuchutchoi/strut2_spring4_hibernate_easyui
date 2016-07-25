package com.howtodoinjava.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.hibernate.validator.ClassValidator;
import org.hibernate.validator.InvalidValue;
import org.hibernate.validator.Valid;

import com.howtodoinjava.dao.UserDAO;
import com.howtodoinjava.entity.User;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {

	private static final long serialVersionUID = -6659925652584240539L;

	@Valid
	private User user;
	private List<User> userList = new ArrayList<User>();
	private UserDAO userDAO;
	private static ClassValidator<User> userValidator = new ClassValidator<User>(User.class);
	private Boolean error;
	private Map<String, String> mapErr = new HashedMap()<String, String>;

	public String add() {
		try {
			InvalidValue[] invalidValues = userValidator.getInvalidValues(user);
			for (InvalidValue value : invalidValues) {
				System.out.println("========");
				System.out.println(value);
				System.out.println("message=" + value.getMessage());
				System.out.println("propertyName=" + value.getPropertyName());
				System.out.println("propertyPath=" + value.getPropertyPath());
				System.out.println("value=" + value.getValue());
			}
			if(invalidValues.length>0){
				error=Boolean.TRUE;
			}
			else if(invalidValues.length==0){
				userDAO.saveUser(user);
			}
		} catch (Exception e) {
			System.out.println("Err here................");
		}

		return SUCCESS;
	}

	public String list() {
		userList = userDAO.listUser();
		return SUCCESS;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

}
