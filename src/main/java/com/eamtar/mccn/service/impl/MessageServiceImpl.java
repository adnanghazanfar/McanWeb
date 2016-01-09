package com.eamtar.mccn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.eamtar.mccn.dao.ManagerDAO;
import com.eamtar.mccn.model.Message;
import com.eamtar.mccn.model.MessageFeedback;
import com.eamtar.mccn.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService{
	
	@Autowired
	@Qualifier("managerDao")
	private ManagerDAO managerDao;
	
	@Override
	public Message save(Message message) {
		// TODO Auto-generated method stub
		return managerDao.getMessageDao().save(message);
	}

	@Override
	public Message update(Message message) {
		// TODO Auto-generated method stub
		return managerDao.getMessageDao().update(message);
	}

	@Override
	public String delete(Message message) {
		// TODO Auto-generated method stub
		return managerDao.getMessageDao().delete(message);
	}

	@Override
	public List<Message> getMessagesByCriteria(Message message,
			String messageType) {
		// TODO Auto-generated method stub
		return managerDao.getMessageDao().getMessagesByCriteria(message,messageType);
	}

	@Override
	public MessageFeedback save(MessageFeedback messageFeedback) {
		// TODO Auto-generated method stub
		return managerDao.getMessageFeedbackDao().save(messageFeedback);
	}

}
