package com.adegas.twittertest.service;

import java.io.Serializable;

import org.apache.spark.api.java.JavaRDD;
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

	public void processPostsByHour(JavaRDD<Tweet> rdd) {

		rdd
			.mapToPair(tweet -> new Tuple2<>(TwitterTestUtil.convertDateAndReturnHour(tweet.getCreatedAt()), 1))
			.reduceByKey((c1, c2) -> c1 + c2)
			.sortByKey()
			.collect()
			.stream()
			.forEach(this::savePostsByDateHour);
	}

	private void savePostsByDateHour(Tuple2<String, Integer> tuple) {
		PostsByDateHour post = new PostsByDateHour(
											tuple._1(), 
											tuple._2());
		this.repository.save(post);
		
	}
	
	public void deleteAll() {
		this.repository.deleteAll();
	}

}
