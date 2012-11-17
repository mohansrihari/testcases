package com.mohansrihari.dao;

import java.io.Serializable;

public interface Dao<E> {
	String persist(E entity);
    void remove(E entity);
    E findById(Serializable id);
    E update(E item);	
}