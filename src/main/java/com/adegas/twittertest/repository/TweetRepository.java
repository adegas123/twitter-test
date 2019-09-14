package com.adegas.twittertest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.adegas.twittertest.model.Tweet;


public interface TweetRepository extends MongoRepository<Tweet, Long>{
 
}
