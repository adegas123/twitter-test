package com.adegas.twittertest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adegas.twittertest.model.TopUsersByFollowers;

@Repository
public interface TopUsersByFollowersRepository extends JpaRepository<TopUsersByFollowers, Long>{
	

}
