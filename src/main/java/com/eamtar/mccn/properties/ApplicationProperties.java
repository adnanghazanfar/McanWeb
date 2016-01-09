package com.eamtar.mccn.properties;

import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * @author Adnan Ghazanfar
 * @date 11 OCT, 2014
 */
public enum ApplicationProperties {

	DIRECTORY_PROFILE_PICTURE("directoryProfilePicture"), SRC_DIRECTORY_PROFILE_PICTURE("srcDirectoryProfilePicture"), FEEDBACK_EMAIL_ADDRESS(
			"mccn.feedbackEmailAddress"), FEEDBACK_EMAIL_SUBJECT("mccn.feedbackEmailSubject"), HOST_EMAIL_ADDRESS(
			"mccn.hostEmailAddress"), REGISTERATION_EMAIL_SUBJECT("mccn.registrationEmailSubject"), FORGOT_PASSWORD_EMAIL_SUBJECT(
			"mccn.forgotPasswordEmailSubject"), USER_MESSAGE_EMAIL_SUBJECT("mccn.userMessageEmailSubject");

	/**
	 * This method is called from PropertiesManager everytime when
	 * PropertiesManager load's properties from file.
	 * 
	 * @param properties
	 *            values
	 * @throws Exception
	 * @throws ResourceNotFoundException
	 */
	public static void reload(Properties properties) throws Exception {

		try {
			synchronized (ApplicationProperties.class) {
				for (ApplicationProperties property : ApplicationProperties.values()) {
					property.value = properties.getProperty(property.key, "");
				}
			}
			logger.info("Application Properties loaded successfully.");
		} catch (Exception e) {
			logger.error("Unable to load Application Properties");
		}
	}

	public static String getProperty(String key) throws Exception {
		String property = "";
		property = PropertiesManager.getProperty(key);
		return property;
	}

	// /////////////////////////
	private static Logger logger = Logger.getLogger(ApplicationProperties.class.getSimpleName());
	// ////////////////////////// Object/Instance data //////////////////
	private String key;
	private String value;

	ApplicationProperties(String key) {
		this.key = key;
		this.value = null;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public int getIntValue() {
		return Integer.parseInt(value);
	}

	public float getFloatValue() {
		return Float.parseFloat(value);
	}

	@Override
	public String toString() {
		return value;
	}

}
