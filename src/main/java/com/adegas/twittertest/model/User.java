package com.adegas.twittertest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter @Setter 
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class User {
	
	@Transient
	public static final String SEQUENCE_NAME = "users_sequence";
	
    @Id
    private Long id;
    private String name;
    @Email
    private String email;
    private String imageUrl;
    private Boolean emailVerified = false;

    @JsonIgnore
    private String password;

    @NotNull
    //@Enumerated(EnumType.STRING)
    private AuthProvider provider;

    private String providerId;
}