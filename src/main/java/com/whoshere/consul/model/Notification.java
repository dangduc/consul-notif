package com.whoshere.consul.model;

public class Notification {
	private String subject;
	private String body;
	
	public Notification(String subject, String body) {
		this.subject = subject;
		this.body = body;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	@Override
	public String toString() {
		return String.format("subject:%s", subject);
	}
}
