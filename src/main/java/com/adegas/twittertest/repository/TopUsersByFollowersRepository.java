package com.adegas.twittertest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.adegas.twittertest.model.TopUsersByFollowers;

public interface TopUsersByFollowersRepository extends MongoRepository<TopUsersByFollowers, Long>{
	

}
