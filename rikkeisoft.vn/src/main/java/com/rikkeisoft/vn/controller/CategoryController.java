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

import com.rikkeisoft.vn.model.TopicCategory;
import com.rikkeisoft.vn.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/categories")
	public List<TopicCategory> get() {
		return categoryService.get();
	}

	@PostMapping("/category")
	public TopicCategory save(@RequestBody TopicCategory category) {
		categoryService.save(category);
		return category;
	}

	@GetMapping("/category/{id}")
	public TopicCategory get(@PathVariable int id) {
		return categoryService.get(id);
	}

	@DeleteMapping("/category/{id}")
	public String delete(@PathVariable int id) {

		categoryService.delete(id);

		return "Category removed with id " + id;

	}

	@PutMapping("/category")
	public TopicCategory update(@RequestBody TopicCategory category) {
		categoryService.save(category);
		return category;
	}

}
