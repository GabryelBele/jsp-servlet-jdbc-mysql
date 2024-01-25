package com.makiesse.registration.dao;

import com.makiesse.registration.model.User;

public interface UserDao {

	boolean registerUser(User user);

	User authenticateUser(String email, String password);

}
