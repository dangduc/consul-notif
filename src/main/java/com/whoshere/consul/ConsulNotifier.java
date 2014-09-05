package com.whoshere.consul;

import java.io.IOException;
import java.util.List;

import org.apache.commons.mail.EmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.whoshere.consul.input.InputHelper;
import com.whoshere.consul.input.Node;
import com.whoshere.consul.model.HealthChecker;
import com.whoshere.consul.model.Notification;
import com.whoshere.consul.service.EmailService;
import com.whoshere.consul.service.HttpService;

public class ConsulNotifier {
	
	private static Logger logger = LoggerFactory.getLogger(ConsulNotifier.class);
	
	private static String DEFAULT_CONFIG_FILEPATH = "consul-notif.json";
	
	public static void main(String[] args) {
		Config config;
		try {
			config = ConfigLoader.loadConfig(DEFAULT_CONFIG_FILEPATH);
		} catch (IOException e) {
			logger.error("cannot open config file " + DEFAULT_CONFIG_FILEPATH, e);
			return;
		}
		
		HttpService httpService = new HttpService(config.getConsulUser(), config.getConsulPass());
		HealthChecker healthChecker = new HealthChecker();
		EmailService emailService = new EmailService(config);
		
		while (true) {
			logger.debug("polling Consul");
			String jsonInput;
			
			try {
				jsonInput = httpService.makeRestCall(config.getConsulResourceUrl());
			} catch (Exception e) {
				logger.warn("http service error", e);
				continue;
			}
			
			List<Node> nodes;
			try {
				nodes = InputHelper.parseJson(jsonInput);
			} catch (Exception e) {
				logger.warn("cannot parse json", e);
				continue;
			}
			
			List<Notification> notifs = healthChecker.process(nodes);
			
			if (notifs.size() == 0) {
				logger.debug("nodes are stable");
			}
			
			for (Notification n : notifs) {
				logger.info(String.format("sending email %s", n));
				
				try {
					emailService.send(n);
				} catch (EmailException e) {
					logger.warn("error while sending notification", e);
				}
			}
			
			logger.debug("sleeping.");
			try {
				Thread.sleep(config.sleepDurationMillis);
			} catch (InterruptedException e) {
				logger.error("sleep interrupted", e);
				continue;
			}
		}
	}
}
