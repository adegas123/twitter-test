package com.adegas.twittertest.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.apache.spark.api.java.JavaRDD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adegas.twittertest.model.PostsByDateHour;
import com.adegas.twittertest.model.Tweet;
import com.adegas.twittertest.repository.PostsByDateHourRepository;

import scala.Tuple2;

@Component
public class PostsByDateHourService {
	
	@Autowired
	private PostsByDateHourRepository repository;

	public void processTop5(JavaRDD<Tweet> rdd) {

		rdd
				.mapToPair(tweet -> new Tuple2<>(this.convertDateAndTruncate(tweet.getCreatedAt()), 1))
				.reduceByKey((c1, c2) -> c1 + c2)
				.collect()
				.stream()
				.forEach(this::savePostsByDateHour);
	}

	private void savePostsByDateHour(Tuple2<LocalDateTime, Integer> tuple) {
		PostsByDateHour post = new PostsByDateHour(
											toDate(tuple._1()), 
											tuple._2());
		this.repository.save(post);
		
	}

	private Date toDate(LocalDateTime dateTime) {
		return Date.from(dateTime.atZone(ZoneOffset.systemDefault()).toInstant());
	}

	private LocalDateTime toLocalDateTime(Date date) {
		return LocalDateTime.ofInstant(date.toInstant(), ZoneOffset.systemDefault());
	}

	private LocalDateTime convertDateAndTruncate(Date date) {
		LocalDateTime dateTime = toLocalDateTime(date);

		return dateTime.truncatedTo(ChronoUnit.HOURS);
	}

}
