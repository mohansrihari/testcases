package com.mohansrihari.unit.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.MessageSource;

import com.mohansrihari.constant.ConfigurationIDS;
import com.mohansrihari.dao.UsersDao;
import com.mohansrihari.dto.UserDTO;
import com.mohansrihari.entity.User;
import com.mohansrihari.service.impl.UsersServiceImpl;

public class UsersServiceImplTest {

	@InjectMocks
	protected UsersServiceImpl usersService;

	@Mock
	protected UsersDao usersDao;
	@Mock
	protected MessageSource messageSource;

	private UserDTO userDTO;

	private List<User> userList = new ArrayList<User>();
	private User user;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		user = new User();
		userDTO = new UserDTO();
		setUserDTO();
		setUserList();
	}

	private void setUserDTO() {
		userDTO.setUserName("tprasad");
		userDTO.setRoleId("2");
		userDTO.setRoleName("manager");
		userDTO.setFirstName("Prasad");
		userDTO.setLastName("T");
		userDTO.setLastName("HYD");
		userDTO.setPassword("Tesd@123");
		userDTO.setEmailAddress("tprasad@gmail.com");
		userDTO.setStatus(ConfigurationIDS.ACTIVE_STATUS);
	}

	private void setUserList() {
		user.setUserName("kmohan");
		user.setFirstName("mohan");
		user.setLastName("srihari");
		user.setLastName("HYD");
		user.setPassword("Admin@123");
		user.setEmailAddress("kmohan@prokarmasoftech.com");
		user.setStatus(ConfigurationIDS.ACTIVE_STATUS);
		userList.add(user);
	}

	@Test
	public void testGetUserProfiles() {
		when(usersDao.getUserProfiles()).thenReturn(userList);
		List<UserDTO> userDTOsList = usersService.getUserProfiles();
		Assert.assertNotNull(userDTOsList);
	}

	@Test
	public void testSaveUsersDetails() {
		ArgumentCaptor<User> captor = getArgumentCaptor();
		usersService.saveUsersDetails(userDTO);
		verify(usersDao).saveUsersDetails(captor.capture());
		List<User> users = captor.getAllValues();
		assertEquals(users.get(0).getFirstName(), "Prasad");
	}

	@Test
	public void testUpdateUserProfile() {
		userDTO.setLastName("Test");
		ArgumentCaptor<User> captorObject = getArgumentCaptor();
		when(usersDao.findUserByUserId("1")).thenReturn(user);
		usersService.updateUserDetails(userDTO);
		verify(usersDao).updateUserProfile(captorObject.capture());
		List<User> users = captorObject.getAllValues();
		assertEquals(users.get(0).getLastName(), "Test");
	}

	@Test
	public void testDeleteUserByUserId() {
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId("1");
		ArgumentCaptor<User> captorObject = getArgumentCaptor();
		when(usersDao.findUserByUserId("1")).thenReturn(user);
		usersService.deleteUser(userDTO);
		verify(usersDao).inActiveUser(captorObject.capture());
		User user = captorObject.getValue();
		assertEquals(user.getStatus(), ConfigurationIDS.IN_ACTIVE_STATUS);
	}

	private ArgumentCaptor<User> getArgumentCaptor() {
		ArgumentCaptor<User> captorObject = ArgumentCaptor.forClass(User.class);
		return captorObject;
	}

	@Test
	public void testFindUserByUserId() {
		when(usersDao.findUserByUserId("1")).thenReturn(user);
		UserDTO userDTO = usersService.findUserByUserId("1");
		Assert.assertNotNull(userDTO);
	}

	@Test
	public void testFindUserByEmail() {
		when(usersDao.findUserByEmail("kmohan@prokarmasoftech.com")).thenReturn(user);
		UserDTO userDTO = usersService.findUserByEmail("kmohan@prokarmasoftech.com");
		Assert.assertNotNull(userDTO);
	}

	@Test
	public void testGetUserByUserName() {
		when(usersDao.getUserByUserName("kmohan")).thenReturn(user);
		UserDTO userDTO = usersService.getUserByUserName("kmohan");
		Assert.assertNotNull(userDTO);
	}

	@Test
	public void testfindUserByUserNameAndPassword() {
		when(usersDao.findUserByUserNameAndPassword("admin@up.com","Admin@123")).thenReturn(user);
		UserDTO userDTO = usersService.findUserByUserNameAndPassword("admin@up.com", "Admin@123");
		Assert.assertNotNull(userDTO);
	}

}
