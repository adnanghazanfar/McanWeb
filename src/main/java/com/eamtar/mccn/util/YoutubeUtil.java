package com.eamtar.mccn.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.eamtar.mccn.servlets.ResourceInit;

public class YoutubeUtil {
	
	private static final String USER_AGENT = "Mozilla/5.0";
	private static final String PARAM_VIDEO_ID = "<VIDEOID/>";
	private static final String PARAM_VIDEO_URL = "<VIDEOURL/>";
	private static final String THUMBNAIL_IMAGE_URL;
	private static final String VIDEO_INFO_URL ;
	private static final String VIDEO_URL ;
	private static Logger logger = Logger.getLogger(ResourceInit.class
			.getSimpleName());
	
	static{
		THUMBNAIL_IMAGE_URL = "http://img.youtube.com/vi/"+PARAM_VIDEO_ID+"/mqdefault.jpg";
		VIDEO_INFO_URL = "http://www.youtube.com/oembed?url="+PARAM_VIDEO_URL+"&format=json";
		VIDEO_URL = "http://www.youtube.com/embed/"+PARAM_VIDEO_ID+"?html5=1";
	}
	
	public static String getVideoNameFromUrl(String ytUrl) {
	    String vId = null;
	    Pattern pattern = Pattern.compile(".*(?:youtu.be\\/|v\\/|u\\/\\w\\/|embed\\/|watch\\?v=)([^#\\&\\?]*).*");
	    Matcher matcher = pattern.matcher(ytUrl);
	    if (matcher.matches()){
	        vId = matcher.group(1);
	    }
	    return vId;
	}
	
	public static String getThumbnailImageUrl(String videoId) {
		return THUMBNAIL_IMAGE_URL.replace(PARAM_VIDEO_ID, videoId);
	}
	
	public static String getVideoUrl(String videoId) {
		return VIDEO_URL.replace(PARAM_VIDEO_ID, videoId);
	}
	
 
	// HTTP GET request
	public static String sendGet(String videoUrl) throws Exception {
 
		videoUrl = VIDEO_INFO_URL.replace(PARAM_VIDEO_URL, videoUrl);
 
		URL obj = new URL(videoUrl);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		// optional default is GET
		con.setRequestMethod("GET");
 
		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
 
		int responseCode = con.getResponseCode();
		logger.info("\nSending 'GET' request to URL : " + videoUrl);
		logger.info("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
 
		return response.toString();
 
	}

}
