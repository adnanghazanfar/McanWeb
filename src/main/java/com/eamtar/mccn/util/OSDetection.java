package com.eamtar.mccn.util;

import org.apache.log4j.Logger;

public class OSDetection {
	
	 
	private static String OS = System.getProperty("os.name").toLowerCase();
	private static Logger logger = Logger.getLogger(SessionListener.class
			.getSimpleName());
	
	static{
		logger.info("SERVER IS RUNNING ON :::: "+OS);
	}
 
	public static boolean isWindows() {
 
		return (OS.indexOf("win") >= 0);
 
	}
 
	public static boolean isMac() {
 
		return (OS.indexOf("mac") >= 0);
 
	}
 
	public static boolean isUnix() {
 
		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
 
	}
 
	public static boolean isSolaris() {
 
		return (OS.indexOf("sunos") >= 0);
 
	}
 
}