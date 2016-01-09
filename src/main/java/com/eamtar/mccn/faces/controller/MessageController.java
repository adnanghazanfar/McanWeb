package com.eamtar.mccn.faces.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.eamtar.mccn.email.EmailBean;
import com.eamtar.mccn.email.EmailService;
import com.eamtar.mccn.faces.managedbean.MessageBean;
import com.eamtar.mccn.faces.managedbean.UserBean;
import com.eamtar.mccn.faces.model.MessageDTO;
import com.eamtar.mccn.model.Message;
import com.eamtar.mccn.model.MessageFeedback;
import com.eamtar.mccn.model.User;
import com.eamtar.mccn.service.ManagerService;
import com.eamtar.mccn.service.MessageService;
import com.eamtar.mccn.service.ServiceManagerFactory;
import com.eamtar.mccn.service.UserService;
import com.eamtar.mccn.util.ProjectConstant;

@Component(value = "messageController")
@Scope("request")
public class MessageController extends AbstractController {

	private final String SUCCESS_MESSAGE_SAVE = "Message Sent Successfully";
	private final String ERROR_MESSAGE_SAVE = "An Error has occured while Sending Message";
	private final String SUCCESS_MESSAGE_DELETE = "Message Deleted Successfully";
	private final String ERROR_MESSAGE_DELETE = "An Error has occured while Deleting Message";
	private final String ERROR_MESSAGE_EXCEPTION = "An Exception has occured please see logs.";
	private final String NO_REDIRECTION = null;

	private static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("HH:mm dd-MMM-yyyy");
	
	@Autowired
	private MessageBean messageBean;
	@Autowired
	private UserBean userBean;
	@Autowired
	private ManagerService managerService;
	
	public void initializeValues() {
		String viewType = getParamInString("viewType");

		if (viewType != null)
			messageBean.setViewType(viewType);

		if (userBean != null && userBean.getUserId() != null && !messageBean.isIntialized()) {

			if (viewType.equalsIgnoreCase(ProjectConstant.MESSAGE_TYPE_INBOX)) {
				MessageService messageService = managerService.getMessageService();
				// GET INBOX
				Message searchMessage = new Message();
				searchMessage.setReceiver(userBean.getUser());

				List<Message> messages = messageService.getMessagesByCriteria(searchMessage,
						ProjectConstant.MESSAGE_TYPE_INBOX);
				messageBean.setMessageDTOs(convertToMessageDTOList(messages));
			} else if (viewType.equalsIgnoreCase(ProjectConstant.MESSAGE_TYPE_COMPOSE)) {
				UserService userService = managerService.getUserService();

				User searchUser = new User();
				searchUser.setStatus(ProjectConstant.STATUS_ACTIVE);
				List<User> users = userService.findViaCriteria(searchUser);
				messageBean.setUsers(users);

				Message message = new Message();
				message.setReceiver(searchUser);
				message.setSender(userBean.getUser());
				messageBean.setSelectedMessage(message);
			} else if (viewType.equalsIgnoreCase(ProjectConstant.MESSAGE_TYPE_OUTBOX)) {
				MessageService messageService = managerService.getMessageService();
				// GET SENT ITEMS
				Message searchMessage = new Message();
				searchMessage.setSender(userBean.getUser());

				List<Message> messages = messageService.getMessagesByCriteria(searchMessage,
						ProjectConstant.MESSAGE_TYPE_OUTBOX);
				messageBean.setMessageDTOs(convertToMessageDTOList(messages));
			} else if (viewType.equalsIgnoreCase(ProjectConstant.MESSAGE_TYPE_DELETED)) {
				MessageService messageService = managerService.getMessageService();
				// GET INBOX
				Message searchMessage = new Message();
				searchMessage.setReceiver(userBean.getUser());

				List<Message> messages = messageService.getMessagesByCriteria(searchMessage,
						ProjectConstant.MESSAGE_TYPE_DELETED);
				messageBean.setMessageDTOs(convertToMessageDTOList(messages));
			}

			messageBean.setIntialized(Boolean.TRUE);
		}

	}

	private List<MessageDTO> convertToMessageDTOList(List<Message> messages) {
		List<MessageDTO> messageDTOs = null;
		if (messages != null) {
			messageDTOs = new ArrayList<MessageDTO>();
			for (Message message : messages) {
				MessageDTO messageDTO = convertToMessageDTO(message);
				messageDTOs.add(messageDTO);
			}
		}
		return messageDTOs;
	}

	private MessageDTO convertToMessageDTO(Message message) {
		MessageDTO messageDTO = null;
		if (message != null) {
			messageDTO = new MessageDTO();
			messageDTO.setMessageId(message.getMessageId());
			messageDTO.setMessage(message);
			List<Message> replies = null;
			if (message.getReplies() != null)
				replies = new ArrayList<Message>(message.getReplies());
			else
				replies = new ArrayList<Message>();
			replies.add(0, message);
			messageDTO.setReplies(replies);
		}
		return messageDTO;
	}

	public void initializeValuesForFeedback() {
		MessageFeedback messageFeedback = new MessageFeedback();
		messageBean.setSelectedMessageFeedback(messageFeedback);
	}

	public String sendMessage() {
		String messageStr = null;

		try {

			Date currentDate = new Date();
			Message message = messageBean.getSelectedMessage();
			message.setSentOn(currentDate);
			message.setUpdatedOn(currentDate);
			message.setMessageStatus(ProjectConstant.MESSAGE_STATUS_UNREAD);
			message.setSenderStatus(ProjectConstant.STATUS_NOT_DELETED);
			message.setReceiverStatus(ProjectConstant.STATUS_NOT_DELETED);

			UserService userService = managerService.getUserService();
			User sender = message.getSender();
			User receiver = userService.getById(message.getReceiver().getUserId());

			MessageService messageService = managerService.getMessageService();
			message = messageService.save(message);
			if (message != null && message.getMessageId() != null) {
				EmailService emailService = managerService.getEmailService();
				EmailBean messageEmail = EmailBean.getMessageEmailBean(sender.getEmailAddress(),
						receiver.getEmailAddress(), sender.getFirstNameFirst(), message.getSubject(),
						message.getMessageBody());
				emailService.sendMail(messageEmail);
				User searchUser = new User();
				Message newMessage = new Message();
				newMessage.setReceiver(searchUser);
				newMessage.setSender(message.getSender());
				messageBean.setSelectedMessage(newMessage);
				messageStr = SUCCESS_MESSAGE_SAVE;
			} else
				messageStr = ERROR_MESSAGE_SAVE;
		} catch (Exception exception) {
			logger.error(exception.getMessage());
			messageStr = ERROR_MESSAGE_EXCEPTION;
		}

		addMessageToFacesContext(messageStr);
		return NO_REDIRECTION;
	}

	public String sendReplyMessage() {
		String messageStr = null;

		try {

			Integer userId = userBean.getUserId();
			
			Date currentDate = new Date();
			MessageDTO selectedMessageDTO = messageBean.getSelectedMessageDTO();
			Message message = selectedMessageDTO.getMessage();
			message.setMessageStatus(ProjectConstant.MESSAGE_STATUS_UNREAD);
			message.setUpdatedOn(currentDate);

			String subject = message.getSubject();
			if (message.getParentId() == null)
				subject = "Reply To ::: " + message.getSubject();

			Message replyMessage = new Message();
			replyMessage.setSubject(subject);
			
			User sender,receiver;
			if(userId.intValue() == message.getSender().getUserId().intValue()){
				sender = message.getSender();
				receiver = message.getReceiver();
			} else{
				sender = message.getReceiver();
				receiver = message.getSender();
			}
			
			replyMessage.setSender(sender);
			replyMessage.setReceiver(receiver);
			replyMessage.setSentOn(currentDate);
			replyMessage.setMessageStatus(ProjectConstant.MESSAGE_STATUS_UNREAD);
			replyMessage.setSenderStatus(ProjectConstant.STATUS_NOT_DELETED);
			replyMessage.setReceiverStatus(ProjectConstant.STATUS_NOT_DELETED);
			replyMessage.setParentId(message.getMessageId());
			replyMessage.setMessageBody(messageBean.getReplyMessage());

			sender = replyMessage.getSender();
			receiver = replyMessage.getReceiver();

			MessageService messageService = managerService.getMessageService();
			replyMessage = messageService.save(replyMessage);
			message = messageService.update(message);
			if (message != null && replyMessage != null && replyMessage.getMessageId() != null) {
				
				// Update Message Replies
				for (MessageDTO messageDTO : messageBean.getMessageDTOs()) {
					if(messageDTO.getMessageId().intValue() == selectedMessageDTO.getMessageId().intValue()){
						messageDTO.getReplies().add(replyMessage);
						break;
					}
				}
				
				EmailService emailService = managerService.getEmailService();
				EmailBean messageEmail = EmailBean.getMessageEmailBean(sender.getEmailAddress(),
						receiver.getEmailAddress(), sender.getFirstNameFirst(), subject, replyMessage.getMessageBody());
				emailService.sendMail(messageEmail);
				User searchUser = new User();
				Message newMessage = new Message();
				newMessage.setReceiver(searchUser);
				newMessage.setSender(sender);
				messageBean.setSelectedMessage(newMessage);
				messageBean.setReplyMessage(null);
				messageStr = SUCCESS_MESSAGE_SAVE;
			} else
				messageStr = ERROR_MESSAGE_SAVE;
		} catch (Exception exception) {
			logger.error(exception.getMessage());
			messageStr = ERROR_MESSAGE_EXCEPTION;
		}

		addMessageToFacesContext(messageStr);
		return NO_REDIRECTION;
	}

	public String sendMessageFeedback() {
		String messageStr = null;

		try {

			MessageFeedback messageFeedback = messageBean.getSelectedMessageFeedback();
			messageFeedback.setSentTime(new Date());

			MessageService messageService = managerService.getMessageService();
			messageFeedback = messageService.save(messageFeedback);
			if (messageFeedback != null && messageFeedback.getMessageId() != null) {
				EmailService emailService = managerService.getEmailService();
				EmailBean feedbackEmail = EmailBean.getFeedBackEmailBean(messageFeedback.getEmail(),
						messageFeedback.getName(), messageFeedback.getSubject(), messageFeedback.getMessageBody());
				emailService.sendMail(feedbackEmail);
				messageFeedback = new MessageFeedback();
				messageBean.setSelectedMessageFeedback(messageFeedback);
				messageStr = SUCCESS_MESSAGE_SAVE;
			} else
				messageStr = ERROR_MESSAGE_SAVE;
		} catch (Exception exception) {
			logger.error(exception.getMessage());
			messageStr = ERROR_MESSAGE_EXCEPTION;
		}

		addMessageToFacesContext(messageStr);

		return NO_REDIRECTION;
	}

	public List<User> selectSender(String query) {
		Integer userId = userBean.getUserId();
		List<User> results = new ArrayList<User>();
		List<User> users = messageBean.getUsers();
		User user = null;
		if (userId != null && query != null) {
			for (int i = 0; i < users.size(); i++) {
				user = users.get(i);
				if (user.getUserId().intValue() != userId
						&& user.getFirstNameFirst().toLowerCase().startsWith(query.toLowerCase())) {
					results.add(user);
				}
			}
		}
		return results;
	}

	public String deleteMessage(MessageDTO deleteMessageDTO) {

		String messageStr = null;

		try {
			MessageService messageService = managerService.getMessageService();
			Message deleteMessage = deleteMessageDTO.getMessage();
			deleteMessage.setSenderStatus(ProjectConstant.STATUS_DELETED);
			deleteMessage = messageService.update(deleteMessage);

			if (deleteMessage != null) {
				messageBean.getMessageDTOs().remove(deleteMessageDTO);
				messageStr = SUCCESS_MESSAGE_DELETE;
			} else {
				messageStr = ERROR_MESSAGE_DELETE;
			}

		} catch (Exception exception) {
			logger.error(exception.getMessage());
			messageStr = ERROR_MESSAGE_EXCEPTION;
		}

		addMessageToFacesContext(messageStr);

		return NO_REDIRECTION;
	}

	public String reduceDescriptionText(String text) {
		if (text == null || text.length() <= 100)
			return text;
		return text.substring(0, 100).concat(" ...");
	}

	public String convertToDate(Date date) {
		if (date == null)
			return "";
		return dateTimeFormat.format(date);
	}

	public void onRowSelect() {
		try {
			Integer userId = userBean.getUserId();
			Message message = messageBean.getSelectedMessageDTO().getMessage();
			if (message.getMessageStatus().intValue() == 0 && message.getSender().getUserId().intValue() != userId) {
				MessageService messageService = managerService.getMessageService();
				message.setMessageStatus(ProjectConstant.MESSAGE_STATUS_READ);
				message = messageService.update(message);
			}

		} catch (Exception exception) {
			logger.error(exception.getMessage());
		}
	}

	public void sendShortMessage(User receiver) {

		String messageStr = null;

		try {

			User sender = userBean.getUser();
			Date currentDate = new Date();
			Message message = new Message();
			message.setSentOn(currentDate);
			message.setUpdatedOn(currentDate);
			message.setMessageStatus(ProjectConstant.MESSAGE_STATUS_UNREAD);
			message.setSenderStatus(ProjectConstant.STATUS_NOT_DELETED);
			message.setReceiverStatus(ProjectConstant.STATUS_NOT_DELETED);
			message.setSubject(messageBean.getSubject());
			message.setMessageBody(messageBean.getMessageBody());
			message.setReceiver(receiver);
			message.setSender(sender);
			
			MessageService messageService = managerService.getMessageService();
			message = messageService.save(message);
			if (message != null && message.getMessageId() != null) {
				EmailService emailService = managerService.getEmailService();
				EmailBean messageEmail = EmailBean.getMessageEmailBean(sender.getEmailAddress(),
						receiver.getEmailAddress(), sender.getFirstNameFirst(), message.getSubject(),
						message.getMessageBody());
				emailService.sendMail(messageEmail);
				messageBean.setSubject(null);
				messageBean.setMessageBody(null);
				messageStr = SUCCESS_MESSAGE_SAVE;
			} else
				messageStr = ERROR_MESSAGE_SAVE;
		} catch (Exception exception) {
			logger.error(exception.getMessage());
			messageStr = ERROR_MESSAGE_EXCEPTION;
		}

		addMessageToFacesContext(messageStr);
	}

	public void mccnUserValidate(FacesContext context, UIComponent component, Object value) {

		UserService userService = managerService.getUserService();
		String email = value.toString();
		User user = userService.getUserByEmail(email);
		if (user == null) {
			FacesMessage doneMessage = null;
			doneMessage = new FacesMessage("This is not a MCCN user.", "You can only send messages to MCCN users.");
			doneMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(doneMessage);
		} else {
			messageBean.getSelectedMessage().setReceiver(user);
		}
	}
}
