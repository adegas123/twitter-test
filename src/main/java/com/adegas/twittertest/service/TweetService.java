package com.adegas.twittertest.service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.adegas.twittertest.model.Tweet;
import com.adegas.twittertest.repository.TweetRepository;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

@Service
@EnableScheduling
public class TweetService {
	
	@Autowired
	private TweetRepository repository;
	
	@Autowired
	private TopUsersByFollowersService topUsersService;
	
	@Autowired
	private PostsByDateHourService postsByDateService;
	
	@Autowired
	private PostsByTagsAndLangService postsByTagAndLangService;
	
	@Autowired
    private JavaSparkContext sc;
	
	private static final Logger logger = LoggerFactory.getLogger(TweetService.class);
	
	@Scheduled(cron = "0/20 * * * * *")
	public void getTweets() {
		Set<String> tags = new HashSet<>();
		tags.add("#devops");
		tags.add("#apifirst");

		logger.info("Initializing GetTweets Method!");
		logger.info("Cleaning all previous data...");
		this.clearAllPreviousData();
		
		Twitter twitter = TwitterFactory.getSingleton();
		
		tags
			.stream()
			.map(this::createQueryForTag)
			.map(query -> {
				try {
					logger.info("Searching for tag: "+query.getQuery());
					return twitter.search().search(query);
				} catch (TwitterException e) {
					throw new RuntimeException();
				}
			}).flatMap(this::queryResultToTweet).forEach(tweet -> {
				this.repository.save(tweet);
				logger.debug("Saving tweet: " + tweet.getId());
			});
		
		logger.info("Uploading data into RDD cache.");
		JavaRDD<Tweet> rdd = sc.parallelize(this.repository.findAll()).cache();
		
		logger.info("Calling process Top 5 service...");
		this.topUsersService.processTop5(rdd);
		this.topUsersService.printAll();
		
		logger.info("Calling process Posts By Date and Hour...");
		this.postsByDateService.processPostsByDateHour(rdd);
		
		logger.info("Calling process Posts By Tag and Lang...");
		this.postsByTagAndLangService.processPostsByTagAndLang(rdd);
		this.postsByTagAndLangService.printAll();
	}
	
	private Query createQueryForTag(String tag) {
		Query query = new Query();
		query.setQuery(tag);
		
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
	
	private void clearAllPreviousData() {
		this.repository.deleteAll();
		this.topUsersService.deleteAll();
		this.postsByDateService.deleteAll();
		this.postsByTagAndLangService.deleteAll();
	}

}
