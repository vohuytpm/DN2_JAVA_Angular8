package com.rikkeisoft.vn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer categoryId;

	@Column
	private String title;

	@Column
	private String description;

	@Override
	public String toString() {
		return "Category [categoryId=	" + categoryId + ", title=" + title + ", description=" + description + "]";
	}

	public Integer getId() {
		return categoryId;
	}

	public void setId(Integer id) {
		this.categoryId = id;
	}

	public String getName() {
		return title;
	}

	public void setName(String name) {
		this.title = name;
	}

	public String getDepartment() {
		return description;
	}

	public void setDepartment(String department) {
		this.description = department;
	}

}
