package com.rikkeisoft.vn.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "topic")
public class Topic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer topicId;

	@Column
	private Integer userId;
	
	@Column
	private String title;
	
	@Column
	private Integer statePostId;
	
	@Column
	private Integer groupId;
	
	@Column
	private Date date;
	
	@Column
	private Integer state;
	
	@Column
	private String Content;
	
	@Column
	private Integer categoryId;

	@Override
	public String toString() {
		return "topic [topicId=	" + topicId + ", userId=" + userId + ", title=" + title + 
				", statePostId=" + statePostId + ", groupId=" + groupId + ", date=" + date + 
				", state=" + state + ", Content=" + Content + ", categoryId="+ categoryId + "]";
	}

	public Integer getTopicId() {
		return topicId;
	}



	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}



	public Integer getUserId() {
		return userId;
	}



	public void setUserId(Integer userId) {
		this.userId = userId;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public Integer getStatePostId() {
		return statePostId;
	}



	public void setStatePostId(Integer statePostId) {
		this.statePostId = statePostId;
	}



	public Integer getGroupId() {
		return groupId;
	}



	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public Integer getState() {
		return state;
	}



	public void setState(Integer state) {
		this.state = state;
	}



	public String getContent() {
		return Content;
	}



	public void setContent(String content) {
		Content = content;
	}



	public Integer getCategoryId() {
		return categoryId;
	}



	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

}
