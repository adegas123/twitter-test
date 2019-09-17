package com.adegas.twittertest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.adegas.twittertest.config.AppProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class TwitterTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwitterTestApplication.class, args);
	}
	

}
