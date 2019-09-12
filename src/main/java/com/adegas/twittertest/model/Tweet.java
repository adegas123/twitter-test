package com.adegas.twittertest.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tweet")
@Getter @Setter 
@AllArgsConstructor 
@NoArgsConstructor
public class Tweet {
	
	@Id @Column(name = "id") private Long id;
	
	@Column(name = "tag") private String tag;
	
	@Column(name = "user_id") private Long userId;
	
	@Column(name = "user_name") private String userName;
	
	@Column(name = "followers") private Integer followers;
	
	@Column(name = "lang") private String lang;
	
	@Column(name = "created_at") private Date createdAt;

}
