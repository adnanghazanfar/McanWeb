package com.eamtar.mccn.service;

import java.util.List;

import com.eamtar.mccn.model.Message;
import com.eamtar.mccn.model.MessageFeedback;

public interface MessageService {

	public Message save(Message message);
	public Message update(Message message);
	public String delete(Message message);
	public List<Message> getMessagesByCriteria(Message message,
			String messageType);
	
	public MessageFeedback save(MessageFeedback messageFeedback);

}
