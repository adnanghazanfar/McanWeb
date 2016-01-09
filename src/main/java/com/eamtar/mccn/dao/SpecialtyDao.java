package com.eamtar.mccn.dao;
import java.util.List;

import com.eamtar.mccn.model.Specialty;

/**
 * @author ADNAN GHAZANFAR
 * @email  adnan.ghazanfar@yahoo.com
 * @since 09 SEP, 2014
 */
@SuppressWarnings("all")
public interface SpecialtyDao extends GenericDAO<Specialty, Integer>{
	
	public List<Specialty> findByParentId(Integer parentId);
	
}