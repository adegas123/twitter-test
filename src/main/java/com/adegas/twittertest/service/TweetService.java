package com.adegas.twittertest.service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.adegas.twittertest.model.Tweet;
import com.adegas.twittertest.repository.TweetRepository;

import twitter4j.Query;
import twitter4j.Query.ResultType;
import twitter4j.QueryResult;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

@Component
@EnableScheduling
public class TweetService {
	
	@Autowired
	private TweetRepository repository;
	
	@Autowired
	private TopUsersByFollowersService topUsersService;
	
	private static final Logger logger = LoggerFactory.getLogger(TweetService.class);
	
	@PostConstruct
	@Scheduled(cron = "10 * * * * *")
	public void getTweets() {
		Set<String> tags = new HashSet<>();
		tags.add("#devops");
		tags.add("#apifirst");
		
		Twitter twitter = TwitterFactory.getSingleton();
		
		tags
			.stream()
			.map(this::createQueryForTag)
			.map(query -> {
				try {
					return twitter.search().search(query);
				} catch (TwitterException e) {
					throw new RuntimeException();
				}
			}).flatMap(this::queryResultToTweet).forEach(this.repository::save);
		
		JavaSparkContext context = new JavaSparkContext();
		JavaRDD<Tweet> rdd = context.parallelize(this.repository.findAll()).cache();
		context.close();
		
		this.topUsersService.processTop5(rdd);
	}
	
	private Query createQueryForTag(String tag) {
		Query query = new Query();
		query.setQuery(tag);
		query.setResultType(ResultType.recent);
		
		return query;
	}
	
	private Stream<Tweet> queryResultToTweet(QueryResult result) {
		return result
					.getTweets()
					.stream()
					.map(query -> new Tweet(
							query.getId(),
							result.getQuery(),
							query.getUser().getId(),
							query.getUser().getName(),
							query.getUser().getFollowersCount(),
							query.getLang(),
							query.getCreatedAt()));
	}

}
