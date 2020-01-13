package com.parkingspot.dto;

import java.io.Serializable;

public class ParkingMessage implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public ParkingMessage(String message) {
		this.message = message;
	}
	
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
