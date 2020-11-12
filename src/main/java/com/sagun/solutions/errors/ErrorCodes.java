package com.sagun.solutions.errors;

public class ErrorCodes {
	
	public static final ErrorCode MISSING_PARAMETERS_ERROR=new ErrorCode("Missing data in request or request data is NULL", 400);
	public static final ErrorCode INTERNAL_SERVER_ERROR=new ErrorCode("Internal Server Error",500);
	
	private ErrorCodes(){}
}