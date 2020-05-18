package com.rikkeisoft.vn.service;

import java.util.List;

import com.rikkeisoft.vn.model.Topic;

public interface TopicService {

	List<Topic> get();
	
	Topic get(int id);
	
	void save(Topic cat);
	
	void delete(int id);

}
