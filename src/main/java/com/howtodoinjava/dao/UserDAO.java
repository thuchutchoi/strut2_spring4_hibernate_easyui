package com.howtodoinjava.dao;

import java.util.List;

import com.howtodoinjava.entity.User;

public interface UserDAO {

	public void saveUser(User user);
	public List<User> listUser();
}
