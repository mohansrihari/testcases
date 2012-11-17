package com.mohansrihari.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import com.mohansrihari.constant.JPAConstant;

public abstract class JpaDao<E> implements Dao<E> {

	public transient Class<E> entityClass;

	@PersistenceContext(unitName = JPAConstant.PERSISTANCE_UNIT)
	protected transient EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public JpaDao() {
		final ParameterizedType genericSuperclass = (ParameterizedType) getClass()
				.getGenericSuperclass();
		this.entityClass = (Class<E>) genericSuperclass
				.getActualTypeArguments()[0];
	}

	public String persist(final E entity) {
		if (entity == null) {
			throw new PersistenceException("Item may not be null");
		}
		final E entity2 = entityManager.merge(entity);
		entityManager.persist(entity2);
		Object id = entityManager.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(entity2);
		return id.toString();
	}

	public E update(final E item) {
		if (item == null) {
			throw new PersistenceException("Item may not be null");
		}
		entityManager.merge(item);
		return item;
	}

	public void remove(final E entity) {
		if (entity == null) {
			throw new PersistenceException("Item may not be null");
		}
		entityManager.remove(entity);
	}

	public E findById(final Serializable entityId) {
		if (entityId == null) {
			throw new PersistenceException("Id may not be null or negative");
		}
		return entityManager.find(entityClass, entityId);
	}

	public List<E> execute(final String jpaQry) {
		try {
			@SuppressWarnings("unchecked")
			final List<E> resutList = this.entityManager.createQuery(jpaQry)
					.getResultList();
			return resutList;
		} catch (Exception exception) {
			throw new PersistenceException(
					"Exception while fetching the records");
		}
	}

}
