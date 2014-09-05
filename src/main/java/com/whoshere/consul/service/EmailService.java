package com.whoshere.consul.service;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import com.whoshere.consul.Config;
import com.whoshere.consul.model.Notification;

public class EmailService {
	private Config config = null;
	public EmailService(Config config) {
		this.config = config;
	}
			
	private Email createEmail() throws EmailException {
		if (config == null) {
			throw new EmailException("cannot create email without config");
		}
		
		Email email = new SimpleEmail();
		
		email.setHostName(config.getSmtpServer());
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator(config.getSmtpUser(), config.getSmtpPass()));
		email.setSSLOnConnect(true);
		email.setFrom(config.getNotifSenderEmail());
		email.addTo(config.getNotifRecipientEmail());
		
		return email;
	}
	
	public void send(Notification notif) throws EmailException{
		Email email = createEmail();
		
		email.setSubject(String.format("Consul - %s", notif.getSubject()));
		email.setMsg(notif.getBody());
		
		email.send();
	}
}
