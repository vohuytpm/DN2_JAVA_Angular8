package com.rikkeisoft.vn.service;

import java.util.List;

import com.rikkeisoft.vn.model.TopicCategory;


public interface CategoryService {
	
	List<TopicCategory> get();
	
	TopicCategory get(int id);
	
	void save(TopicCategory cat);
	
	void delete(int id);

}
