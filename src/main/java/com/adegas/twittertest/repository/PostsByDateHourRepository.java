package com.adegas.twittertest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.adegas.twittertest.model.PostsByDateHour;

public interface PostsByDateHourRepository extends MongoRepository<PostsByDateHour, Long>{
	

}
