package com.rikkeisoft.vn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rikkeisoft.vn.model.Topic;
import com.rikkeisoft.vn.service.TopicService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TopicController {

	@Autowired
	private TopicService topicService;

	@GetMapping("/topic")
	public List<Topic> get() {
		return topicService.get();
	}

	@PostMapping("/topic")
	public Topic save(@RequestBody Topic topic) {
		topicService.save(topic);
		return topic;
	}

	@GetMapping("/topic/{topicid}")
	public Topic get(@PathVariable int id) {
		return topicService.get(id);
	}

	@DeleteMapping("/topic/{topicid}")
	public String delete(@PathVariable int id) {

		topicService.delete(id);

		return "Topic removed with id " + id;

	}

	@PutMapping("/topic")
	public Topic update(@RequestBody Topic topic) {
		topicService.save(topic);
		return topic;
	}

}
