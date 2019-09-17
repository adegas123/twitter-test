package com.adegas.twittertest.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.adegas.twittertest.model.User;

public interface UserRepository extends MongoRepository<User, Long> {
	
	Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

}
