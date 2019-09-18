package com.adegas.twittertest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adegas.twittertest.repository.PostsByDateHourRepository;
import com.adegas.twittertest.repository.PostsByTagsAndLangRepository;
import com.adegas.twittertest.repository.TopUsersByFollowersRepository;

@RestController
public class WebController {

    @Autowired
    private TopUsersByFollowersRepository topUsersRepository;
    
    @Autowired
    private PostsByDateHourRepository postsByTimeRepository;
    
    @Autowired
    private PostsByTagsAndLangRepository postsByTagLangRepository;

    @GetMapping("/api/top-users")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getTopUsers() {
        return ResponseEntity.ok(topUsersRepository.findAll());
    }

    @GetMapping("/api/posts-by-time")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getPostsByTime() {
        return ResponseEntity.ok(postsByTimeRepository.findAll());
    }

    @GetMapping("/api/posts-by-tag-lang")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getPostsByTagLang() {
        return ResponseEntity.ok(postsByTagLangRepository.findAll());
    }
    
}