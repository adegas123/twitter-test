package com.adegas.twittertest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;

import com.adegas.twittertest.config.AppProperties;
import com.adegas.twittertest.service.TweetService;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class TwitterTestApplication {
	
	@Autowired
	private static TweetService service;

	public static void main(String[] args) {
		ApplicationContext app = SpringApplication.run(TwitterTestApplication.class, args);
		
		service = app.getBean(TweetService.class);
		service.getTweets();
	}

}
