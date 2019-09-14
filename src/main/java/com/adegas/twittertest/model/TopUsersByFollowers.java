package com.adegas.twittertest.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter 
@AllArgsConstructor 
@NoArgsConstructor
public class TopUsersByFollowers implements Serializable {
	
	/**
	 * Serialization number.
	 */
	private static final long serialVersionUID = -6888214942819168948L;

	@Id Long id;
	
	String userName;
	
	Integer followers;

}
