package com.eamtar.mccn.service.impl;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eamtar.mccn.dao.ManagerDAO;
import com.eamtar.mccn.model.Video;
import com.eamtar.mccn.service.VideoService;

@Service 
public class VideoServiceImpl implements VideoService{
	
	@Autowired
	private ManagerDAO managerDao;
 
	public ManagerDAO getManagerDao() {
		return managerDao;
	}
	
	public void setManagerDao(ManagerDAO managerDao) {
		this.managerDao = managerDao;
	}

	@Override
	public List<Video> findViaCriteria(Video searchVideo, Integer limit){
 		return managerDao.getVideoDao().findViaCriteria(searchVideo,limit);
	}

	@Override
	public Video save(Video video) {
		return managerDao.getVideoDao().save(video);
	}

	@Override
	public String deleteList(List<Video> videos) {
		return managerDao.getVideoDao().deleteList(videos);
	}

	@Override
	public boolean videoExists(String videoName) {
		return managerDao.getVideoDao().videoExists(videoName);
	}

}