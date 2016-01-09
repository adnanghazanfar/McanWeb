package com.eamtar.mccn.dao;
import java.util.List;

import com.eamtar.mccn.model.User;
import com.eamtar.mccn.model.Video;

@SuppressWarnings("all")
public interface VideoDao extends GenericDAO<Video, Integer> {
	
	public boolean videoExists(String videoName);
	public List<Video> findViaCriteria(Video searchVideo,Integer limit);

}