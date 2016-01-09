package com.eamtar.mccn.dao;

import java.util.List;

import com.eamtar.mccn.model.Message;

/**
 * @author ADNAN GHAZANFAR
 * @email adnan.ghazanfar@yahoo.com
 * @since 01 JULY, 2014
 */
@SuppressWarnings("all")
public interface MessageDao extends GenericDAO<Message, Integer> {

	public List<Message> getMessagesByCriteria(Message message,
			String messageType);

}