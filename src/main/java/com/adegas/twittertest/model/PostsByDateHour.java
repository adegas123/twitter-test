package com.adegas.twittertest.model;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter 
@AllArgsConstructor 
@NoArgsConstructor
public class PostsByDateHour implements Serializable {
	
	/**
	 * Serialization number.
	 */
	private static final long serialVersionUID = 1826161080613574681L;

	String hour;
	
	Integer count;

}
