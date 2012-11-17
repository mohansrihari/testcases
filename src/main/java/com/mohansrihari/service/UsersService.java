package com.mohansrihari.service;

import java.util.List;

import com.mohansrihari.dto.UserDTO;

public interface UsersService {
	List<UserDTO> getUserProfiles();

	void saveUsersDetails(UserDTO usersDTO);

	void updateUserDetails(UserDTO userDTO);
	
	void deleteUser(UserDTO userDTO);

	UserDTO findUserByUserId(String userId);

	UserDTO findUserByEmail(String emailAddress);

	UserDTO findUserByUserNameAndPassword(String userName,String password);
	
	UserDTO getUserByUserName(String userName) ;
	
}