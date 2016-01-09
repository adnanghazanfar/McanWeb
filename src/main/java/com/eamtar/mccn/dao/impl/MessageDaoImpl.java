package com.eamtar.mccn.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.eamtar.mccn.dao.AbstractHibernateDAO;
import com.eamtar.mccn.dao.MessageDao;
import com.eamtar.mccn.model.Message;
import com.eamtar.mccn.util.ProjectConstant;

@Repository
public class MessageDaoImpl extends AbstractHibernateDAO<Message, Integer> implements MessageDao {

	private List<Message> getMessageListbyId(String messageIds) {
		String hqlStr = "from Message message where messageId in (" + messageIds + ")";
		hqlStr += " order by message.sentOn desc";
		Query query = getSession().createQuery(hqlStr);
		return query.list();
	}

	@Override
	public List<Message> getMessagesByCriteria(Message message, String messageType) {
		// TODO Auto-generated method stub
		String hqlStr = "SELECT DISTINCT COALESCE(parentId,messageId) from Message message where 1=1 ";

		if (messageType.equalsIgnoreCase(ProjectConstant.MESSAGE_TYPE_INBOX)) { // FOR
																				// INBOX

			hqlStr += " and message.messageStatus != " + ProjectConstant.MESSAGE_STATUS_DRAFT;
			hqlStr += " and message.senderStatus=" + ProjectConstant.STATUS_NOT_DELETED;

			if (message.getReceiver() != null) {
				hqlStr += " and message.receiver.userId=" + message.getReceiver().getUserId();
			}

		} else if (messageType.equalsIgnoreCase(ProjectConstant.MESSAGE_TYPE_OUTBOX)) { // FOR
																						// SEND
																						// MAILS
																						// AND
																						// DRAFTS

			hqlStr += " and message.messageStatus != " + ProjectConstant.MESSAGE_STATUS_DRAFT;
			hqlStr += " and message.receiverStatus=" + ProjectConstant.STATUS_NOT_DELETED;

			if (message.getSender() != null) {
				hqlStr += " and message.sender.userId=" + message.getSender().getUserId();
			}

		} else if (messageType.equalsIgnoreCase(ProjectConstant.MESSAGE_TYPE_DELETED)) { // FOR
																							// DELETED
																							// MAILS

			hqlStr += " and message.messageStatus != " + ProjectConstant.MESSAGE_STATUS_DRAFT;
			hqlStr += " and message.senderStatus=" + ProjectConstant.STATUS_DELETED;

			if (message.getReceiver() != null) {
				hqlStr += " and message.receiver.userId=" + message.getReceiver().getUserId();
			}

		}

		hqlStr += " order by message.sentOn desc";

		Query query = getSession().createQuery(hqlStr);
		List<Integer> messageIdList = query.list();

		String messageIds = getMessageIds(messageIdList);

		return getMessageListbyId(messageIds);
	}

	private String getMessageIds(List<Integer> messageIdList) {
		String messageIds = "0";

		for (Integer messageId : messageIdList) {
			messageIds += "," + Integer.valueOf(messageId);
		}

		return messageIds;
	}

}