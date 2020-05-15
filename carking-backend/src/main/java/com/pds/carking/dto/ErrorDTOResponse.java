package com.pds.carking.dto;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ErrorDTOResponse {

	private int status;
	private String error;
	private String timestamp;
	
	public ErrorDTOResponse (int status, String error) {
		this.status = status;
		this.error = error;
		this.timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
}
