package com.rikkeisoft.vn.dao;

import java.util.List;

import com.rikkeisoft.vn.model.Topic;


public interface TopicDAO {
	
	List<Topic> get();
	
	Topic get(int topicId);
	
	void save(Topic topic);
	
	void delete(int topicId);
}
