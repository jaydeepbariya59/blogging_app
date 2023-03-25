package com.jaydeep.blogapplication.util;

import java.time.LocalDateTime;

public class ErrorInfo {
	
	private int statusCode;
	private String errorMsg;
	private LocalDateTime timeStamp;
	
	public ErrorInfo(int statusCode, String errorMsg, LocalDateTime timeStamp) {
		super();
		this.statusCode = statusCode;
		this.errorMsg = errorMsg;
		this.timeStamp = timeStamp;
	}

	public ErrorInfo() {
		super();
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "ErrorInfo [statusCode=" + statusCode + ", errorMsg=" + errorMsg + ", timeStamp=" + timeStamp + "]";
	}
	
	
	
}
