package com.adegas.twittertest.service;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.apache.spark.api.java.JavaRDD;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adegas.twittertest.model.PostsByDateHour;
import com.adegas.twittertest.model.Tweet;
import com.adegas.twittertest.repository.PostsByDateHourRepository;
import com.adegas.twittertest.util.TwitterTestUtil;

import scala.Tuple2;

@Component
public class PostsByDateHourService implements Serializable {
	
	/**
	 * Serialization number.
	 */
	private static final long serialVersionUID = 9016320763143764107L;
	
	@Autowired
	private PostsByDateHourRepository repository;

	public void processPostsByDateHour(JavaRDD<Tweet> rdd) {

		rdd
			.mapToPair(tweet -> new Tuple2<>(TwitterTestUtil.convertDateAndTruncate(tweet.getCreatedAt()), 1))
			.reduceByKey((c1, c2) -> c1 + c2)
			.collect()
			.stream()
			.forEach(this::savePostsByDateHour);
	}

	private void savePostsByDateHour(Tuple2<LocalDateTime, Integer> tuple) {
		PostsByDateHour post = new PostsByDateHour(
											TwitterTestUtil.toDate(tuple._1()), 
											tuple._2());
		this.repository.save(post);
		
	}
	
	public void deleteAll() {
		this.repository.deleteAll();
	}

}
