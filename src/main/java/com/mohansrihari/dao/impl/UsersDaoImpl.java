package com.mohansrihari.dao.impl;

import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.stereotype.Repository;

import com.mohansrihari.constant.ConfigurationIDS;
import com.mohansrihari.dao.JpaDao;
import com.mohansrihari.dao.UsersDao;
import com.mohansrihari.entity.User;
import com.mohansrihari.exception.DAOException;

@Repository
public class UsersDaoImpl extends JpaDao<User> implements UsersDao {

	public void saveUsersDetails(final User users) {
		try {
			users.setStatus(ConfigurationIDS.ACTIVE_STATUS);
			persist(users);
		} catch (PersistenceException exception) {
			throw new DAOException(exception.toString());
		} catch (Exception exception) {
			throw new DAOException(exception.toString());
		}
	}

	public void updateUserProfile(final User users) {
		try {
			users.setStatus(ConfigurationIDS.ACTIVE_STATUS);
			update(users);
		} catch (PersistenceException exception) {
			throw new DAOException(exception.toString());
		} catch (Exception exception) {
			throw new DAOException(exception.toString());
		}
	}


	public User findUserByEmail(final String emailAddress) {
		@SuppressWarnings("unchecked")
		final List<User> userList = entityManager
				.createQuery(
						"select users from User users where trim(users.emailAddress)=?1")
				.setParameter(1, emailAddress)
				.getResultList();
		if (userList.size() > 0) {
			return userList.get(0);
		} else {
			return null;
		}
	}

	public User findUserByUserId(final String userId) {
		@SuppressWarnings("unchecked")
		final List<User> userList = entityManager
				.createQuery(
						"select users from User users where trim(users.userId)=?1")
				.setParameter(1, userId)
				.getResultList();
		if (userList.size() > 0) {
			return userList.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<User> getUserProfiles() {
		return entityManager
				.createQuery("select users from User users order by users.firstName asc ")
				.getResultList();
	}
	
	
	@Override
	public User findUserByUserNameAndPassword(String userName,String password){
		try {
			@SuppressWarnings("unchecked")
			final List<User> userList = entityManager
					.createQuery(
							"select user from User user where trim(user.userName)=?1 and trim(user.password)=?2 and user.status=?3")
					.setParameter(1, userName).setParameter(2, password).setParameter(3, ConfigurationIDS.ACTIVE_STATUS)
					.getResultList();
			if (userList.size() > 0) {
				return userList.get(0);
			} else {
				return null;
			}
		} catch (Exception exception) {
			throw new DAOException(exception.toString());
		}
	}
	
	@Override
	public User getUserByUserName(String userName) {
		try {
			@SuppressWarnings("unchecked")
			final List<User> userList = entityManager
					.createQuery(
							"select user from User user where trim(user.userName)=?1 and user.status=?2")
					.setParameter(1, userName)
			        .setParameter(2, ConfigurationIDS.ACTIVE_STATUS).getResultList();
			if (userList.size() > 0) {
				return userList.get(0);
			} else {
				return null;
			}
		} catch (Exception exception) {
			throw new DAOException(exception.toString());
		}
	}

	@Override
	public void inActiveUser(User user) {
		try {
			user.setStatus(ConfigurationIDS.IN_ACTIVE_STATUS);
			update(user);
		} catch (PersistenceException exception) {
			throw new DAOException(exception.toString());
		} catch (Exception exception) {
			throw new DAOException(exception.toString());
		}
	
		
	}

}