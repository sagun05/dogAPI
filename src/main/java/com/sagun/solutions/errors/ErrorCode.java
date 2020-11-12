package com.sagun.solutions.errors;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonValue;

public class ErrorCode {
	private String message;
	private int code;

	public ErrorCode(String message, int code){
		this.message=message;
		this.code=code;
		
	}
	
	@Override
	@JsonValue
	@JsonRawValue
	public String toString(){

			return "{\"code\":"+code+",\"message\":\""+message+"\"}";
	}
}