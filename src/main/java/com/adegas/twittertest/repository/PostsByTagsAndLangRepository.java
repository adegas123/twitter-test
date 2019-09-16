package com.adegas.twittertest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.adegas.twittertest.model.PostsByTagsAndLang;

public interface PostsByTagsAndLangRepository extends MongoRepository<PostsByTagsAndLang, Long>{
	

}
