package com.rikkeisoft.vn.dao;

import java.util.List;

import com.rikkeisoft.vn.model.Category;


public interface CategoryDAO {
	
	List<Category> get();
	
	Category get(int id);
	
	void save(Category category);
	
	void delete(int id);
	

}
