package com.eamtar.mccn.util;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.aspectj.lang.JoinPoint;

public class LoggerManager {
	
	private static final Logger logger = Logger
			.getLogger(LoggerManager.class);
	{
		PropertyConfigurator.configure(this.getClass().getClassLoader().getResource(("resources/log4j.properties")));
	}
	
	public void writeLog(JoinPoint point, Exception exception) {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss");
		String dateStr = dateFormat.format(date);
		logger.error("\n\n\n"+dateStr+" ***** Eamtar Logger Start *****");
		logger.error(point.getSignature() + " called...");
		logger.error(exception.toString());
		logger.error(Arrays.toString(exception.getStackTrace()));
		logger.error(dateStr+" ***** eamtar Logger End *****"+"\n\n\n");
	}
	
	public void before(JoinPoint point) {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss");
		String dateStr = dateFormat.format(date);
		logger.info("\n\n\n"+dateStr+" ***** Eamtar Logger Start *****");
		logger.info(point.getSignature() + " called...");
	}
	
	public void after(JoinPoint point) {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss");
		String dateStr = dateFormat.format(date);
		logger.info("\n"+dateStr+" ***** Eamtar Logger End *****");
		logger.info(point.getSignature() + " called...");
	}
	
	public void error(String error) {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss");
		String dateStr = dateFormat.format(date);
		logger.error(error);
		logger.error("\n\n\n"+dateStr+" ***** Logger Controller Layer *****");
	}
	
	public void info(String info) {
		logger.info(info);
	}

}
