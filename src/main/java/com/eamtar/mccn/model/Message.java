package com.eamtar.mccn.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class Message implements Serializable {

	private static final long serialVersionUID = 8427479680088028498L;
	private Integer messageId;
	private String subject;
	private String messageBody;
	private String messageType;
	private Date sentOn;
	private Date viewedOn;
	private Date updatedOn;
	private Integer receiverStatus;
	private Integer priority = 2;
	private User sender;
	private User receiver;
	private Integer parentId;
	private Integer messageStatus;
	private Integer senderStatus;
	private Set<Message> replies;

	public Integer getReceiverStatus() {
		return receiverStatus;
	}

	public void setReceiverStatus(Integer receiverStatus) {
		this.receiverStatus = receiverStatus;
	}

	public Integer getMessageStatus() {
		return messageStatus;
	}

	public void setMessageStatus(Integer messageStatus) {
		this.messageStatus = messageStatus;
	}

	public Integer getSenderStatus() {
		return senderStatus;
	}

	public void setSenderStatus(Integer senderStatus) {
		this.senderStatus = senderStatus;
	}

	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public Date getSentOn() {
		return sentOn;
	}

	public void setSentOn(Date sentOn) {
		this.sentOn = sentOn;
	}

	public Date getViewedOn() {
		return viewedOn;
	}

	public void setViewedOn(Date viewedOn) {
		this.viewedOn = viewedOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Set<Message> getReplies() {
		return replies;
	}

	public void setReplies(Set<Message> replies) {
		this.replies = replies;
	}

}
