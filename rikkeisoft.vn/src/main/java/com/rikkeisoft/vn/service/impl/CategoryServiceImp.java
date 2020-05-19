package com.rikkeisoft.vn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rikkeisoft.vn.dao.CategoryDAO;
import com.rikkeisoft.vn.model.TopicCategory;
import com.rikkeisoft.vn.service.CategoryService;

@Service
public class CategoryServiceImp implements CategoryService {
	
	@Autowired
	private CategoryDAO categoryDAO;

	@Transactional
	@Override
	public List<TopicCategory> get() {
		// TODO Auto-generated method stub
		return categoryDAO.get();
	}

	@Transactional
	@Override
	public TopicCategory get(int id) {
		// TODO Auto-generated method stub
		return categoryDAO.get(id);
	}

	@Transactional
	@Override
	public void save(TopicCategory cat) {
		categoryDAO.save(cat);
	}

	@Transactional
	@Override
	public void delete(int id) {
		categoryDAO.delete(id);
		
	}


}
