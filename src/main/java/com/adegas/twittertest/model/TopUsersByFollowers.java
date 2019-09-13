package com.adegas.twittertest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "top_users_by_followers")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class TopUsersByFollowers {
	
	@Id @Column(name = "user_id") Long id;
	
	@Column(name = "user_name") String userName;
	
	@Column(name = "followers") Integer followers;

}
