package com.mohansrihari.unit.dao.impl;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mohansrihari.constant.ConfigurationIDS;
import com.mohansrihari.dao.impl.UsersDaoImpl;
import com.mohansrihari.entity.User;

public class UserDaoImplTest {

    @InjectMocks
    UsersDaoImpl userDAO;

    @Mock
    private EntityManager entityManager;

    @Mock
    private Query query;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        when(entityManager.createQuery(anyString())).thenReturn(query);
    }

    @Test
    public void testGetAllUsersList() {
        User user = new User();
        user.setFirstName("Test");
        user.setUserId("1");
        List<User> list = new ArrayList<User>();
        list.add(user);
        when(query.setParameter(1, ConfigurationIDS.ACTIVE_STATUS)).thenReturn(query);
        when(query.getResultList()).thenReturn(list);
        final List<User> userList = userDAO.getUserProfiles();
        Assert.assertNotNull(userList);
    }

}