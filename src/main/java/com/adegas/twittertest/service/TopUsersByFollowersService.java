package com.adegas.twittertest.service;

import java.io.Serializable;

import org.apache.spark.api.java.JavaRDD;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adegas.twittertest.model.TopUsersByFollowers;
import com.adegas.twittertest.model.Tweet;
import com.adegas.twittertest.repository.TopUsersByFollowersRepository;

import scala.Tuple2;

@Component
public class TopUsersByFollowersService implements Serializable {
	
	/**
	 * Serialization number.
	 */
	private static final long serialVersionUID = 8692721617266883011L;
	
	private static final Logger logger = LoggerFactory.getLogger(TopUsersByFollowers.class);

	@Autowired
	private TopUsersByFollowersRepository repository;
		
	public void processTop5(JavaRDD<Tweet> rdd) {

		JavaRDD<Tweet> localrdd = rdd;
		localrdd
			.mapToPair(tweet -> new Tuple2<>(tweet.getFollowers(), tweet))
			.sortByKey(false)
			.take(5)
			.stream()
			.map(Tuple2::_2)
			.forEach(this::saveTopUser);
	}
	
	public void printAll() {
		logger.info(repository.findAll().toString());
	}
	
	public void deleteAll() {
		repository.deleteAll();
	}

	private void saveTopUser(Tweet tweet) {
		TopUsersByFollowers topUser = new TopUsersByFollowers(
											tweet.getUserId(), 
											tweet.getUserName(), 
											tweet.getFollowers(),
											tweet.getUserId());
		repository.save(topUser);
		
	}

}
