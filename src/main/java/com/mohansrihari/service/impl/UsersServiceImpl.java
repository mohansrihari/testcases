package com.mohansrihari.service.impl;

import static org.springframework.util.StringUtils.hasText;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mohansrihari.constant.ConfigurationIDS;
import com.mohansrihari.dao.UsersDao;
import com.mohansrihari.dto.UserDTO;
import com.mohansrihari.entity.User;
import com.mohansrihari.exception.DAOException;
import com.mohansrihari.exception.ServiceException;
import com.mohansrihari.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private transient UsersDao usersDao;
	
	@Override
	public List<UserDTO> getUserProfiles() {
		List<User> users = usersDao.getUserProfiles();
		return this.getUserDTOList(users);
	}

	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	public void saveUsersDetails(final UserDTO userDTO) {
		try {
			
			userDTO.setPassword("Admin@123");
			
			User user = getUser(userDTO);
			if(userDTO.isSuperUserLogged()){
				user.setCreatedBy("SUPER ADMIN");
			}else{
				user.setCreatedBy("ADMIN");
			}
			Timestamp timestamp=new Timestamp(new Date().getTime());
			user.setCreatedDate(timestamp);
			user.setModifiedDate(timestamp);
			user.setStatus(ConfigurationIDS.ACTIVE_STATUS);
			usersDao.saveUsersDetails(user);
			
						
		} catch (DAOException exception) {
			throw new ServiceException(exception.toString());
		} catch (Exception exception) {
			throw new ServiceException(exception.toString());
		}
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	public void updateUserDetails(final UserDTO userDTO) {
		try {
			User user = getUser(userDTO);
			if(userDTO.isSuperUserLogged()){
				user.setModifiedBy("SUPER ADMIN");
			}else{
				user.setModifiedBy("ADMIN");
			}
			user.setModifiedDate(new Timestamp(new Date().getTime()));
			usersDao.updateUserProfile(user);
		} catch (DAOException exception) {
			throw new ServiceException(exception.toString());
		} catch (Exception exception) {
			throw new ServiceException(exception.toString());
		}
	}

	
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	public void deleteUser(UserDTO userDTO){
		try {
			User user = usersDao.findUserByUserId(userDTO.getUserId());
			if(userDTO.isSuperUserLogged()){
				user.setModifiedBy("SUPER ADMIN");
			}else{
				user.setModifiedBy("ADMIN");
			}
			user.setModifiedDate(new Timestamp(new Date().getTime()));
			user.setStatus(ConfigurationIDS.IN_ACTIVE_STATUS);
			usersDao.inActiveUser(user);
		} catch (DAOException exception) {
			throw new ServiceException(exception.toString());
		} catch (Exception exception) {
			throw new ServiceException(exception.toString());
		}
	}

	public UserDTO findUserByUserId(final String userId) {
		UserDTO userDTO = null;
		try {
			User users = usersDao.findUserByUserId(userId);
			userDTO = getUserDTO(users);
		} catch (DAOException exception) {
			throw new ServiceException(exception.toString());
		} catch (Exception exception) {
			throw new ServiceException(exception.toString());
		}
		return userDTO;
	}

	public UserDTO findUserByEmail(String emailAddress) {
		UserDTO userDTO=null;
		try {
			User users = usersDao.findUserByEmail(emailAddress);
			if(users!=null)
			userDTO = getUserDTO(users);
		} catch (DAOException exception) {
			throw new ServiceException(exception.toString());
		} catch (Exception exception) {
			throw new ServiceException(exception.toString());
		}
		return userDTO;
	}
	
	@Override
	public UserDTO findUserByUserNameAndPassword(String userName,String password) {
		try {
			UserDTO userDTO=null;
			User user = usersDao.findUserByUserNameAndPassword(userName,password);
			if(user!=null){
			userDTO = getUserDTO(user);
			}
			return userDTO;
		} catch (Exception exception) {
			throw new ServiceException(exception.toString());
		}
	}


	/* Following are the helper methods only */

	private User getUser(UserDTO userDTO) {
		User user = new User();
		if (hasText(userDTO.getUserId())) {
			user.setUserId(userDTO.getUserId());
			User existingUser=usersDao.findUserByUserId(userDTO.getUserId());
				if(existingUser!=null){
				user.setPassword(existingUser.getPassword());
				user.setCreatedBy(existingUser.getCreatedBy());
				user.setModifiedBy(existingUser.getModifiedBy());
				user.setCreatedDate(existingUser.getCreatedDate());
				user.setModifiedDate(existingUser.getModifiedDate());
				user.setStatus(existingUser.getStatus());
				}
		}
		if(hasText(userDTO.getPassword())){
		user.setPassword(userDTO.getPassword());
		}
		user.setFirstName(userDTO.getFirstName());
		user.setLocation(userDTO.getLocation());
		user.setLastName(userDTO.getLastName());
		user.setUserName(userDTO.getUserName());
		user.setEmailAddress(userDTO.getEmailAddress());
			return user;
	}

	private UserDTO getUserDTO(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(user.getUserId());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setLastName(user.getLastName());
		userDTO.setUserName(user.getUserName());
		userDTO.setLocation(user.getLocation());
		userDTO.setEmailAddress(user.getEmailAddress());
		userDTO.setPassword(user.getPassword());
		userDTO.setStatus(user.getStatus());
	
		return userDTO;
	}

	private List<UserDTO> getUserDTOList(List<User> users) {
		List<UserDTO> userDTOList = new ArrayList<UserDTO>();
		for (Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
			User user = (User) iterator.next();
			UserDTO userDTO = getUserDTO(user);
			userDTOList.add(userDTO);
		}
		return userDTOList;
	}

	@Override
	public UserDTO getUserByUserName(String userName) {
		try {
			UserDTO userDTO=null;
			User user = usersDao.getUserByUserName(userName);
			if( user!=null)
			userDTO=getUserDTO(user);
			return userDTO;
		} catch (Exception exception) {
			throw new ServiceException(exception.toString());
		}
	}
	
	
}