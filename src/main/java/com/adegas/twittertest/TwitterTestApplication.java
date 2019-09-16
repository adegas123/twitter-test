package com.adegas.twittertest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.adegas.twittertest.model.Tweet;
import com.adegas.twittertest.service.TweetService;

@SpringBootApplication
public class TwitterTestApplication {

	public static void main(String[] args) {
		ApplicationContext appContext = SpringApplication.run(TwitterTestApplication.class, args);
		
		run(appContext);
		
	}
	
	
	private static void run(ApplicationContext appContext) {
		TweetService tweetService = appContext.getBean(TweetService.class);
		tweetService.getTweets();
	}
	

}
