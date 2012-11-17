package com.mohansrihari.dao;

import java.util.List;

import com.mohansrihari.entity.User;

public interface UsersDao {

	void saveUsersDetails(User users);

	void updateUserProfile(User users);
   
	void inActiveUser(User user);
	
	User findUserByEmail(String emailAddress);

	User findUserByUserId(String userId);

	List<User> getUserProfiles();

	User findUserByUserNameAndPassword(String userName,String password);
	
	User getUserByUserName(String userName) ;
	
}