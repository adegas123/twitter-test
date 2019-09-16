package com.adegas.twittertest.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter 
@AllArgsConstructor 
@NoArgsConstructor
@ToString
public class PostsByTagsAndLang implements Serializable {
	
	/**
	 * Serialization number.
	 */
	private static final long serialVersionUID = 3477893715762401957L;

	String username;
	
	String tag;
	
	String lang;
	
	Integer count;

}
