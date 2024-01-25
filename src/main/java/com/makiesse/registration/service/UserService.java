package com.makiesse.registration.service;

import com.makiesse.registration.dao.UserDao;
import com.makiesse.registration.model.User;

public class UserService {
	private final UserDao userDao;

	public UserService(UserDao userDao) {
		this.userDao = userDao;
	}

	public boolean registerUser(String name, String email, String pass, String contact) {
	    if (name == null || email == null || pass == null || contact == null) {
	        return false;
	    }

	    User user = new User(name, email, pass, contact);
	    return userDao.registerUser(user);
	}


	public User authenticateUser(String email, String password) {
		if (email == null || password == null) {
			return null;
		}
		return userDao.authenticateUser(email, password);
	}
}
