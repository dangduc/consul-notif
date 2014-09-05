package com.whoshere.consul.model;

import java.util.LinkedList;
import java.util.List;

import com.whoshere.consul.input.Check;
import com.whoshere.consul.input.Node;

public class HealthChecker {
	CheckStore checks;
	
	public HealthChecker() {
		checks = new CheckStore();
	}
	
	public List<Notification> process(List<Node> nodes) {
		List<Notification> notifs = new LinkedList<Notification>();
		
		for (Node n : nodes) {
			for (Check c: n.getChecks()) {
				if (checks.checkExists(c)) {
					Check oldCheck = checks.getCheck(c);
					
					if (!oldCheck.getStatus().equals(c.getStatus())) {
						notifs.add(createHealthNotification(c));
					}
				}
				
				checks.putCheck(c);
			}
		}
		
		return notifs;
	}
	
	private Notification createHealthNotification(Check c) {
		return new Notification(String.format("%s - %s is %s", c.getNode(), c.getCheckId(), c.getStatus()), c.getOutput());
	}
}
