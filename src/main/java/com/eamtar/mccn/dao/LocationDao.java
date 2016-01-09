package com.eamtar.mccn.dao;

import com.eamtar.mccn.model.Location;

/**
 * @author ADNAN GHAZANFAR
 * @email adnan.ghazanfar@yahoo.com
 * @since 04 SEP, 2014
 */
@SuppressWarnings("all")
public interface LocationDao extends GenericDAO<Location, Integer> {

	public Location isLocationExist(Location location);
}