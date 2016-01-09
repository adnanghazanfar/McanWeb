package com.eamtar.mccn.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.eamtar.mccn.dao.AbstractHibernateDAO;
import com.eamtar.mccn.dao.VideoDao;
import com.eamtar.mccn.model.Video;

@Repository
public class VideoDaoImpl extends AbstractHibernateDAO<Video, Integer> implements VideoDao {

	@SuppressWarnings("unchecked")
	public List<Video> findViaCriteria(Video searchVideo, Integer limit) {
		Criteria criteria = getSession().createCriteria(Video.class);

		if (searchVideo.getTitle() != null && !searchVideo.getTitle().equals(""))
			criteria.add(Restrictions.ilike("title", "%" + searchVideo.getTitle() + "%"));

		criteria.addOrder(Order.desc("createdDate"));
		
		if (limit != null)
			criteria.setMaxResults(limit);
		
		List<Video> videos = criteria.list();
		return videos;
	}

	@Override
	public boolean videoExists(String videoName) {
		Query query = getSession().createQuery("from Video video where video.videoName=:videoName");
		query.setString("videoName", videoName);
		Video video = (Video)query.uniqueResult();
		if(video != null)
			return true;
		return false;
	}

}