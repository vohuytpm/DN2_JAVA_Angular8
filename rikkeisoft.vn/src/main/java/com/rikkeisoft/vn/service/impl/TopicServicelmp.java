package com.rikkeisoft.vn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.stereotype.Service;

import com.rikkeisoft.vn.dao.TopicDAO;
import com.rikkeisoft.vn.model.Topic;

@Service
public class TopicServicelmp implements TopicService {
	//
	@Autowired
	private TopicDAO topicDAO;

	@Override
	public List<Topic> get() {
		return topicDAO.get();
	}

	@Override
	public Topic get(int id) {
		return topicDAO.get(id);
	}

	@Override
	public void save(Topic cat) {
		topicDAO.save(cat);
		
	}

	@Override
	public void delete(int id) {
		topicDAO.delete(id);
		
	}


}
package com.rikkeisoft.vn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rikkeisoft.vn.dao.TopicDAO;
import com.rikkeisoft.vn.model.Topic;
import com.rikkeisoft.vn.service.TopicService;

@Service
public class TopicServicelmp implements TopicService {
	//
	@Autowired
	private TopicDAO topicDAO;

	@Override
	public List<Topic> get() {
		return topicDAO.get();
	}

	@Override
	public Topic get(int id) {
		return topicDAO.get(id);
	}

	@Override
	public void save(Topic cat) {
		topicDAO.save(cat);

	}

	@Override
	public void delete(int id) {
		topicDAO.delete(id);

	}

}
