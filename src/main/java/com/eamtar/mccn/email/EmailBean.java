package com.eamtar.mccn.email;

import com.eamtar.mccn.properties.ApplicationProperties;

public class EmailBean {

	private String fromEmail;
	private String senderEmail;
	private String subject;
	private String messageBody;

	private EmailBean() {
	}

	public static EmailBean getMessageEmailBean(String senderEmail, String receiverEmail, String receiverName, String subject,
			String messageText) {
		EmailBean emailBean = new EmailBean();
		emailBean.setFromEmail(senderEmail);
		emailBean.setSubject(ApplicationProperties.USER_MESSAGE_EMAIL_SUBJECT.getValue());
		emailBean.setSenderEmail(receiverEmail);
		StringBuffer messageBody = new StringBuffer();
		messageBody.append("<html><body style='font-family:sans-serif;'>").append("<b style='font-size:13px;'>")
				.append(ApplicationProperties.USER_MESSAGE_EMAIL_SUBJECT.getValue()).append("</b><hr />")
				.append("<div style='margin-left:20px;'>").append("<p style='font-size:12px;'>")
				.append("Subject : " + subject).append("<br />").append("Message : <br />" + messageText)
				.append("<br />").append("<br />")
				.append("From : " + receiverName).append("<br /><br /></p>").append("</div>").append("<hr />")
				.append("</body></html>");
		emailBean.setMessageBody(messageBody.toString());
		return emailBean;
	}

	public static EmailBean getFeedBackEmailBean(String senderEmail, String name, String subject, String messageText) {
		subject = ApplicationProperties.FEEDBACK_EMAIL_SUBJECT.getValue() +" -- "+subject;
		EmailBean emailBean = new EmailBean();
		emailBean.setFromEmail(ApplicationProperties.HOST_EMAIL_ADDRESS.getValue());
		emailBean.setSubject(subject);
		emailBean.setSenderEmail(ApplicationProperties.FEEDBACK_EMAIL_ADDRESS.getValue());
		StringBuffer messageBody = new StringBuffer();
		messageBody.append("<html><body style='font-family:sans-serif;'>").append("<b style='font-size:13px;'>")
				.append(ApplicationProperties.FEEDBACK_EMAIL_SUBJECT.getValue()).append("</b><hr />")
				.append("<div style='margin-left:20px;'>").append("<p style='font-size:12px;'>")
				.append("Subject : " + subject).append("<br />").append("Details : <br />" + messageText)
				.append("<br />").append("<br />").append("Customer Information").append("<br />")
				.append("Email : " + senderEmail).append("<br />").append("Name : " + name).append("<br /><br /></p>")
				.append("</div>").append("<hr />").append("</body></html>");
		emailBean.setMessageBody(messageBody.toString());
		return emailBean;
	}

	public static EmailBean getRegistrationEmailBean(String senderEmail, String password, String url) {
		EmailBean emailBean = new EmailBean();
		emailBean.setFromEmail(ApplicationProperties.HOST_EMAIL_ADDRESS.getValue());
		emailBean.setSubject(ApplicationProperties.REGISTERATION_EMAIL_SUBJECT.getValue());
		emailBean.setSenderEmail(senderEmail);
		StringBuffer messageBody = new StringBuffer();
		messageBody.append("<html><body style='font-family:sans-serif;'>")
				.append("<b style='font-size:13px;'>Welcome to MCAN</b>").append("<hr />")
				.append("<div style='margin-left:20px;'>")
				.append("<p style='font-size:12px;'>Hello! Welcome to Medical Career Advising Network.")
				.append("<br />").append("Your account has been created on Medical Career Advising Network System.")
				.append("<br /><br />").append("Account Information").append("<br />").append("Email : " + senderEmail)
				.append("<br />").append("Password : " + password).append("<br /><br />")
				.append("Click link below to activate your account.")
				.append("URL: <a href='" + url + "' >" + url + "</a></p>").append("</div>").append("<hr />")
				.append("</body></html>");
		emailBean.setMessageBody(messageBody.toString());
		return emailBean;
	}

	public static EmailBean getForgotPasswordEmailBean(String senderEmail, String password) {
		EmailBean emailBean = new EmailBean();
		emailBean.setFromEmail(ApplicationProperties.HOST_EMAIL_ADDRESS.getValue());
		emailBean.setSubject(ApplicationProperties.FORGOT_PASSWORD_EMAIL_SUBJECT.getValue());
		emailBean.setSenderEmail(senderEmail);
		StringBuffer messageBody = new StringBuffer();
		messageBody.append("<html><body style='font-family:sans-serif;'>")
				.append("<b style='font-size:13px;'>MCAN! Forgot Password</b>").append("<hr />")
				.append("<div style='margin-left:20px;'>")
				.append("<p style='font-size:12px;'>Hello! Welcome to Medical Career Advising Network.")
				.append("<br />").append("Please find your password details below.").append("<br /><br />")
				.append("Account Information").append("<br />").append("Email : " + senderEmail).append("<br />")
				.append("Password : " + password).append("</p>").append("</div>").append("<hr />")
				.append("</body></html>");
		emailBean.setMessageBody(messageBody.toString());
		return emailBean;
	}

	public String getFromEmail() {
		return fromEmail;
	}

	private void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public String getSenderEmail() {
		return senderEmail;
	}

	private void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}

	public String getSubject() {
		return subject;
	}

	private void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessageBody() {
		return messageBody;
	}

	private void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

}
