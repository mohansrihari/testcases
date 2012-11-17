package com.mohansrihari.dto;

import java.io.Serializable;
import java.util.Arrays;

public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String userId;
	private String firstName;
	private String lastName;
	private String userName;
	private String emailAddress;
	private String roleName;
	private String roleId;
	private String roles[];
	private String userRoles;
	private String password;
	private String adminLevel;
	
	private String saveParam;
	private String location;
	
	private boolean status;
	
	private boolean isSuperUserLogged;
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}

	public String getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(String userRoles) {
		this.userRoles = userRoles;
	}

	public String getSaveParam() {
		return saveParam;
	}

	public void setSaveParam(String saveParam) {
		this.saveParam = saveParam;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAdminLevel() {
		return adminLevel;
	}

	public void setAdminLevel(String adminLevel) {
		this.adminLevel = adminLevel;
	}
	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", userName=" + userName + ",location="+location
				+ ", emailAddress=" + emailAddress + ", roleName=" + roleName
				+", adminLevel=" + adminLevel 
				+ ", roleId=" + roleId + ", roles=" + Arrays.toString(roles)
				+ ", userRoles=" + userRoles + ", saveParam=" + saveParam + "]";
	}

	public boolean isSuperUserLogged() {
		return isSuperUserLogged;
	}

	public void setSuperUserLogged(boolean isSuperUserLogged) {
		this.isSuperUserLogged = isSuperUserLogged;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}


}
