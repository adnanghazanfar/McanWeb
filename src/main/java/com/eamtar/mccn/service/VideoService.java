package com.eamtar.mccn.service;
 
import java.util.List;

import com.eamtar.mccn.model.Video;
 
public interface VideoService{
	
	public boolean videoExists(String videoName);
	public Video save(Video video);
	public List<Video> findViaCriteria(Video searchVideo, Integer limit);
	public String deleteList(List<Video> videos);
}