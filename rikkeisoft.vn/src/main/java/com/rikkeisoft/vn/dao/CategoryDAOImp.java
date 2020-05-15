package com.rikkeisoft.vn.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rikkeisoft.vn.model.TopicCategory;


@Repository
public class CategoryDAOImp implements CategoryDAO {

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<TopicCategory> get() {

		Session currSession = entityManager.unwrap(Session.class);
		Query<TopicCategory> query = currSession.createQuery("from TopicCategory", TopicCategory.class);
		List<TopicCategory> list = query.getResultList();
		return list;

	}

	@Override
	public TopicCategory get(int id) {
		Session currSession = entityManager.unwrap(Session.class);
		TopicCategory cat = currSession.get(TopicCategory.class, id);
		return cat;
	}

	@Override
	public void save(TopicCategory category) {
		Session currSession = entityManager.unwrap(Session.class);
		currSession.saveOrUpdate(category);
		
	}

	@Override
	public void delete(int id) {
		Session currSession = entityManager.unwrap(Session.class);
		TopicCategory cat = currSession.get(TopicCategory.class, id);
		currSession.delete(cat);

	}


}
