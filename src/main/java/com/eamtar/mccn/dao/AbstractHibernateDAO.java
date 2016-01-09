package com.eamtar.mccn.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author ADNAN GHAZANFAR
 * @email adnan.ghazanfar@yahoo.com
 * @since 01 JULY, 2014
 */
@SuppressWarnings("all")
public abstract class AbstractHibernateDAO<T, ID extends Serializable>
		implements GenericDAO<T, ID> {
	
	@Autowired
	@Qualifier("mySqlSessionFactory")
	private SessionFactory sessionFactory;
	private Class<T> persistentClass;
	private GenericTransaction transaction;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public GenericTransaction getTransaction() {
		return transaction;
	}

	public void setTransaction(GenericTransaction transaction) {
		this.transaction = transaction;
	}

	@SuppressWarnings("unchecked")
	public AbstractHibernateDAO() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];

	}

	protected Session getSession() {
		// if (session == null) {
		Session session = this.sessionFactory.getCurrentSession();
		// }
		// System.out.println("session.hashCode() = " + session.hashCode());
		if (session == null || session.isOpen() == false)
			session = this.sessionFactory.openSession();

		return session;
	}

	protected void closeSession() {
		// session = HibernateUtil.getSessionFactory().getCurrentSession();
		getSession().close();
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	@SuppressWarnings("unchecked")
	public T getById(ID id) {
		T value = (T) getSession().get(getPersistentClass(), id);
		return value;
	}

	@SuppressWarnings("unchecked")
	public T getById(ID id, boolean lock) {
		if (lock) {
			return (T) getSession().get(getPersistentClass(), id,
					LockMode.UPGRADE);
		} else
			return getById(id);
	}

	@SuppressWarnings("unchecked")
	public T loadById(ID id) {
		return (T) getSession().load(getPersistentClass(), id);
	}

	public T save(T entity) {
		try {
			Session session = getSession();
			session.save(entity);
			return entity;
		} catch (RuntimeException re) {
			re.printStackTrace();
			return null;
		}

	}

	public String saveList(List<T> entityList) {
		try {
			Session ses = getSession();
			for (T entity : entityList) {
				ses.save(entity);
			}
			return "success";
		} catch (RuntimeException re) {
			re.printStackTrace();
			return "failure";
		}
	}

	public T update(T entity) {
		try {
			getSession().update(entity);
			return entity;
		} catch (RuntimeException re) {
			return entity;
		}
	}

	public T saveOrUpdate(T entity) {
		try {
			Session ses = getSession();
			ses.saveOrUpdate(entity);
			return entity;
		} catch (RuntimeException re) {
			return null;
		}
	}

	public String saveOrUpdate(List<T> entityList) {
		try {
			Session ses = getSession();
			for (T entity : entityList) {
				ses.merge(entity);
			}
			return "success";
		} catch (RuntimeException re) {
			return "failure";
		}
	}

	public void evict(T entity) {
		Session ses = getSession();
		if (entity != null && ses != null && ses.contains(entity)) {
			ses.evict(entity);
		}
	}

	public void evict(List<T> entityList) {
		// Session ses = getSession();
		for (T entity : entityList) {
			evict(entity);
		}
	}

	public void merge(T entity) {
		getSession().merge(entity);
	}

	public void clear() {
		getSession().clear();
	}

	public String deleteList(List<T> entityList) {
		try {
			Session ses = getSession();
			for (T entity : entityList) {
				ses.delete(entity);
			}
			return "success";
		} catch (RuntimeException re) {
			return "failure";
		}
	}

	public String deleteAll() {
		try {
			List<T> entityList = findAll();
			Session ses = getSession();
			for (T entity : entityList) {
				ses.delete(entity);
			}
			return "success";
		} catch (RuntimeException re) {
			return "failure";
		}
	}

	public String delete(T entity) {
		try {
			getSession().delete(entity);
			return "success";
		} catch (RuntimeException re) {
			return "failure";
		}

	}

	public void deleteById(ID id) {
		getSession().delete(loadById(id));
	}

	public List<T> findAll() {
		return findByCriteria();
	}

	/**
	 * Use this inside subclasses as a convenience method.
	 */

	protected List<T> findByCriteria(Criterion... criterion) {
		List<T> list = null;
		Session ses = getSession();
		Criteria crit = ses.createCriteria(getPersistentClass());
		for (Criterion c : criterion) {
			crit.add(c);
		}
		list = crit.list();
		return list;
	}

	public Integer findMaxByColumn(String columnName) {
		Integer result = null;
		Session ses = getSession();
		Criteria crit = ses.createCriteria(getPersistentClass());
		crit.setProjection(Projections.max(columnName));
		result = (Integer) crit.uniqueResult();
		return result;
	}

	/**
	 * Find by criteria.
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(Map criterias) {

		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.allEq(criterias));
		return criteria.list();
	}

	/**
	 * This method will execute an HQL query and return the number of affected
	 * entities.
	 */
	protected int executeQuery(String query, String[] namedParams,
			Object[] params) {
		Query q = getSession().createQuery(query);

		if (namedParams != null) {
			for (int i = 0; i < namedParams.length; i++) {
				q.setParameter(namedParams[i], params[i]);
			}
		}

		return q.executeUpdate();
	}

	protected int executeQuery(String query) {
		return executeQuery(query, null, null);
	}

	/**
	 * This method will execute a Named HQL query and return the number of
	 * affected entities.
	 */
	protected int executeNamedQuery(String namedQuery, String[] namedParams,
			Object[] params) {
		Query q = getSession().getNamedQuery(namedQuery);

		if (namedParams != null) {
			for (int i = 0; i < namedParams.length; i++) {
				q.setParameter(namedParams[i], params[i]);
			}
		}

		return q.executeUpdate();
	}

	protected int executeNamedQuery(String namedQuery) {
		return executeNamedQuery(namedQuery, null, null);
	}

	@SuppressWarnings("unchecked")
	public List<T> findByExample(T exampleInstance, String[] excludeProperty) {
		Criteria crit = getSession().createCriteria(getPersistentClass());
		Example example = Example.create(exampleInstance).excludeZeroes()
				.enableLike().ignoreCase();
		for (String exclude : excludeProperty) {
			example.excludeProperty(exclude);
		}
		crit.add(example);
		return crit.list();
	}

}
