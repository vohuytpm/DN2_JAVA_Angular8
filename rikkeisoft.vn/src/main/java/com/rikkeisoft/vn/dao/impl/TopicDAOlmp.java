package com.rikkeisoft.vn.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rikkeisoft.vn.dao.TopicDAO;
import com.rikkeisoft.vn.model.Topic;

@Repository(value="topicDAO")
public class TopicDAOlmp implements TopicDAO{
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Topic> get() {
		Session currSession = entityManager.unwrap(Session.class);
		Query<Topic> query = currSession.createQuery("from Topic", Topic.class);
		List<Topic> list = query.getResultList();
		return list;
	}

	@Override
	public Topic get(int topicId) {
		Session currSession = entityManager.unwrap(Session.class);
		Topic cat = currSession.get(Topic.class, topicId);
		return cat;
	}

	@Override
	public void save(Topic topic) {
		Session currSession = entityManager.unwrap(Session.class);
		currSession.saveOrUpdate(topic);
	}

	@Override
	public void delete(int topicId) {
		Session currSession = entityManager.unwrap(Session.class);
		Topic cat = currSession.get(Topic.class, topicId);
		currSession.delete(cat);
		
	}

}
