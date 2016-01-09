package com.eamtar.mccn.faces.managedbean;

import java.util.List;

import org.springframework.stereotype.Component;

import com.eamtar.mccn.faces.custom.scope.SpringViewScoped;
import com.eamtar.mccn.faces.model.MessageDTO;
import com.eamtar.mccn.model.Message;
import com.eamtar.mccn.model.MessageFeedback;
import com.eamtar.mccn.model.User;

@Component(value = "messageBean")
@SpringViewScoped
public class MessageBean {

	private boolean intialized = false;
	private String viewType = "inbox";
	private String toEmail = null;
	private String replyMessage = null;
	private Message selectedMessage = null;
	private MessageDTO selectedMessageDTO = null;
	private MessageFeedback selectedMessageFeedback = null;
	private List<User> users = null;
	private List<MessageDTO> messageDTOs = null;
	
	
	private Integer receiverId = null;
	private String 	subject = null;
	private String 	messageBody = null;

	public boolean isIntialized() {
		return intialized;
	}

	public void setIntialized(boolean intialized) {
		this.intialized = intialized;
	}

	public String getToEmail() {
		return toEmail;
	}

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	public String getReplyMessage() {
		return replyMessage;
	}

	public void setReplyMessage(String replyMessage) {
		this.replyMessage = replyMessage;
	}


	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getViewType() {
		return viewType;
	}

	public void setViewType(String viewType) {
		this.viewType = viewType;
	}

	public Integer getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Message getSelectedMessage() {
		return selectedMessage;
	}

	public void setSelectedMessage(Message selectedMessage) {
		this.selectedMessage = selectedMessage;
	}

	public MessageDTO getSelectedMessageDTO() {
		return selectedMessageDTO;
	}

	public void setSelectedMessageDTO(MessageDTO selectedMessageDTO) {
		this.selectedMessageDTO = selectedMessageDTO;
	}

	public MessageFeedback getSelectedMessageFeedback() {
		return selectedMessageFeedback;
	}

	public void setSelectedMessageFeedback(MessageFeedback selectedMessageFeedback) {
		this.selectedMessageFeedback = selectedMessageFeedback;
	}

	public List<MessageDTO> getMessageDTOs() {
		return messageDTOs;
	}

	public void setMessageDTOs(List<MessageDTO> messageDTOs) {
		this.messageDTOs = messageDTOs;
	}

	public String getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

}
