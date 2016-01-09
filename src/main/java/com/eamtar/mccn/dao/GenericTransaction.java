package com.eamtar.mccn.dao;
/**
 * @author ADNAN GHAZANFAR
 * @email  adnan.ghazanfar@yahoo.com
 * @since 01 JULY, 2014
 */
@SuppressWarnings("all")
public interface GenericTransaction {
	
	boolean begin();
	boolean commit();
	boolean rollback();
	

}
