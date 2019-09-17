package com.adegas.twittertest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

@Getter @Setter 
@AllArgsConstructor
@NoArgsConstructor
public class User {
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