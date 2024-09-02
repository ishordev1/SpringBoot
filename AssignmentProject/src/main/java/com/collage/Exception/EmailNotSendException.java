package com.collage.Exception;

public class EmailNotSendException extends RuntimeException{
public EmailNotSendException() {
	super();
}
public EmailNotSendException(String message) {
	super(message);
}
}
