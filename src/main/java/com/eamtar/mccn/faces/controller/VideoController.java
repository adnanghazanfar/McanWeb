package com.eamtar.mccn.faces.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.eamtar.mccn.faces.managedbean.VideoBean;
import com.eamtar.mccn.model.Video;
import com.eamtar.mccn.service.ManagerService;
import com.eamtar.mccn.service.ServiceManagerFactory;
import com.eamtar.mccn.service.VideoService;
import com.eamtar.mccn.util.JsonUtil;
import com.eamtar.mccn.util.YoutubeUtil;

@Component(value = "videoController")
@Scope("request")
public class VideoController extends AbstractController {

	private final String PARAM_VIDEO_INFO_TITLE = "title";

	private final String NO_REDIRECTION = null;
	private final String SUCCESS_MESSAGE_ADD = "Video added successfully.";
	private final String ERROR_MESSAGE_ADD = "Error! while saving video.";
	private final String ERROR_MESSAGE_VALIDATION = "Error! Already added please search in videos.";
	private final String SUCCESS_MESSAGE_DELETE = "Selected videos deleted successfully.";
	private final String ERROR_MESSAGE_DELETE = "Error! please select atleast one video video.";
	private final String ERROR_MESSAGE_EXCEPTION = "An Exception has occured please see logs.";
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm");
	
	@Autowired
	private VideoBean videoBean;
	@Autowired
	private ManagerService managerService;

	public void initializeValues() {
		try {
			if (!videoBean.isIntialized()) {

				// SEARCH DTO INJECTION
				VideoService videoService = managerService.getVideoService();
				Video videoDTO = new Video();
				List<Video> videoList = videoService.findViaCriteria(videoDTO, null);
				if (videoList != null && videoList.size() > 3) {
					int listSize = videoList.size();
					videoBean.setTop3VideoList(videoList.subList(listSize-3, listSize));
					videoDTO = videoBean.getTop3VideoList().get(0);
					videoBean.setSelectedVideo(videoDTO);
					videoBean.setVideoList(videoList);
				} else {
					videoBean.setTop3VideoList(videoList);
				}
				videoBean.setIntialized(Boolean.TRUE);
			}

		} catch (Exception exception) {
			logger.error(exception.getMessage());
		}
	}

	public void searchVideos() {
		String message = null;
		try {
			String searchText = videoBean.getSearchText();
			Video searchVideoDTO = new Video();
			searchVideoDTO.setTitle(searchText);
			VideoService videoService = managerService.getVideoService();

			List<Video> videoList = videoService.findViaCriteria(searchVideoDTO, null);
			videoBean.setVideoList(videoList);

		} catch (Exception exception) {
			message = ERROR_MESSAGE_EXCEPTION;
			logger.error(exception.getMessage());
		}

		addMessageToFacesContext(message);
	}

	public void addVideo() {
		String message = null;
		try {

			VideoService videoService = managerService.getVideoService();

			String url = videoBean.getVideoUrl();
			String videoName = YoutubeUtil.getVideoNameFromUrl(url);
			boolean flag = videoService.videoExists(videoName);
			if (!flag) {
				String jsonStr = YoutubeUtil.sendGet(url);
				if (videoName != null && jsonStr != null && jsonStr.length() > 0) {
					JsonUtil videoUtil = new JsonUtil(jsonStr);
					String title = videoUtil.getValue(PARAM_VIDEO_INFO_TITLE);
					String src = YoutubeUtil.getVideoUrl(videoName);
					String thumbnailImage = YoutubeUtil.getThumbnailImageUrl(videoName);
					logger.info("TITLE :" + title);
					logger.info("SRC :" + src);
					logger.info("VIDEO ID :" + videoName);
					logger.info("THUMBNAIL :" + thumbnailImage);
					Video video = new Video();
					video.setVideoName(videoName);
					video.setTitle(title);
					video.setUrl(src);
					video.setThumbnail(thumbnailImage);
					video = videoService.save(video);
					if (video != null && video.getVideoId() != null) {
						if (videoBean.getVideoList() != null)
							videoBean.getVideoList().add(0, video);
						videoBean.setVideoUrl(null);
						message = SUCCESS_MESSAGE_ADD;
					} else {
						message = ERROR_MESSAGE_ADD;
					}
				}
			} else {
				message = ERROR_MESSAGE_VALIDATION;
			}

		} catch (Exception exception) {
			message = ERROR_MESSAGE_EXCEPTION;
			logger.error(exception.getMessage());
		}

		addMessageToFacesContext(message);
	}

	public String deleteVideos() {
		String message = null;
		try {
			List<Video> videoList = videoBean.getVideoList();
			List<Video> deleteList = null;
			if (videoList != null) {
				deleteList = new ArrayList<Video>();
				for (Video video : videoList) {
					if (video.getSelected()) {
						logger.info("DELETE VIDE NAME :" + video.getVideoName());
						deleteList.add(video);
					}
				}
			}

			if (deleteList != null && deleteList.size() > 0) {
				logger.info("DELETE VIDE LIST SIZE :" + deleteList.size());
				VideoService videoService = managerService.getVideoService();
				videoService.deleteList(deleteList);
				searchVideos();
				message = SUCCESS_MESSAGE_DELETE;
			} else {
				message = ERROR_MESSAGE_DELETE;
			}

		} catch (Exception exception) {
			message = ERROR_MESSAGE_EXCEPTION;
			logger.error(exception.getMessage());
		}

		addMessageToFacesContext(message);
		return NO_REDIRECTION;
	}

	public void loadSelectedVideo(Video selectedVideo) {

		if (selectedVideo != null) {
			videoBean.setSelectedVideo(selectedVideo);
		}

	}

	public String convertToDate(Date date) {
		if (date == null)
			return "";
		return dateFormat.format(date);
	}

}
