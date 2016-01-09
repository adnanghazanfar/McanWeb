package com.eamtar.mccn.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.eamtar.mccn.util.LoggerManager;

/**
 * 
 * @author Adnan Ghazanfar
 * @email adnan.ghazanfar@yahoo.com
 * @since 24 SEP, 2014
 */
public class EmailService {

	private JavaMailSender mailSender;
	static LoggerManager logger = new LoggerManager();
	private boolean emailNotifications;

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void sendMail(final EmailBean emailBean) {
		if (emailNotifications) {
			Thread emailThread = new Thread() {
				public void run() {
					try {
						if (emailBean != null) {
							MimeMessage message = mailSender.createMimeMessage();
							MimeMessageHelper helper = new MimeMessageHelper(message, true);
							helper.setFrom(emailBean.getFromEmail());
							helper.setTo(emailBean.getSenderEmail());
							helper.setSubject(emailBean.getSubject());
							helper.setText(emailBean.getMessageBody(), true);
							mailSender.send(message);
						}

					} catch (MessagingException error) {
						logger.error(error.getMessage());
						error.printStackTrace();
					}
				}
			};

			emailThread.start();
		}
	}

	public boolean isEmailNotifications() {
		return emailNotifications;
	}

	public void setEmailNotifications(boolean emailNotifications) {
		this.emailNotifications = emailNotifications;
	}

}
