package com.whoshere.consul;

public class Config {	
	long sleepDurationMillis;
	
	private String consulUser;
	private String consulPass;
	private String consulResourceUrl;
	
	private String smtpServer;
	private String smtpUser;
	private String smtpPass;
	
	private String notifSenderEmail;
	private String notifRecipientEmail;
	
	public long getSleepDurationMillis() {
		return sleepDurationMillis;
	}
	
	public void setSleepDurationMillis(long sleepDurationMillis) {
		this.sleepDurationMillis = sleepDurationMillis;
	}
	
	public String getConsulUser() {
		return consulUser;
	}
	
	public void setConsulUser(String consulUser) {
		this.consulUser = consulUser;
	}
	
	public String getConsulPass() {
		return consulPass;
	}
	
	public void setConsulPass(String consulPass) {
		this.consulPass = consulPass;
	}
	
	public String getConsulResourceUrl() {
		return consulResourceUrl;
	}
	
	public void setConsulResourceUrl(String consulResourceUrl) {
		this.consulResourceUrl = consulResourceUrl;
	}
	
	public String getSmtpServer() {
		return smtpServer;
	}
	
	public void setSmtpServer(String smtpServer) {
		this.smtpServer = smtpServer;
	}
	
	public String getSmtpUser() {
		return smtpUser;
	}
	
	public void setSmtpUser(String smtpUser) {
		this.smtpUser = smtpUser;
	}
	
	public String getSmtpPass() {
		return smtpPass;
	}
	
	public void setSmtpPass(String smtpPass) {
		this.smtpPass = smtpPass;
	}
	
	public String getNotifSenderEmail() {
		return notifSenderEmail;
	}
	
	public void setNotifSenderEmail(String notifSenderEmail) {
		this.notifSenderEmail = notifSenderEmail;
	}
	
	public String getNotifRecipientEmail() {
		return notifRecipientEmail;
	}
	
	public void setNotifRecipientEmail(String notifRecipientEmail) {
		this.notifRecipientEmail = notifRecipientEmail;
	}	
}
