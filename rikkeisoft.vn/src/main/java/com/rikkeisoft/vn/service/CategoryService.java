package com.rikkeisoft.vn.service;

import java.util.List;

import com.rikkeisoft.vn.model.Category;


public interface CategoryService {
	
	List<Category> get();
	
	Category get(int id);
	
	void save(Category cat);
	
	void delete(int id);

}
