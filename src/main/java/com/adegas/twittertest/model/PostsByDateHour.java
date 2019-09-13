package com.adegas.twittertest.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "posts_by_date_hour")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class PostsByDateHour {
	
	@Column(name = "date") Date date;
	
	@Column(name = "count") Integer count;

}
