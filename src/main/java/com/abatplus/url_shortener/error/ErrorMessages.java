package com.abatplus.url_shortener.error;

import java.util.Date;

public class ErrorMessages {
	
	private Date timestamp;
	private String message;
	private int statusCode;
	private String error;
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public ErrorMessages(Date timestamp, String message, int statusCode, String error) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.statusCode = statusCode;
		this.error = error;
	}
	
	
	
	
	
	
	

}
