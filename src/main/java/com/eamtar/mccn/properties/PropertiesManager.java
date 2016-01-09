package com.eamtar.mccn.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertiesManager {

	public static final String DEFAULT_FILE_PATH = PropertiesManager.class.getClassLoader()
			.getResource("resources/application.properties").getFile();
	private static Logger logger = Logger.getLogger(PropertiesManager.class.getSimpleName());
	private static XProperties properties = null;
	private static File propertyFile = null;
	private static Long lastLoaded = (long) -1;

	// /////////////// Property Getter & Setter ///////////////////////

	public static String getProperty(String key) throws Exception {

		reloadIfFileUpdated();
		return properties.getProperty(key);

	}

	public static void setProperty(String key, String value) throws Exception {

		reloadIfFileUpdated();
		properties.setProperty(key, value);

	}

	public static Properties getProperties() throws Exception {

		reloadIfFileUpdated();
		return properties;

	}

	private static void reloadIfFileUpdated() throws Exception {

		if ((propertyFile != null) && (propertyFile.exists())) {
			if (propertyFile.lastModified() != lastLoaded) {
				synchronized (PropertiesManager.lastLoaded) {
					if (propertyFile.lastModified() != lastLoaded) {
						load(propertyFile);
					}
				}
			}
		} else {
			throw new Exception("APPLICATION_PROPERTIES_NOT_LOADED");
		}

	}

	private static void load(File file) throws Exception {

		InputStream is = null;
		try {
			is = new FileInputStream(propertyFile);
			properties = new XProperties();
			properties.load(is);
			lastLoaded = propertyFile.lastModified();
			logger.info("Properties loaded successfully." + properties.keySet());
			ApplicationProperties.reload(properties);

		} finally {

			if (is != null) {
				is.close();
			}

		}

	}

	public static Properties loadProperties(String propertyFilePath)

	throws Exception {

		logger.info("propertyFilePath : " + propertyFilePath);
		propertyFile = new File(propertyFilePath);
		if (propertyFile.exists()) {
			load(propertyFile);
		} else {
			logger.error("Property file does not exists at application ear root.");
			throw new Exception("APPLICATION_PROPERTY_FILE_NOT_EXISTS");

		}

		return properties;

	}

	public static class XProperties extends Properties {

		private static final long serialVersionUID = 1L;
		private static final String START_CONST = "{";
		private static final String END_CONST = "}";
		private static final int MAX_SUBST_DEPTH = 5;

		public XProperties() {
			super();
		}

		public String getProperty(String key) {

			return getProperty(key, 0);

		}

		private String getProperty(String key, int level) {
			String value = super.getProperty(key);
			if (value != null) {
				int beginIndex = 0;
				int startName = value.indexOf(START_CONST, beginIndex);
				while (startName != -1) {
					if (level + 1 > MAX_SUBST_DEPTH) {
						return value;
					}

					int endName = value.indexOf(END_CONST, startName);
					if (endName == -1) {
						return value;
					}

					String constName = value.substring(startName + 1, endName);
					String constValue = getProperty(constName, level + 1);
					if (constValue == null) {
						return value;
					}

					String newValue = (startName > 0) ? value.substring(0, startName) : "";
					newValue += constValue;
					beginIndex = newValue.length();
					newValue += value.substring(endName + 1);

					value = newValue;
					startName = value.indexOf(START_CONST, beginIndex);

				}

			}
			return value;

		}

	}

}
