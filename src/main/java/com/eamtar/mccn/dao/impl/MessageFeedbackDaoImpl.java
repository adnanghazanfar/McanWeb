package com.eamtar.mccn.dao.impl;

import org.springframework.stereotype.Repository;

import com.eamtar.mccn.dao.AbstractHibernateDAO;
import com.eamtar.mccn.dao.MessageFeedbackDao;
import com.eamtar.mccn.model.MessageFeedback;

@Repository
public class MessageFeedbackDaoImpl extends AbstractHibernateDAO<MessageFeedback, Integer>
		implements MessageFeedbackDao {

}