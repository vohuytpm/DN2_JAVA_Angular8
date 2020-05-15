package com.rikkeisoft.vn.dao;

import java.util.List;

import com.rikkeisoft.vn.model.TopicCategory;


public interface CategoryDAO {
	
	List<TopicCategory> get();
	
	TopicCategory get(int id);
	
	void save(TopicCategory category);
	
	void delete(int id);
	

}
