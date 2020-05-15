package com.rikkeisoft.vn.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rikkeisoft.vn.model.Category;


@Repository
public class CategoryDAOImp implements CategoryDAO {

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Category> get() {

		Session currSession = entityManager.unwrap(Session.class);
		Query<Category> query = currSession.createQuery("from Category", Category.class);
		List<Category> list = query.getResultList();
		return list;

	}

	@Override
	public Category get(int id) {
		Session currSession = entityManager.unwrap(Session.class);
		Category cat = currSession.get(Category.class, id);
		return cat;
	}

	@Override
	public void save(Category category) {
		Session currSession = entityManager.unwrap(Session.class);
		currSession.saveOrUpdate(category);
		
	}

	@Override
	public void delete(int id) {
		Session currSession = entityManager.unwrap(Session.class);
		Category cat = currSession.get(Category.class, id);
		currSession.delete(cat);

	}


}
