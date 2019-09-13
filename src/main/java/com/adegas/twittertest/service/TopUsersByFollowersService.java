package com.adegas.twittertest.service;

import org.apache.spark.api.java.JavaRDD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adegas.twittertest.model.TopUsersByFollowers;
import com.adegas.twittertest.model.Tweet;
import com.adegas.twittertest.repository.TopUsersByFollowersRepository;

import scala.Tuple2;

@Component
public class TopUsersByFollowersService {
	
	@Autowired
	private TopUsersByFollowersRepository repository;
	
	public void processTop5(JavaRDD<Tweet> rdd) {

		rdd
				.mapToPair(tweet -> new Tuple2<>(tweet.getFollowers(), tweet))
				.sortByKey(false)
				.take(5)
				.stream()
				.map(Tuple2::_2)
				.forEach(this::saveTopUser);
	}

	private void saveTopUser(Tweet tweet) {
		TopUsersByFollowers topUser = new TopUsersByFollowers(
											tweet.getUserId(), 
											tweet.getUserName(), 
											tweet.getFollowers());
		this.repository.save(topUser);
		
	}

}
