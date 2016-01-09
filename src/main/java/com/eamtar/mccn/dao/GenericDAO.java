package com.eamtar.mccn.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
/**
 * @author ADNAN GHAZANFAR
 * @email  adnan.ghazanfar@yahoo.com
 * @since 01 JULY, 2014
 */
@SuppressWarnings("all")
public interface GenericDAO<T, ID extends Serializable> {
	
	public GenericTransaction getTransaction();
	
    T getById(ID id, boolean lock);

    T getById(ID id);

    T loadById(ID id);

    List<T> findAll();

    public Integer findMaxByColumn(String columnName);

    List<T> findByCriteria(Map criterias);

    public List<T> findByExample(T exampleInstance, String[] excludeProperty);

    T save(T entity);
    
    String saveList(List<T> entity);

    T update(T entity);

    T saveOrUpdate(T entity);

    String delete(T entity);
    
    public String deleteList(List<T> entityList);
    
    public String deleteAll();    

    void deleteById(ID id);

    public void merge(T entity);

    public void clear();

    public void evict(List<T> entityList);

    public void evict(T entity);
    
    public String saveOrUpdate(List<T> entityList) ;
    
}
