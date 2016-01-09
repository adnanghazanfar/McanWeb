package com.eamtar.mccn.faces.managedbean;

import java.util.List;

import org.springframework.stereotype.Component;

import com.eamtar.mccn.faces.controller.AbstractController;
import com.eamtar.mccn.faces.custom.scope.SpringViewScoped;
import com.eamtar.mccn.model.Video;


@Component(value = "videoBean")
@SpringViewScoped
public class VideoBean extends AbstractController {

	private boolean intialized = false;
	private String searchText = null;
	private String videoUrl = null;
	private Video selectedVideo = null;
	private List<Video> videoList = null;
	private List<Video> top3VideoList = null;

	public boolean isIntialized() {
		return intialized;
	}

	public void setIntialized(boolean intialized) {
		this.intialized = intialized;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public Video getSelectedVideo() {
		return selectedVideo;
	}

	public void setSelectedVideo(Video selectedVideo) {
		this.selectedVideo = selectedVideo;
	}

	public List<Video> getVideoList() {
		return videoList;
	}

	public void setVideoList(List<Video> videoList) {
		this.videoList = videoList;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public List<Video> getTop3VideoList() {
		return top3VideoList;
	}

	public void setTop3VideoList(List<Video> top3VideoList) {
		this.top3VideoList = top3VideoList;
	}

}
