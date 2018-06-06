package com.ego.dao.impl;

import org.springframework.data.mongodb.core.MongoTemplate;

public class LiuYanDao {
	private MongoTemplate mongoTemplate;
	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
}
