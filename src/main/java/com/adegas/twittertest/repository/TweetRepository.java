package com.adegas.twittertest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adegas.twittertest.model.Tweet;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long>{
 
}