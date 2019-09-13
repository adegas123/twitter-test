package com.adegas.twittertest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adegas.twittertest.model.PostsByDateHour;

@Repository
public interface PostsByDateHourRepository extends JpaRepository<PostsByDateHour, Long>{
	

}
