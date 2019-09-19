package com.adegas.twittertest.service;

import java.io.Serializable;

import org.apache.spark.api.java.JavaRDD;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adegas.twittertest.model.PostsByTagsAndLang;
import com.adegas.twittertest.model.Tweet;
import com.adegas.twittertest.repository.PostsByTagsAndLangRepository;

import scala.Tuple2;

@Component
public class PostsByTagsAndLangService implements Serializable {
	
	/**
	 * Serialization number.
	 */
	private static final long serialVersionUID = -1678333679158809190L;

	@Autowired
	private PostsByTagsAndLangRepository repository;
	
	private static final Logger logger = LoggerFactory.getLogger(PostsByDateHourService.class);

	public void processPostsByTagAndLang(JavaRDD<Tweet> rdd) {
		// pegar uma lista das tags - ok
		// percorrer os tweets e filtrar por cada lang da lista
		// reduzir incrementando o count
		rdd
			.mapToPair(tw -> new Tuple2<>(tw.getTag(), 1))
			.reduceByKey((a, b) -> a + b)
			.collect()
			.forEach(tag -> {
				rdd
					.filter(tw -> tw.getTag().equals(tag._1()))
					.mapToPair(tw -> new Tuple2<>(tw.getLang(), 1))
					.reduceByKey((count1, count2) -> count1 + count2)
					.collect()
					.stream()
					.forEach(tuple -> savePostsByTagsAndLang(tuple, tag._1()));
			});
	}
	
	public void deleteAll() {
		this.repository.deleteAll();
	}
	
	public void printAll() {
		logger.info(this.repository.findAll().toString());
	}

	private void savePostsByTagsAndLang(Tuple2<String, Integer> tuple, String tag) {
		PostsByTagsAndLang post = new PostsByTagsAndLang(
														tag,
														tuple._1(),
														tuple._2());
		this.repository.save(post);
		
	}

}
