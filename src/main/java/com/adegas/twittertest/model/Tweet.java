package com.adegas.twittertest.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter 
@AllArgsConstructor 
@NoArgsConstructor
public class Tweet implements Serializable {
	
	/**
	 * Serialization number.
	 */
	private static final long serialVersionUID = -4465627603476977236L;

	@Id private Long id;
	
	private String tag;
	
	private Long userId;
	
	private String userName;
	
	private Integer followers;
	
	private String lang;
	
	private Date createdAt;

}
