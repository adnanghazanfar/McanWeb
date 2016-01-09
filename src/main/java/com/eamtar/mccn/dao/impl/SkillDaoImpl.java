package com.eamtar.mccn.dao.impl;

import org.springframework.stereotype.Repository;

import com.eamtar.mccn.dao.AbstractHibernateDAO;
import com.eamtar.mccn.dao.SkillDao;
import com.eamtar.mccn.model.Skill;

@Repository
public class SkillDaoImpl extends  AbstractHibernateDAO<Skill, Integer> 
implements SkillDao{

	
}