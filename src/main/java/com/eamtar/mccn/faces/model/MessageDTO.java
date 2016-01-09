package com.eamtar.mccn.faces.model;

import java.io.Serializable;
import java.util.List;

import com.eamtar.mccn.model.Message;

public class MessageDTO implements Serializable{

	private static final long serialVersionUID = -7043425383072208792L;
	private Integer messageId;
	private Message message;
	private List<Message> replies;
	
	public Integer getMessageId() {
		return messageId;
	}
	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	public List<Message> getReplies() {
		return replies;
	}
	public void setReplies(List<Message> replies) {
		this.replies = replies;
	}
	
}
