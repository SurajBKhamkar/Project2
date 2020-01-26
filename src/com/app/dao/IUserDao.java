package com.app.dao;

import java.util.List;

import com.app.pojos.Users;

public interface IUserDao {

	List<Users> getAllUsers();

	Users validateUser(Users user);

	Users registerUser(Users signUp);

	Users registerOwner(Users signUp);

}
